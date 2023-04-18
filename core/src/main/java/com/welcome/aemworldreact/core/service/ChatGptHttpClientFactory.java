package com.welcome.aemworldreact.core.service;

import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;

public interface ChatGptHttpClientFactory {

    Executor getExecutor();

    Request post(String baseUrl);

    public String getBaseUrl();

    public String getCreateImageBaseUrl();


}
