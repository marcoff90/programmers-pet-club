package com.greenfox.programmerspetclub.services;

import com.greenfox.programmerspetclub.models.pet.Pet;
import java.util.List;

public interface PetService {

  public void setCurrentPet(Pet pet);
  public Pet getCurrentPet();
  public List<Pet> getPets();
  public boolean addPet(String type, String food, String drink, String name);  // adds Pet to the list, returns boolean for alert (created)
  public boolean isInDatabase(String name); // returns boolean if the pet was found for redirecting and showing menu


}
