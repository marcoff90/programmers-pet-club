package com.greenfox.programmerspetclub.models.pet;

import java.util.Random;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public abstract class Pet {

  @Id
  protected String name;
  protected String food;
  protected String drink;
  protected String description;
  protected String image;
  protected boolean isFoodUpdated;
  protected boolean isTricksUpdated;
  protected boolean isCreated;
  protected Random rand;

  public Pet(String name, String food, String drink) {
    this.name = name;
    this.food = food;
    this.drink = drink;
    this.isTricksUpdated = false;
    this.isFoodUpdated = false;
    this.isCreated = false;
    this.rand = new Random();
  }

  public Pet() {
    this.isTricksUpdated = false;
    this.isFoodUpdated = false;
    this.isCreated = false;
    this.rand = new Random();
  }

  public String getName() {
    return name.substring(0, 1).toUpperCase() + name.substring(1);
  }

}
