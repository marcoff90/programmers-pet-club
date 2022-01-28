package com.greenfox.programmerspetclub.services;

import com.greenfox.programmerspetclub.models.pet.Doggo;
import com.greenfox.programmerspetclub.models.pet.Fox;
import com.greenfox.programmerspetclub.models.pet.Paegas;
import com.greenfox.programmerspetclub.models.pet.Pet;
import com.greenfox.programmerspetclub.models.pet.Unicorn;
import com.greenfox.programmerspetclub.models.pet.Wolf;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements PetService {

  private List<Pet> pets;
  private Pet currentPet;

  public PetServiceImpl() {
    this.pets = new ArrayList<>();
    pets.add(new Doggo("Lucas", "Ham", "Cola"));
  }

  @Override
  public void setCurrentPet(Pet pet) {
    this.currentPet = pet;
  }

  @Override
  public Pet getCurrentPet() {
    return currentPet;
  }

  public List<Pet> getPets() {
    return pets;
  }

  @Override
  public void addPet(String type, String food, String drink, String name) {
    Pet pet = null;
    String nameWithUpperCase = name.substring(0, 1).toUpperCase() + name.substring(1);
    switch (type) {
      case "Cute Fox" :
        pet = new Fox(nameWithUpperCase, food, drink);
        break;
      case "Cute Wolf" : pet = new Wolf(nameWithUpperCase, food, drink);
        break;
      case "Cute Doggo" :
        pet = new Doggo(nameWithUpperCase, food, drink);
        break;
      case "Cute Unicorn" :
        pet = new Unicorn(nameWithUpperCase, food, drink);
        break;
      case "Paegas Unicorn" :
        pet = new Paegas(nameWithUpperCase, food, drink);
        break;
    }
    pets.add(pet);
    this.currentPet = pet;
    // * based on passed String type, the pet type is created, added to the list and set as a current pet -> when redirected to infomartion, the new
    // * pet is displayed
  }

  @Override
  public void updateFoodAndDrink(String drink, String food) {
    currentPet.setDrink(drink);
    currentPet.setFood(food);
    currentPet.setFoodUpdated(true);
    currentPet.addHistory(getTimeAndDate() + " " + currentPet.getName() +
        " changed his food & drink into " + food + " & " + drink);
  }

  @Override
  public void updateTricks(String newTrick) {
    currentPet.addTrick(newTrick);
    currentPet.setTricksUpdated(true);
    currentPet.addHistory(getTimeAndDate() + " " + currentPet.getName() +
        " has learned to " + newTrick);
  }

  @Override
  public boolean isInDatabase(String name) {
    if (name == null) {
      return false;
    }
    return pets.stream().anyMatch(pet -> pet.getName().equalsIgnoreCase(name));
  }

  @Override
  public void resetBooleans() {
    currentPet.setCreated(false);
    currentPet.setTricksUpdated(false);
    currentPet.setFoodUpdated(false);
  }

  @Override
  public Pet matchingPet(String name) {
    Optional<Pet> matchingPet = pets.stream().filter(pet -> pet.getName().equalsIgnoreCase(name)).findFirst();
    currentPet = matchingPet.orElse(null);
    return matchingPet.orElse(null);
    // * when the user is logging in with pet, it checks if the pet is in the list and then sets it as a current pet
  }

  private String getTimeAndDate() {
    String date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    return date.substring(6, 8) + "." + date.substring(5, 7) + "." + date.substring(0, 4) + " at " + date.substring(9, 11) + ":" + date.substring(
        11, 13);
  }
}
