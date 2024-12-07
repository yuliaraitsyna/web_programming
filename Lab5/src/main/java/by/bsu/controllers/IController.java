package by.bsu.controllers;

import java.io.Writer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.web.IWebExchange;

public interface IController {
    public void process(final IWebExchange webExchange, final ITemplateEngine templateEngine, final Writer writer, final HttpServletRequest request, final HttpServletResponse response)
            throws Exception;

}
