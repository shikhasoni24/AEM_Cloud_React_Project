package com.welcome.aemworldreact.core.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.welcome.aemworldreact.core.bean.ChatGptResponseCreateImage;
import com.welcome.aemworldreact.core.bean.CreateImageBean;
import com.welcome.aemworldreact.core.bean.SummaryBean;
import com.welcome.aemworldreact.core.service.ChatGptApiInvoker;
import com.welcome.aemworldreact.core.service.ChatGptHttpClientFactory;
import com.welcome.aemworldreact.core.service.JSONConverter;
import com.welcome.aemworldreact.core.utils.StringObjectResponseHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Component(service = ChatGptApiInvoker.class)
public class ChatGptApiInvokerImpl implements ChatGptApiInvoker {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private static final StringObjectResponseHandler HANDLER = new StringObjectResponseHandler();

    private static final ObjectMapper MAPPER = new ObjectMapper();


    @Reference
    private ChatGptHttpClientFactory chatGptHttpClientFactory;

    @Reference
    private JSONConverter jsonConverter;


    @Override
    public String invokeAPI(String bodyText, int maxTokens) {
        String responseString = StringUtils.EMPTY;
        try {
            responseString = chatGptHttpClientFactory.getExecutor()
                    .execute(chatGptHttpClientFactory.post(chatGptHttpClientFactory.getBaseUrl())
                    .bodyString(generatePrompt(bodyText, maxTokens), ContentType.APPLICATION_JSON))
                    .handleResponse(HANDLER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseString;
    }

    @Override
    public List<String> invokeCreateImageApi(String bodyText , int numberOfImage , String size) {
        String responeString = StringUtils.EMPTY;

            HttpClient httpClient = HttpClientBuilder.create().build();
            List<String> urls = new ArrayList<>();

            try {
                HttpPost request = new HttpPost(chatGptHttpClientFactory.getCreateImageBaseUrl());
                request.addHeader("Authorization", "Bearer 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");
                request.addHeader("Content-Type", "application/json");
                String params = MAPPER.writeValueAsString(new CreateImageBean(bodyText, numberOfImage, size));
                request.setEntity(new StringEntity(params));
                HttpResponse response = httpClient.execute(request);
                ChatGptResponseCreateImage chatGptResponse = MAPPER.readValue(EntityUtils.toString(response.getEntity()), ChatGptResponseCreateImage.class);
                ListIterator<ChatGptResponseCreateImage.Url> listIterator = chatGptResponse.getData().listIterator();
                while(listIterator.hasNext()){
                    urls.add(listIterator.next().getUrl());
                }
            /*StringEntity params =new StringEntity(generatePromptForCreateImage(bodyText, numberOfImage, size));

        responeString = chatGptHttpClientFactory.getExecutor()
                    .execute(chatGptHttpClientFactory.post(chatGptHttpClientFactory.getCreateImageBaseUrl())
                            .body(params).*/
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return urls;
    }

    private String generatePrompt(String bodyText, int maxTokens) {
        SummaryBean bodyBean = new SummaryBean();
        if(maxTokens != 0) {
            bodyBean.setMaxTokens(maxTokens);
        }
        bodyBean.setPrompt(bodyText);
        return jsonConverter.convertToJsonString(bodyBean);
    }

    /*private String generatePromptForCreateImage(String bodyText, int numberOfImage, String size) {
        CreateImageBean createImageBean = new CreateImageBean();
        createImageBean.setPrompt(bodyText);
        createImageBean.setNumberOfImage(numberOfImage);
        createImageBean.setSize(size);
        String converted = jsonConverter.convertToJsonString(createImageBean);;
        return converted;

    }*/
    }
