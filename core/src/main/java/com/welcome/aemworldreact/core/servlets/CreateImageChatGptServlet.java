package com.welcome.aemworldreact.core.servlets;

import com.drew.lang.annotations.NotNull;
import com.welcome.aemworldreact.core.service.ChatGptApiInvoker;
import lombok.extern.slf4j.Slf4j;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component(service = { Servlet.class }, property = {
        "sling.servlet.paths=" + CreateImageChatGptServlet.RESOURCE_PATH, "sling.servlet.methods=GET" })
public class CreateImageChatGptServlet extends SlingSafeMethodsServlet {
         static final String RESOURCE_PATH = "/bin/createImage" ;

    @Reference
    private ChatGptApiInvoker chatGptApiInvoker;

    @Override
    protected void doGet(@NotNull SlingHttpServletRequest request, @NotNull SlingHttpServletResponse response) throws IOException {

        String bodyText = request.getParameter("prompt");
        String numberOfImage = request.getParameter("n");
        String size = request.getParameter("size");
        List<String> res =  chatGptApiInvoker.invokeCreateImageApi(bodyText, Integer.parseInt(numberOfImage), size);
        JSONArray jsonArray = new JSONArray(res);
        response.setContentType("application/json");
        response.getWriter().print(jsonArray.toString());


    }
}
