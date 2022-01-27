package com.greenfox.programmerspetclub.controllers;

import com.greenfox.programmerspetclub.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PetClubController {

  private PetService petService;

  @Autowired
  public PetClubController(PetService petService) {
    this.petService = petService;
  }

  @GetMapping("/home")
  public String index(Model model) {
    model.addAttribute("isInDatabase", petService.isInDatabase(null));
    return "home";
  }

  @PostMapping("/home")
  public String login(@RequestParam String name) {
    if (petService.isInDatabase(name)) {
      return "redirect:information";
    } else {
      return "redirect:create";
    }

  }

  @GetMapping("/information")
  public String showInformation() {
    return "information";
  }

  @GetMapping("/create")
  public String showCreate() {
    return "create";
  }
}
