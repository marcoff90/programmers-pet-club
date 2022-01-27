package com.greenfox.programmerspetclub.services;

import com.greenfox.programmerspetclub.models.pet.Pet;
import java.util.List;
import org.springframework.stereotype.Service;

public interface PetService {

  public void setCurrentPet(Pet pet);
  public Pet getCurrentPet();
  public List<Pet> getPets();
  public boolean addPet(Pet pet);  // adds Pet to the list, returns boolean for alert (created)
  public boolean updateFoodAndDrink(Pet pet); // updates pet either food and drink, returns boolean for alert
  public boolean updateTricks(Pet pet); // updates tricks, returns boolean for alert
  public boolean isInDatabase(String name); // returns boolean if the pet was found for redirecting and showing menu


}
