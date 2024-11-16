package by.bsu.servlet;

import by.bsu.controllers.*;
import by.bsu.controllers.HomeController;
import org.thymeleaf.web.IWebRequest;

import java.util.HashMap;
import java.util.Map;

public class ControllerMapping {

    private static final Map<String, IController> controllersByURL = new HashMap<>();

    static {
        // Home page mapping
        controllersByURL.put("/", new HomeController());

        // Add your controllers for each path
        controllersByURL.put("/client", new ClientController()); // Client page
        controllersByURL.put("/projects", new ProjectController()); // Projects page
//        controllersByURL.put("/staff", new StaffController()); // Staff page
//        controllersByURL.put("/technical_tasks", new TechnicalTaskController()); // Technical Tasks page
    }

    public static IController resolveControllerForRequest(final IWebRequest request) {
        final String path = request.getPathWithinApplication();
        System.out.println("Request path: " + path);
        return controllersByURL.get(path);
    }
}
