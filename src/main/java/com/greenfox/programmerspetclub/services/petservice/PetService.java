package com.greenfox.programmerspetclub.services.petservice;

import com.greenfox.programmerspetclub.models.pet.Pet;
import java.util.List;

public interface PetService {

  void setCurrentPet(Pet pet);

  Pet getCurrentPet();

  List<Pet> getPets();

  void addPet(String type, String food, String drink, String name);

  void updateFoodAndDrink(String drink, String food);

  void updateTricks(String newTrick);

  boolean isInDatabase(String name);

  void resetBooleans();

  Pet matchingPet(String name);

}
