package com.welcome.aemworldreact.core.servlets;

import com.day.cq.wcm.api.Page;
import com.drew.lang.annotations.NotNull;
import com.welcome.aemworldreact.core.service.ChatGptApiInvoker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import java.io.IOException;

@Slf4j
@Component(service = { Servlet.class }, property = {
        "sling.servlet.paths=" + TeaserPromoServlet.RESOURCE_PATH, "sling.servlet.methods=GET" })
public class TeaserPromoServlet extends SlingSafeMethodsServlet {


    private static final long serialVersionUID = 1L;
    static final String RESOURCE_PATH = "/bin/teaserPromoServlet";
    static final String PAGE_PATH = "pagePath";
    static final String TITLE_TOKEN = "titleToken";
    static final String DESC_TOKEN = "descToken";
    private static final String TEASER_TITLE = "jcr:title";
    private static final String TEASER_DESCRIPTION = "jcr:description";

    @Reference
    private ChatGptApiInvoker chatGptApiInvoker;

    @Override
    protected void doGet(@NotNull SlingHttpServletRequest request, @NotNull SlingHttpServletResponse response) throws IOException {
        String pagePath = request.getParameter(PAGE_PATH);
        String titleTokenParam = request.getParameter(TITLE_TOKEN);
        String descTokenParam = request.getParameter(DESC_TOKEN);
        int titleToken = 0;
        int descToken = 0;
        JSONObject jsonObject = new JSONObject();
        if(StringUtils.isNoneEmpty(pagePath)) {
            Resource pageResource = request.getResourceResolver().resolve(pagePath);
            if(!ResourceUtil.isNonExistingResource(pageResource)) {
                Page page = pageResource.adaptTo(Page.class);
                String teaserTitle = page.getProperties().get(TEASER_TITLE, StringUtils.EMPTY);
                String teaserDescription = page.getProperties().get(TEASER_DESCRIPTION, StringUtils.EMPTY);
                try {
                    if(StringUtils.isNoneEmpty(titleTokenParam) && StringUtils.isNotEmpty(descTokenParam)) {
                        titleToken = Integer.parseInt(titleTokenParam);
                        descToken = Integer.parseInt(descTokenParam);
                    }
                    jsonObject.put(TEASER_TITLE, chatGptApiInvoker.invokeAPI(teaserTitle, titleToken));
                    jsonObject.put(TEASER_DESCRIPTION, chatGptApiInvoker.invokeAPI(teaserDescription, descToken));
                } catch (JSONException e) {
                    log.error(e.getMessage());
                }
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().print(jsonObject);
            }
        }

    }
}
