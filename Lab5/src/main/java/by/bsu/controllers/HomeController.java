package by.bsu.controllers;

import by.bsu.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;

public class HomeController implements IController {

    @Override
    public void process(IWebExchange exchange, ITemplateEngine templateEngine, Writer writer, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        WebContext context = new WebContext(exchange, exchange.getLocale());

        User user = (User) request.getSession(true).getAttribute("user");

        context.setVariable("username", user.getUsername());

        if(!user.getAccessLevel().equals("GUEST")) {
            context.setVariable("username", user.getUsername());
        } else {
            context.setVariable("username", "Guest");
        }

        context.setVariable("role", user.getAccessLevel());
        templateEngine.process("home", context, writer);
    }
}
