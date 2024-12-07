package by.bsu.filter;

import by.bsu.controllers.*;
import by.bsu.controllers.HomeController;
import org.thymeleaf.web.IWebRequest;

import java.util.HashMap;
import java.util.Map;

public class ControllerMapping {

    private static final Map<String, IController> controllersByURL = new HashMap<>();

    static {
        controllersByURL.put("/", new HomeController());
        controllersByURL.put("/client", new ClientController());
        controllersByURL.put("/projects", new ProjectController());
        controllersByURL.put("/staff", new StaffController());
        controllersByURL.put("/technical_tasks", new TechnicalTaskController());
        controllersByURL.put("/login", new LoginController());
        controllersByURL.put("/logout", new LogoutController());
    }


    public static IController resolveControllerForRequest(final IWebRequest request) {
        final String path = request.getPathWithinApplication();
        System.out.println("Request path: " + path);
        return controllersByURL.get(path);
    }
}
