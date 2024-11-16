package by.bsu.controllers;

import by.bsu.controllers.IController;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.IServletWebExchange;

import java.io.Writer;

public class ClientController implements IController {

    @Override
    public void process(IWebExchange exchange, ITemplateEngine templateEngine, Writer writer) {
        try {
            WebContext context = new WebContext(exchange, exchange.getLocale());
            templateEngine.process("client/client", context, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
