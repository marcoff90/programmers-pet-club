package com.greenfox.programmerspetclub.services;

import com.greenfox.programmerspetclub.models.pet.Doggo;
import com.greenfox.programmerspetclub.models.pet.Fox;
import com.greenfox.programmerspetclub.models.pet.Paegas;
import com.greenfox.programmerspetclub.models.pet.Pet;
import com.greenfox.programmerspetclub.models.pet.Unicorn;
import com.greenfox.programmerspetclub.models.pet.Wolf;
import java.util.ArrayList;
import java.util.List;
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
  public boolean addPet(String type, String food, String drink, String name) {
    Pet pet = null;
    switch (type) {
      case "Cute Fox" :
        pet = new Fox(name, food, drink);
        break;
      case "Cute Wolf" : pet = new Wolf(name, food, drink);
        break;
      case "Cute Doggo" :
        pet = new Doggo(name, food, drink);
        break;
      case "Cute Unicorn" :
        pet = new Unicorn(name, food, drink);
        break;
      case "Paegas Unicorn" :
        pet = new Paegas(name, food, drink);
        break;
    }
    pets.add(pet);
    this.currentPet = pet;
    return true;
  }

  @Override
  public boolean isInDatabase(String name) {
    if (name == null) {
      return false;
    }

    return pets.stream().anyMatch(pet -> pet.getName().equalsIgnoreCase(name));
  }
}
