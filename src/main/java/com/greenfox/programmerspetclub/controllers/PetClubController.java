package com.greenfox.programmerspetclub.controllers;

import com.greenfox.programmerspetclub.services.historyservice.HistoryService;
import com.greenfox.programmerspetclub.services.petservice.PetService;
import com.greenfox.programmerspetclub.services.trickservice.TrickService;
import com.greenfox.programmerspetclub.services.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PetClubController {

  // TODO
  // * when tricks are learned check, if it's already in the database under the pets name, if yes, don't add, show alert
  // * don't add to history too

  private PetService petService;
  private UserService userService;
  private TrickService trickService;
  private HistoryService historyService;

  @Autowired
  public PetClubController(PetService petService, UserService userService, TrickService trickService, HistoryService historyService) {
    this.petService = petService;
    this.userService = userService;
    this.trickService = trickService;
    this.historyService = historyService;
  }

  @GetMapping("/home")
  public String index(Model model) {
    shouldUserSeeTheMenu(model);
    // * if user is logged in, he sees the whole menu, when not he sees only home and create
    model.addAttribute("wasUserRedirected", userService.getUser().isRedirected());
    // * if the user has been redirected, he does get the alert
    return "home";
  }

  @PostMapping("/home")
  public String login(@RequestParam String name) {
    if (petService.isInDatabase(name)) {
      if (petService.getCurrentPet() != null) {
        petService.matchingPet(name);
        // * if user is already logged in and is logging in with another pet, we overwrite the current pet for the new one
      }
      userService.getUser().setLoggedIn(true);
      // * user is set to logged in and when requesting other endpoints, he gets the correct view
      return "redirect:information?name=" + name;
      // * if the name is found in the database, the user is redirected to information page of his pet, where this pet is set as a current pet
    } else {
      return "redirect:create?name=" + name;
      // * if not user is redirected to create a pet page
    }
  }

  @GetMapping("/information")
  public String showInformation(Model model, @RequestParam(required = false) String name) {
    if (userService.getUser().isLoggedIn()) {
      if (petService.getCurrentPet() == null) {
        model.addAttribute("pet", petService.matchingPet(name));
        model.addAttribute("tricks", trickService.getTricks(petService.getCurrentPet().getName()));

        // * after first login the pet is set as a current pet which is the called through pet service
        // * in other methods and that way can easily be always rendered
      } else {
        model.addAttribute("pet", petService.getCurrentPet());
        model.addAttribute("tricks", trickService.getTricks(petService.getCurrentPet().getName()));
      }
      return "information";
    } else {
      userService.getUser().setRedirected(true);
      return "redirect:home";
    }
    // * if for some reason would not logged-in user enter the url for information, it takes him to the home page
    // * similar logic used in other "hidden" views for not logged-in users
  }

  @GetMapping("/create")
  public String showCreate(Model model, @RequestParam(required = false) String name, @RequestParam(required = false) String wrongName) {

    if (wrongName != null) {
      model.addAttribute("isWrongName", true);
      model.addAttribute("name", "What will be the name of your amazing pet?");
      model.addAttribute("wrongName", firstLetterToUpperCase(wrongName));
      userService.getUser().setLoggedIn(false);
      // * wrongName redirected after submitting create form with name which is already in database, displays alert
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
      userService.getUser().setLoggedIn(false);
      // * when redirected from home based on name not being in database, user is no longer logged in and requests for other endpoints are redirected
      // * to home page
    }

    if (name != null) {
      model.addAttribute("name", firstLetterToUpperCase(name));
      // * when the user has entered a name not known in the database, the name is then showed as a placeholder in the form
    }
    userService.getUser().setRedirected(false);
    return "create";
  }

  @PostMapping("/create")
  public String store(@RequestParam String name, @RequestParam String type, @RequestParam String food, @RequestParam String drink) {
    if (!petService.isInDatabase(name)) {
      petService.addPet(type, food, drink, name); // * creates a new pet, adds to the list, changes the current pet to the newly created
      petService.getCurrentPet().setCreated(true); // * for alert showing
      userService.getUser().setLoggedIn(true);
      return "redirect:information";
    } else {
      return "redirect:create?wrongName=" + name;
      // * if the pet is already in database, redirects back to create and displays alert
    }
  }

  @GetMapping("/history")
  public String showHistory(Model model) {
    if (userService.getUser().isLoggedIn()) {
      addPetToModel(model);
      model.addAttribute("history", historyService.getHistory(petService.getCurrentPet().getName()));
      return "history";
    } else {
      userService.getUser().setRedirected(true);
      return "redirect:home";
    }
  }

  @GetMapping("/nutritioncenter")
  public String showNutrition(Model model) {
    if (userService.getUser().isLoggedIn()) {
      addPetToModel(model);
      return "nutritionstore";
    } else {
      userService.getUser().setRedirected(true);
      return "redirect:home";
    }
  }

  @PostMapping("/nutritioncenter")
  public String updateNutrition(@RequestParam String food, @RequestParam String drink) {
    petService.updateFoodAndDrink(drink, food);
    historyService.addHistoryOfFood(petService.getCurrentPet().getName(), food, drink);
    // * updates pet in database, adds history to database
    return "redirect:information";
  }

  @GetMapping("/tricks")
  public String showTricks(Model model) {
    if (userService.getUser().isLoggedIn()) {
      addPetToModel(model);
      return "tricks";
    } else {
      userService.getUser().setRedirected(true);
      return "redirect:home";
    }
  }

  @PostMapping("/tricks")
  public String updateTrick(@RequestParam String newTrick) {
    trickService.addTrick(petService.getCurrentPet().getName(), newTrick);
    petService.getCurrentPet().setTricksUpdated(true);
    historyService.addHistoryOfTricks(petService.getCurrentPet().getName(), newTrick);
    // * adds trick to the database, sets condition for alert to true, adds history to database
    return "redirect:information";
  }

  private Model shouldUserSeeTheMenu(Model model) {
    model.addAttribute("isInDatabase", userService.getUser().isLoggedIn());
    // * if the user is logged in, the tricks, history, information and nutritionstore in menu bar are visible
    return model;
  }

  private String firstLetterToUpperCase(String name) {
    return name.substring(0, 1).toUpperCase() + name.substring(1);
  }

  private Model addPetToModel(Model model) {
    model.addAttribute("pet", petService.getCurrentPet());
    petService.resetBooleans();
    userService.getUser().setRedirected(false);
    return model;
  }
}
