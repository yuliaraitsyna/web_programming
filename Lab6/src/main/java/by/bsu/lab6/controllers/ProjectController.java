package by.bsu.lab6.controllers;

import by.bsu.lab6.entity.Project;
import by.bsu.lab6.repository.ProjectRepository;

import by.bsu.lab6.repository.TechnicalTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectController implements IController {
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("projects", projectRepository.findAll());
        return "projects/index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("project", new Project());
        return "projects/add/index";
    }

    @PostMapping
    public String add(@ModelAttribute Project project) {
        projectRepository.save(project);
        return "redirect:/project";
    }
}
