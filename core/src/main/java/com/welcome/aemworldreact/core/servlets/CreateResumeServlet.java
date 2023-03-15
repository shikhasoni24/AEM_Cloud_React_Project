package com.welcome.aemworldreact.core.servlets;

import com.day.cq.commons.jcr.JcrConstants;
import com.welcome.aemworldreact.core.service.CreateResumeService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Create Resume Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/bin/aemworldreact/createresume" })
public class CreateResumeServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;
    @Reference
    private CreateResumeService createResumeService;

    @Override
    protected void doGet(final SlingHttpServletRequest req,
                         final SlingHttpServletResponse resp) throws ServletException, IOException {
        final Resource resource = req.getResource();
        String response = createResumeService.readJsonFile();
        resp.setContentType("application/json");
        resp.getWriter().write(response);
    }
}
