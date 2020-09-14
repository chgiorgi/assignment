package com.helmes.assignment.api.controller;


import com.helmes.assignment.api.model.EmptyUserResponse;
import com.helmes.assignment.server.repository.UsersRepository;
import com.helmes.assignment.server.services.SectorsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class SectorsController {

    private final SectorsService sectorsService;
    private final UsersRepository usersRepository;

    public SectorsController(SectorsService sectorsService, UsersRepository usersRepository) {
        this.sectorsService = sectorsService;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/sectors")
    public String getAllSectors(Model model) {

        model.addAttribute("sectors", sectorsService.getAllSectors());
        model.addAttribute("user", new EmptyUserResponse());
        return "index";
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("user", new EmptyUserResponse());
        model.addAttribute("sectors", sectorsService.getAllSectors());
        model.addAttribute("loadUser", new EmptyUserResponse());
        return "index";
    }

}
