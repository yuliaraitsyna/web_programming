package by.bsu.filter;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import by.bsu.controllers.HomeController;
import by.bsu.controllers.IController;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.messageresolver.IMessageResolver;
import org.thymeleaf.messageresolver.StandardMessageResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebApplication;
import org.thymeleaf.web.IWebRequest;
import org.thymeleaf.web.servlet.IServletWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/*")
public class WelcomeFilter implements Filter {
    private JakartaServletWebApplication application;
    private ITemplateEngine templateEngine;

    @Override
    public void init(FilterConfig filterConfig) {
        this.application = JakartaServletWebApplication.buildApplication(filterConfig.getServletContext());
        this.templateEngine = buildTemplateEngine(this.application);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (!process((HttpServletRequest) request, (HttpServletResponse) response)) {
            chain.doFilter(request, response);
        }
    }

    private ITemplateEngine buildTemplateEngine(final IWebApplication application) {
        final WebApplicationTemplateResolver templateResolver = new WebApplicationTemplateResolver(application);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheTTLMs(3600000L);
        templateResolver.setCacheable(true);
        IMessageResolver stringTemplateResolver = new StandardMessageResolver();

        final TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.setMessageResolver(stringTemplateResolver);
        return templateEngine;
    }

    private boolean process(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException {
        try {
            String formattedDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            request.getSession().setAttribute("calendar", Calendar.getInstance());

            Cookie[] cookies = request.getCookies();
            String lastVisit = "First visit";
            int visitCount = 0;

            if (cookies != null) {
                for (Cookie cookie : cookies) {
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

            Cookie lastVisitCookie = new Cookie("lastVisit", formattedDate);
            Cookie visitCountCookie = new Cookie("visitCount", String.valueOf(visitCount));

            lastVisitCookie.setMaxAge(3600);
            visitCountCookie.setMaxAge(3600);

            lastVisitCookie.setPath("/");
            visitCountCookie.setPath("/");

            response.addCookie(lastVisitCookie);
            response.addCookie(visitCountCookie);

            final IServletWebExchange webExchange = this.application.buildExchange(request, response);
            final IWebRequest webRequest = webExchange.getRequest();
            if (webRequest.getPathWithinApplication().startsWith("/css")
                    || webRequest.getPathWithinApplication().startsWith("/images")
                    || webRequest.getPathWithinApplication().startsWith("/favicon")) {
                return false;
            }

            IController controller = ControllerMapping.resolveControllerForRequest(webRequest);
            if (controller == null) {
                controller = new HomeController();
                return false;
            }

            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            final Writer writer = response.getWriter();
            controller.process(webExchange, templateEngine, writer);
            return true;

        } catch (Exception e) {
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (final IOException ignored) {
            }
            throw new ServletException(e);
        }
    }
}