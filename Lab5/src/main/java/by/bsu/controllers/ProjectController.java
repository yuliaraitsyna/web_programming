package by.bsu.controllers;

import by.bsu.dao.ClientDao;
import by.bsu.dao.ProjectDao;
import by.bsu.entity.Client;
import by.bsu.entity.Project;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;
import java.util.List;

public class ProjectController implements IController {
    private final ProjectDao projectDao;

    public ProjectController() {
        this.projectDao = new ProjectDao();
    }

    @Override
    public void process(IWebExchange exchange, ITemplateEngine templateEngine, Writer writer, final HttpServletRequest request, final HttpServletResponse response) {
        try {
            List<Project> projects = projectDao.getAllProjects();

            WebContext context = new WebContext(exchange, exchange.getLocale());
            context.setVariable("projects", projects);

            templateEngine.process("projects/projects", context, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
