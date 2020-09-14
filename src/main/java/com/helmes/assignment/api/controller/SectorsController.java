package com.helmes.assignment.api.controller;


import com.helmes.assignment.server.services.SectorsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class SectorsController {

	private final SectorsService sectorsService;

	public SectorsController(SectorsService sectorsService) {
		this.sectorsService = sectorsService;
	}

	@GetMapping("/sectors")
	public String getAllSectors(Model model) {

		model.addAttribute("sectors", sectorsService.getAllSectors());
		return "index";

	}
	@GetMapping("/")
	public String showHomePage(Model model) {

		model.addAttribute("sectors", sectorsService.getAllSectors());
		return "index";

	}
}