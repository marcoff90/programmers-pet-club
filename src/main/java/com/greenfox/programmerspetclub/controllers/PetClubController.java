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
    shouldUserSeeTheMenu(model);
    return "home";

    // * once the user is successfully  logged in, the petservice has the current pet saved in memory
    // * and then when user goes back to homepage, he see's all the options from the menu, if he's not logged
    // * in he can only see option to Create a new pet and go back to the home page by clicking programmers pet club
  }

  @PostMapping("/home")
  public String login(@RequestParam String name) {
    if (petService.isInDatabase(name)) {
      if (petService.getCurrentPet() != null) {
        petService.matchingPet(name);
        // * if user is already logged in and is logging in with another pet, we overwrite the current pet for the new one
      }
      return "redirect:information?name=" + name;
      // * if the name is found in the database, the user is redirected to information page of his pet
    } else {
      return "redirect:create?name=" + name;
      // * if not user is redirected to create a pet page
    }
  }

  @GetMapping("/information")
  public String showInformation(Model model, @RequestParam(required = false) String name) {
    if (petService.getCurrentPet() == null) {
      model.addAttribute("pet", petService.matchingPet(name));
      // * after first login the pet is set as a current pet which is the called through pet service
      // * in other methods and that way can easily be always rendered
    } else {
      model.addAttribute("pet", petService.getCurrentPet());
    }
    return "information";
    // * if for some reason would not logged-in user enter the url for information, it takes him to the home page
    // * similar logic used in other "hidden" views for not logged-in users
  }

  @GetMapping("/create")
  public String showCreate(Model model, @RequestParam(required = false) String name, @RequestParam(required = false) String wrongName) {

    if (wrongName != null) {
      model.addAttribute("isWrongName", true);
      model.addAttribute("name", "What will be the name of your amazing pet?");
      // * wrongName redirected after submitting create form with name already in database, displays alert
    }

    if (name == null) {
      model.addAttribute("isAlertVisible", false);
      model.addAttribute("name", "What will be the name of your amazing pet?");
      // * when the name isn't passed, the alert isn't visible, the placeholder in the form shows generic message
      shouldUserSeeTheMenu(model);
      // * if the user isn't logged in he doesn't see whole menu
    } else if (!petService.isInDatabase(name)) {
      model.addAttribute("isAlertVisible", true);
      // * when the name is passed and is wrong, the alert is visible
      model.addAttribute("isInDatabase", petService.isInDatabase(null));
      // * when the user is already logged in and trying to log in with another pet, but enters a wrong name, the alert shows
      // * up and the menu is no longer visible
    }


    if (name != null) {
      String nameWithUpperCase = name.substring(0, 1).toUpperCase() + name.substring(1);
      model.addAttribute("name", nameWithUpperCase);
      // * when the user has entered a name not known in the database, the name is then showed as a placeholder in the form
    }
    return "create";
  }

  @PostMapping("/create")
  public String store(Model model, @RequestParam String name, @RequestParam String type,
      @RequestParam String food, @RequestParam String drink) {
    if (!petService.isInDatabase(name)) {
      petService.addPet(type, food, drink,
          name); // * creates a new pet, adds to the list, changes the current pet to the newly created
      petService.getCurrentPet().setCreated(true); // * for alert showing
      return "redirect:information";
    } else {
      return "redirect:create?wrongName=" + name;
    }
  }

  @GetMapping("/history")
  public String showHistory(Model model) {
    model.addAttribute("pet", petService.getCurrentPet());
    petService.resetBooleans(); // * resetting booleans for alerts
    return "history";
  }

  @GetMapping("/nutritioncenter")
  public String showNutrition(Model model) {
    model.addAttribute("pet", petService.getCurrentPet());
    petService.resetBooleans();
    return "nutritionstore";
  }

  @PostMapping("/nutritioncenter")
  public String updateNutrition(Model model, @RequestParam String food,
      @RequestParam String drink) {
    petService.updateFoodAndDrink(drink, food);
    return "redirect:information";
  }

  @GetMapping("/tricks")
  public String showTricks(Model model) {
    model.addAttribute("pet", petService.getCurrentPet());
    petService.resetBooleans();
    return "tricks";
  }

  @PostMapping("/tricks")
  public String updateTrick(Model model, @RequestParam String newTrick) {
    petService.updateTricks(newTrick);
    return "redirect:information";
  }

  private Model shouldUserSeeTheMenu(Model model) {
    if (petService.getCurrentPet() != null) {
      model.addAttribute("isInDatabase",
          petService.isInDatabase(petService.getCurrentPet().getName()));
    } else {
      model.addAttribute("isInDatabase", petService.isInDatabase(null));
    }
    return model;
  }
}
