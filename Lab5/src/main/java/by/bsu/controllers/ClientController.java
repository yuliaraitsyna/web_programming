package by.bsu.controllers;

import by.bsu.dao.ClientDao;
import by.bsu.entity.Client;
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
    public void process(IWebExchange exchange, ITemplateEngine templateEngine, Writer writer) {
        try {
            List<Client> clients = clientDao.getAllClients();

            WebContext context = new WebContext(exchange, exchange.getLocale());
            context.setVariable("clients", clients);

            templateEngine.process("client/client", context, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
