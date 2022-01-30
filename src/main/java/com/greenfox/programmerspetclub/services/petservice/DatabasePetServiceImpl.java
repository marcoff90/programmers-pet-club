package com.greenfox.programmerspetclub.services.petservice;

import com.greenfox.programmerspetclub.models.pet.Doggo;
import com.greenfox.programmerspetclub.models.pet.Fox;
import com.greenfox.programmerspetclub.models.pet.Paegas;
import com.greenfox.programmerspetclub.models.pet.Pet;
import com.greenfox.programmerspetclub.models.pet.Unicorn;
import com.greenfox.programmerspetclub.models.pet.Wolf;
import com.greenfox.programmerspetclub.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabasePetServiceImpl implements PetService {


  private PetRepository petRepository;
  private Pet currentPet;

  @Autowired
  public DatabasePetServiceImpl(
      PetRepository petRepository) {
    this.petRepository = petRepository;
  }

  @Override
  public Pet getCurrentPet() {
    return this.currentPet;
  }

  @Override
  public void addPet(String type, String food, String drink, String name) {
    Pet pet = null;
    String nameWithUpperCase = name.substring(0, 1).toUpperCase() + name.substring(1);
    switch (type) {
      case "Cute Fox":
        pet = new Fox(nameWithUpperCase, food, drink);
        break;
      case "Cute Wolf":
        pet = new Wolf(nameWithUpperCase, food, drink);
        break;
      case "Cute Doggo":
        pet = new Doggo(nameWithUpperCase, food, drink);
        break;
      case "Cute Unicorn":
        pet = new Unicorn(nameWithUpperCase, food, drink);
        break;
      case "Paegas Unicorn":
        pet = new Paegas(nameWithUpperCase, food, drink);
        break;
    }
    petRepository.save(pet);
    this.currentPet = pet;
    // * based on passed String type, the pet type is created, added to the list and set as a current pet -> when redirected to infomartion, the new
    // * pet is displayed
  }

  @Override
  public void updateFoodAndDrink(String drink, String food) {
    currentPet.setDrink(drink);
    currentPet.setFood(food);
    currentPet.setFoodUpdated(true);
    petRepository.save(currentPet);
  }

  @Override
  public boolean isInDatabase(String name) {
    return name != null && petRepository.findById(name).isPresent();
  }

  @Override
  public void resetBooleans() {
    currentPet.setCreated(false);
    currentPet.setTricksUpdated(false);
    currentPet.setFoodUpdated(false);
    petRepository.save(currentPet);
  }

  @Override
  public Pet matchingPet(String name) {
    currentPet = petRepository.findById(name).orElse(null);
    return petRepository.findById(name).orElse(null);
  }
}
