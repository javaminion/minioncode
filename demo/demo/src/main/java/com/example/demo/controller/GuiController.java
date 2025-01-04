package com.example.demo.controller;

import com.example.demo.service.GitHubService;
import com.example.demo.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.security.core.Authentication;

@Controller
public class GuiController {
    private final PersonService personService;
    private final GitHubService gitHubService;

    public GuiController(PersonService personService, GitHubService gitHubService) {
        this.personService = personService;
        this.gitHubService = gitHubService;
    }

	/*
	 * @GetMapping("/persons") public String getPersons(Model model, Authentication
	 * authentication) { model.addAttribute("user",
	 * gitHubService.getGitHubUserName(authentication));
	 * //model.addAttribute("persons", personService.getAllPersons()); return
	 * "persons"; }
	 */
    
    
}
