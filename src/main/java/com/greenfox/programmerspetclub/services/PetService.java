package com.greenfox.programmerspetclub.services;

import com.greenfox.programmerspetclub.models.pet.Pet;
import java.util.List;

public interface PetService {

  public void setCurrentPet(Pet pet);

  public Pet getCurrentPet();

  public List<Pet> getPets();

  public void addPet(String type, String food, String drink, String name);

  public void updateFoodAndDrink(String drink, String food);

  public void updateTricks(String newTrick);

  public boolean isInDatabase(String name); // returns boolean if the pet was found for redirecting and showing menu

  public void resetBooleans();

  public Pet matchingPet(String name);


}
