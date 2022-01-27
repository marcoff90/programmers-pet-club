package com.greenfox.programmerspetclub.services;

import com.greenfox.programmerspetclub.models.pet.Doggo;
import com.greenfox.programmerspetclub.models.pet.Pet;
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
  public boolean addPet(Pet pet) {
    pets.add(pet);
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
