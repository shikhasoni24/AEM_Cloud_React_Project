package com.welcome.aemworldreact.core.servlets;


import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.welcome.aemworldreact.core.bean.ChatGptRequest;
import com.welcome.aemworldreact.core.bean.ChatGptResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.osgi.framework.Constants;


import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@Component(
        immediate = true,
        service = Servlet.class,
        property = {
                Constants.SERVICE_DESCRIPTION + "=ChatGPT Integration",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                "sling.servlet.paths=" + "/bin/chat",
                "sling.servlet.extensions={\"json\"}"
        }
)
public class ChatServlet extends SlingSafeMethodsServlet {

    private static final Logger LOGGER = Logger.getLogger(ChatServlet.class.getName());

    private static final String CHATGPT_API_ENDPOINT = "https://api.openai.com/v1/chat/completions";

    private static final HttpClient client = HttpClients.createDefault();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {

        try {String prompt = request.getParameter("prompt");
            String message = generateMessage(prompt);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("answer",message);
            response.setContentType("application/json");
            response.getWriter().write(jsonObject.toString());

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    private static String generateMessage(String prompt) throws IOException {

        // Generate the chat message using ChatGPT API
        String requestBody = MAPPER.writeValueAsString(new ChatGptRequest(prompt,"gpt-3.5-turbo","user"));
        HttpPost request = new HttpPost(CHATGPT_API_ENDPOINT);
        request.addHeader("Authorization", "Bearer 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");
        request.addHeader("Content-Type", "application/json");
        request.setEntity(new StringEntity(requestBody));
        HttpResponse response = client.execute(request);
        ChatGptResponse chatGptResponse = MAPPER.readValue(EntityUtils.toString(response.getEntity()), ChatGptResponse.class);
        String message = chatGptResponse.getChoices().get(0).getMessage().getContent();

        return message;

    }


    public static void main(String[] args) {
        try {
            System.out.println(generateMessage("What is Adobe AEM"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}