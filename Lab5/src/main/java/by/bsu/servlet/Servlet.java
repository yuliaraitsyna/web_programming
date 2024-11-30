package by.bsu.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Calendar;

import by.bsu.controllers.HomeController;
import by.bsu.controllers.IController;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebApplication;
import org.thymeleaf.web.IWebRequest;
import org.thymeleaf.web.servlet.IServletWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/*", loadOnStartup = 1)
public class Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private JakartaServletWebApplication application;
    private ITemplateEngine templateEngine;

    @Override
    public void init() {
        this.application = JakartaServletWebApplication.buildApplication(getServletContext());
        this.templateEngine = buildTemplateEngine(this.application);
    }

    private ITemplateEngine buildTemplateEngine(final IWebApplication application) {
        final WebApplicationTemplateResolver templateResolver = new WebApplicationTemplateResolver(application);

        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheTTLMs(3600000L);
        templateResolver.setCacheable(true);

        final TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String uri = request.getRequestURI();
            System.out.println("Request URI: " + uri);

//            if ("/css/gtvg.css".equals(uri)) {
//                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//                return;
//            }

            if (uri.startsWith("/css/")) {
                serveStaticResource(uri, response);
                return;
            }

            String formattedDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            request.getSession().setAttribute("calendar", Calendar.getInstance());

            jakarta.servlet.http.Cookie[] cookies = request.getCookies();
            String lastVisit = "First visit";
            int visitCount = 0;

            if (cookies != null) {
                for (jakarta.servlet.http.Cookie cookie : cookies) {
                    if ("lastVisit".equals(cookie.getName())) {
                        lastVisit = cookie.getValue();
                    }
                    if ("visitCount".equals(cookie.getName())) {
                        visitCount = Integer.parseInt(cookie.getValue());
                    }
                }
            }

            request.setAttribute("lastVisit", lastVisit);
            request.setAttribute("visitCount", visitCount);

            visitCount++;
            jakarta.servlet.http.Cookie lastVisitCookie = new jakarta.servlet.http.Cookie("lastVisit", formattedDate);
            jakarta.servlet.http.Cookie visitCountCookie = new jakarta.servlet.http.Cookie("visitCount", String.valueOf(visitCount));

            lastVisitCookie.setMaxAge(60 * 60);
            visitCountCookie.setMaxAge(60 * 60);

            lastVisitCookie.setPath("/");
            visitCountCookie.setPath("/");

            response.addCookie(lastVisitCookie);
            response.addCookie(visitCountCookie);

            final IServletWebExchange webExchange = this.application.buildExchange(request, response);
            final IWebRequest webRequest = webExchange.getRequest();
            final Writer writer = response.getWriter();

            IController controller = ControllerMapping.resolveControllerForRequest(webRequest);
            if (controller == null) {
                controller = new HomeController();
            }

            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            controller.process(webExchange, templateEngine, writer);

        } catch (Exception e) {
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (final IOException ignored) {
            }
            throw new ServletException(e);
        }
    }

    private void serveStaticResource(String uri, HttpServletResponse response) throws IOException {
        String resourcePath = uri.substring(1); // Remove leading slash
        InputStream resourceStream = getServletContext().getResourceAsStream("/" + resourcePath);

        if (resourceStream == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Detect content type (e.g., CSS, JS, images)
        String mimeType = getServletContext().getMimeType(resourcePath);
        response.setContentType(mimeType != null ? mimeType : "application/octet-stream");

        // Write resource to response
        OutputStream out = response.getOutputStream();
        resourceStream.transferTo(out);
        out.close();
    }

}
