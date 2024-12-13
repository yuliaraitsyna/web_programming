package by.bsu.lab6.controllers;

import by.bsu.lab6.entity.Staff;
import by.bsu.lab6.entity.TechnicalTask;
import by.bsu.lab6.repository.StaffRepository;
import by.bsu.lab6.repository.TechnicalTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/technical_tasks")
public class TechnicalTaskController implements IController {
    @Autowired
    private TechnicalTaskRepository technicalTaskRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("tasks", technicalTaskRepository.findAll());
        return "technicalTasks/index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("task", new Staff());
        return "technicalTasks/add/index";
    }

    @PostMapping
    public String add(@ModelAttribute TechnicalTask technicalTask) {
        technicalTaskRepository.save(technicalTask);
        return "redirect:/staff";
    }
}
