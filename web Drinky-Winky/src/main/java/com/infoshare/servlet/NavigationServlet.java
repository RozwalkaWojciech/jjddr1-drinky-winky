package com.infoshare.servlet;

import com.infoshare.freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/Navigation-servlet")
public class NavigationServlet extends HttpServlet {

    @Inject
    TemplateProvider templateProvider;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Map<String, Object> root = new HashMap<>();

        if (request.getParameter("navigator").equals("user")) {
            response.sendRedirect("User-view");

        } else if (request.getParameter("navigator").equals("admin")) {
            Template template = templateProvider.getTemplate(getServletContext(), "admin.ftlh");
            Writer out = response.getWriter();

            try {
                template.process(root, out);
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }
    }
}
