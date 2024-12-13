package by.bsu.lab6.controllers;

import by.bsu.lab6.entity.Client;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public interface IController {
    public String list(Model model);
    public String showAddForm(Model model);
}
