package com.helmes.assignment.api.controller;


import com.helmes.assignment.api.model.EmptyUserResponse;
import com.helmes.assignment.api.model.User;
import com.helmes.assignment.server.exception.AssignmentException;
import com.helmes.assignment.server.services.SectorsService;
import com.helmes.assignment.server.services.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
public class UsersController {

	UsersService usersService;

	SectorsService sectorsService;

	public UsersController(UsersService usersService, SectorsService sectorsService) {
		this.usersService = usersService;
		this.sectorsService = sectorsService;
	}

	@PostMapping("/registration")
	public String register(@ModelAttribute @Valid User user, Model model) throws AssignmentException {
		System.out.println(user);
		User savedUser;
		if (user.getId() == null) {
			savedUser = usersService.createUser(user);
		} else {
			savedUser = usersService.updateUser(user);
		}
		model.addAttribute("sectors", sectorsService.getAllSectors());
		model.addAttribute("user", savedUser);
		return "redirect:/";
	}


	@GetMapping("/user")
	public String loadUser(User user, Model model) throws AssignmentException {
		User loadedUser = usersService.getUser(user.getName());
		if (loadedUser != null) {
			model.addAttribute("selectedSectorIds", loadedUser.getSectors());
		} else {
			model.addAttribute("user", new EmptyUserResponse());
		}
		model.addAttribute("sectors", sectorsService.getAllSectors());
		model.addAttribute("loadUser", new EmptyUserResponse());
		return "index";

	}


}
