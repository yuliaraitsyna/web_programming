package by.bsu.controllers;

import by.bsu.dao.StaffDao;
import by.bsu.dao.TechnicalTaskDao;
import by.bsu.entity.Staff;
import by.bsu.entity.TechnicalTask;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;
import java.util.List;

public class TechnicalTaskController implements IController {
    private final TechnicalTaskDao technicalTaskDao;

    public TechnicalTaskController() {
        this.technicalTaskDao = new TechnicalTaskDao();
    }

    @Override
    public void process(IWebExchange exchange, ITemplateEngine templateEngine, Writer writer) {
        try {
            List<TechnicalTask> technicalTasks = technicalTaskDao.getAllTechnicalTasks();

            WebContext context = new WebContext(exchange, exchange.getLocale());
            context.setVariable("technicalTasks", technicalTasks);

            templateEngine.process("technical_tasks/technical_tasks", context, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
