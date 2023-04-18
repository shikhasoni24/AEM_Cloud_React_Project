package com.welcome.aemworldreact.core.service.impl;

import com.welcome.aemworldreact.core.service.ChatGptHttpClientFactory;
import com.welcome.aemworldreact.core.service.config.ChatGptHttpClientFactoryConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.osgi.services.HttpClientBuilderFactory;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component(service = ChatGptHttpClientFactory.class)
@Designate(ocd = ChatGptHttpClientFactoryConfig.class)
public class ChatGptHttpClientFactoryImpl implements ChatGptHttpClientFactory {

    private final Logger log = LoggerFactory.getLogger(getClass());


    private Executor executor;

    private String baseUrl;


    private String createImageBaseUrl;

    private CloseableHttpClient httpClient;

    private ChatGptHttpClientFactoryConfig config;

    @Reference
    private HttpClientBuilderFactory httpClientBuilderFactory;

    @Activate
    @Modified
    protected void activate(ChatGptHttpClientFactoryConfig config) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        log.info("##### Osgi config Start");
        log.info("API Host Name : {}", config.apiHostName());
        log.info("URI Type: {}", config.uriType());
        log.info("########### OSGi Configs End ###############");
        closeHttpConnection();
        this.config = config;
        if(this.config.apiHostName() == null){
            log.debug("Configuration is not Valid. Both hostname is mandatory.");
            throw new IllegalArgumentException("Configuration is not valid. Both hostname is mandatory.");
        }
        this.baseUrl = StringUtils.join(this.config.apiHostName(), config.uriType());
        this.createImageBaseUrl = StringUtils.join(this.config.apiHostName(), config.createImageUriType());
        initExecutor();
        

    }

    private void initExecutor() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        PoolingHttpClientConnectionManager conMgr = null;
        RequestConfig requestConfig = initRequestConfig();
        HttpClientBuilder builder = httpClientBuilderFactory.newBuilder();
        builder.setDefaultRequestConfig(requestConfig);
        if(config.relaxedSSL()){
            conMgr = initPoolingConnectionManagerWithRelaxedSSL();
        }
        else{
            conMgr = new PoolingHttpClientConnectionManager();
        }
        conMgr.closeExpiredConnections();
        conMgr.setMaxTotal(config.maxTotalOpenConnections());
        conMgr.setDefaultMaxPerRoute(config.maxConcurrentConnectionPerRoute());
        builder.setConnectionManager(conMgr);
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Authorization", "Bearer " + config.apiKey()));
        builder.setDefaultHeaders(headers);
        builder.setKeepAliveStrategy(keepAliveStrategy);
        httpClient = builder.build();
        executor = Executor.newInstance(httpClient);


    }
    @Deactivate
    protected void deactivate() {
        closeHttpConnection();
    }

    private PoolingHttpClientConnectionManager initPoolingConnectionManagerWithRelaxedSSL() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        PoolingHttpClientConnectionManager connMgr;
        SSLContextBuilder sslbuilder = new SSLContextBuilder();
        sslbuilder.loadTrustMaterial(new TrustAllStrategy());
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslbuilder.build(),
                NoopHostnameVerifier.INSTANCE);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf).build();
        connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        return connMgr;
    }

    private RequestConfig initRequestConfig() {
        return RequestConfig.custom().setConnectTimeout(Math.toIntExact(TimeUnit.SECONDS.toMillis(config.defaultConnectionTimeout())))
                .setSocketTimeout(Math.toIntExact(TimeUnit.SECONDS.toMillis(config.defaultSocketTimeout())))
                .setConnectionRequestTimeout(Math.toIntExact(TimeUnit.SECONDS.toMillis(config.defaultConnectionRequestTimeout())))
                .build();

    }

    private void closeHttpConnection() {
        if( httpClient != null){
            try {
                httpClient.close();
            } catch (final IOException e) {
                log.error("IOException while closing API, {}",e.getMessage());
            }
        }
    }

    @Override
    public Executor getExecutor() {
        return executor;
    }

    @Override
    public Request post(String baseUrl) {
       return Request.Post(baseUrl);
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getCreateImageBaseUrl() {
        return createImageBaseUrl;
    }
    ConnectionKeepAliveStrategy keepAliveStrategy = new ConnectionKeepAliveStrategy() {
        @Override
        public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
            return  TimeUnit.SECONDS.toMillis(config.defaultKeepAliveconnection());
        }
    };
}
