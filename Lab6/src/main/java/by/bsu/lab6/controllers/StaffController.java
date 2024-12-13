package by.bsu.lab6.controllers;

import by.bsu.lab6.entity.Staff;
import by.bsu.lab6.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffRepository staffRepository;

    @GetMapping
    public String listStaff(Model model) {
        model.addAttribute("staffs", staffRepository.findAll());
        return "staff/index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("staff", new Staff());
        return "staff/add/index";
    }

    @PostMapping
    public String addStaff(@ModelAttribute Staff staff) {
        staffRepository.save(staff);
        return "redirect:/staff";
    }
}
