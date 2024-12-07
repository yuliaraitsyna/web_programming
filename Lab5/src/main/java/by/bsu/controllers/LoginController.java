package by.bsu.controllers;

import by.bsu.entity.User;
import by.bsu.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;

public class LoginController implements IController {

    private final UserService userService = new UserService();

    @Override
    public void process(IWebExchange exchange, ITemplateEngine templateEngine, Writer writer,
                        final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        WebContext context = new WebContext(exchange, exchange.getLocale());

        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            User user = userService.authenticate(username, password);

            if (user != null) {
                request.getSession().setAttribute("user", user);

                Cookie userCookie = new Cookie("userDetails", user.getUsername() + ":" + user.getAccessLevel());
                userCookie.setMaxAge(60 * 60);
                userCookie.setPath("/");
                userCookie.setHttpOnly(true);
                userCookie.setSecure(request.isSecure());

                response.addCookie(userCookie);

                response.sendRedirect(request.getContextPath() + "/");
                return;
            } else {
                context.setVariable("error", "Invalid username or password.");
            }
        }

        templateEngine.process("login/login", context, writer);
    }
}
