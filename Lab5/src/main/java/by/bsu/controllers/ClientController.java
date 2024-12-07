package by.bsu.controllers;

import by.bsu.dao.ClientDao;
import by.bsu.entity.Client;
import by.bsu.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;
import java.util.List;

public class ClientController implements IController {

    private final ClientDao clientDao;

    public ClientController() {
        this.clientDao = new ClientDao();
    }

    @Override
    public void process(IWebExchange exchange, ITemplateEngine templateEngine, Writer writer,
                        final HttpServletRequest request, final HttpServletResponse response) {
        try {
            String path = request.getRequestURI();

            WebContext context = new WebContext(exchange, exchange.getLocale());
            User user = (User) request.getSession(true).getAttribute("user");
            if (user != null) {
                context.setVariable("role", user.getAccessLevel());
            } else {
                context.setVariable("role", "GUEST");
            }

            if ("/add-client".equals(path) && "POST".equalsIgnoreCase(request.getMethod())) {
                handleAddClient(request, response);
            } else if ("/add-client".equals(path) && "GET".equalsIgnoreCase(request.getMethod())) {
                renderAddClientForm(exchange, templateEngine, writer);
            } else {
                listClients(exchange, templateEngine, writer, request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void listClients(IWebExchange exchange, ITemplateEngine templateEngine, Writer writer, HttpServletRequest request) {
        try {
            List<Client> clients = clientDao.getAllClients();
            WebContext context = new WebContext(exchange, exchange.getLocale());

            User user = (User) request.getSession(true).getAttribute("user");
            if (user != null) {
                context.setVariable("role", user.getAccessLevel());
            } else {
                context.setVariable("role", "GUEST");
            }

            context.setVariable("clients", clients);
            templateEngine.process("client/client", context, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void renderAddClientForm(IWebExchange exchange, ITemplateEngine templateEngine, Writer writer) {
        try {
            WebContext context = new WebContext(exchange, exchange.getLocale());
            templateEngine.process("client/add-client", context, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleAddClient(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");

            Client client = new Client(name, surname);
            clientDao.addClient(client);

            response.sendRedirect(request.getContextPath() + "/client");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
