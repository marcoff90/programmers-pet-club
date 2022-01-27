package com.greenfox.programmerspetclub.controllers;

import com.greenfox.programmerspetclub.models.pet.Pet;
import com.greenfox.programmerspetclub.services.PetService;
import java.util.Optional;
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

    shouldUserSeeTheMenu(model);
    return "home";

    // * once the user is successfully  logged in, the petservice has the current pet saved in memory
    // * and then when user goes back to homepage, he see's all the options from the menu, if he's not logged
    // * in he can only see option to Create a new pet and go back to the home page by clicking programmers pet club
  }

  @PostMapping("/home")
  public String login(@RequestParam String name) {
    // TODO send boolean to redirect to create in order to show the alert pet not found
    if (petService.isInDatabase(name)) {
      return "redirect:information?name=" + name;
    } else {
      return "redirect:create";
    }

    // * if the name is found in the database, the user is redirected to information page of his pet
    // * if not user is redirected to create a pet page
  }

  @GetMapping("/information")
  public String showInformation(Model model, @RequestParam(required = false) String name) {

    //TODO alerts booleans, if already logged in and wanna log in with another pet, set new pet in petservice
    if (petService.getCurrentPet() == null) {
      Optional<Pet> matchingPet = petService.getPets().stream().filter(pet -> pet.getName().equalsIgnoreCase(name)).findFirst();
      Pet pet = matchingPet.orElse(null);
      model.addAttribute("pet", pet);
      petService.setCurrentPet(pet);

      // * after first login the pet is set as a current pet which is the called through pet service
      // * in other methods and that way can easily be always rendered

    } else {
      model.addAttribute("pet", petService.getCurrentPet());
    }
    return "information";
  }

  @GetMapping("/create")
  public String showCreate(Model model) {
    shouldUserSeeTheMenu(model);
    return "create";

    // * if the user isn't logged in he doesn't see whole menu
  }

  @GetMapping("/history")
  public String showHistory(Model model) {
    model.addAttribute("pet", petService.getCurrentPet());
    return "history";
  }

  @GetMapping("/nutritioncenter")
  public String showNutrition(Model model) {
    model.addAttribute("pet", petService.getCurrentPet());
    return "nutritionstore";
  }

  @PostMapping("/nutritioncenter")
  public String updateNutrition(Model model, @RequestParam String food, @RequestParam String drink) {
    petService.getCurrentPet().setDrink(drink);
    petService.getCurrentPet().setFood(food);
    return "redirect:information";
  }

  @GetMapping("/tricks")
  public String showTricks(Model model) {
    model.addAttribute("pet", petService.getCurrentPet());
    return "tricks";
  }

  private Model shouldUserSeeTheMenu(Model model) {
    if (petService.getCurrentPet() != null) {
      model.addAttribute("isInDatabase", petService.isInDatabase(petService.getCurrentPet().getName()));
    } else {
      model.addAttribute("isInDatabase", petService.isInDatabase(null));
    }

    return model;
  }

}
