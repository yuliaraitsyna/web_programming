package by.bsu.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;

public class LogoutController implements IController {

    @Override
    public void process(IWebExchange exchange, ITemplateEngine templateEngine, Writer writer,
                        final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/");
    }
}
