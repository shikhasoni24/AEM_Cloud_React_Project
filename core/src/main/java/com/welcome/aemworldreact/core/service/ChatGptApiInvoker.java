package com.welcome.aemworldreact.core.service;

import java.util.List;

public interface ChatGptApiInvoker {

    public String invokeAPI(String bodyText, int maxTokens);

    public List<String> invokeCreateImageApi(String bodyText , int numberOfImages , String size);

}
