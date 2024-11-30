package by.bsu.controllers;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import javax.annotation.processing.AbstractProcessor;
import java.io.Writer;

public class ErrorController implements IController {

    @Override
    public void process(IWebExchange exchange, ITemplateEngine templateEngine, Writer writer) throws Exception {
        throw new RuntimeException("This is a simulated error for testing purposes.");
    }
}