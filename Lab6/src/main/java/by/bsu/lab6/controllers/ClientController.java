package by.bsu.lab6.controllers;

import by.bsu.lab6.entity.Client;
import by.bsu.lab6.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
public class ClientController implements IController{

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("clients", clientRepository.findAll());
        return "clients/index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("client", new Client());
        return "clients/add/index";
    }

    @PostMapping
    public String add(@ModelAttribute Client client) {
        clientRepository.save(client);
        return "redirect:/client";
    }
}
