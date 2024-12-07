package by.bsu.controllers;

import by.bsu.dao.StaffDao;
import by.bsu.entity.Staff;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;
import java.util.List;

public class StaffController implements IController {
    private final StaffDao staffDao;

    public StaffController() {
        this.staffDao = new StaffDao();
    }

    @Override
    public void process(IWebExchange exchange, ITemplateEngine templateEngine, Writer writer, final HttpServletRequest request, final HttpServletResponse response) {
        try {
            List<Staff> staff = staffDao.getAllStaff();

            WebContext context = new WebContext(exchange, exchange.getLocale());
            context.setVariable("staffs", staff);

            templateEngine.process("staff/staff", context, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
