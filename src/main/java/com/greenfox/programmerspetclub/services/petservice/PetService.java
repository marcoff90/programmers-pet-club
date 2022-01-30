package com.greenfox.programmerspetclub.services.petservice;

import com.greenfox.programmerspetclub.models.pet.Pet;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface PetService {

  Pet getCurrentPet();

  void addPet(String type, String food, String drink, String name);

  void updateFoodAndDrink(String drink, String food);

  boolean isInDatabase(String name);

  void resetBooleans();

  Pet matchingPet(String name);

}
