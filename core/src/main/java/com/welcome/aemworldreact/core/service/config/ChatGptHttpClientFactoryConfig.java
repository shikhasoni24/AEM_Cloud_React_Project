package com.welcome.aemworldreact.core.service.config;


import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Chat GPT Http Client API Configuration", description = "Chat GPT Http Client API Configuration")
public @interface ChatGptHttpClientFactoryConfig {

    @AttributeDefinition(name = "API Host Name", description = "API host name", type= AttributeType.STRING)
    String apiHostName() default "https://api.openai.com";

    @AttributeDefinition(name = "API URI Type Path", description = "API URI type path", type = AttributeType.STRING)
    String uriType() default "/v1/engines/davinci/completions";

    @AttributeDefinition(name = "API URI Type Path To Create Image", description = "API URI type path to create image", type = AttributeType.STRING)
    String createImageUriType() default "/v1/images/generations";

    @AttributeDefinition(name = "API Key", description = "Chat GPT API Key", type = AttributeType.STRING)
    String apiKey() default "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";

    @AttributeDefinition(name = "Relaxed SSL", description = "Defines if self-certified certificates should be allowed to SSL transport", type = AttributeType.BOOLEAN)
    boolean relaxedSSL() default true;

    @AttributeDefinition(name = "Maximum number of total open connections", description = "Set maximum number of total open connections, default 5", type = AttributeType.INTEGER)
    int maxTotalOpenConnections() default 4;

    @AttributeDefinition(name = "Maximum number of concurrent connections per route", description = "Set the maximum number of concurrent connections per route, default 5", type = AttributeType.INTEGER)
    int maxConcurrentConnectionPerRoute() default 2;

    @AttributeDefinition(name = "Default Keep alive connection in seconds", description = "Default Keep alive connection in seconds, default value is 1", type = AttributeType.LONG)
    int defaultKeepAliveconnection() default 15;

    @AttributeDefinition(name = "Default connection timeout in seconds", description = "Default connection timout in seconds, default value is 30", type = AttributeType.LONG)
    long defaultConnectionTimeout() default 30;

    @AttributeDefinition(name = "Default socket timeout in seconds", description = "Default socket timeout in seconds, default value is 30", type = AttributeType.LONG)
    long defaultSocketTimeout() default 30;

    @AttributeDefinition(name = "Default connection request timeout in seconds", description = "Default connection request timeout in seconds, default value is 30", type = AttributeType.LONG)
    long defaultConnectionRequestTimeout() default 30;

}
