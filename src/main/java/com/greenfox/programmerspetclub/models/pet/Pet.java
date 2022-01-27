package com.greenfox.programmerspetclub.models.pet;

import java.util.ArrayList;
import java.util.List;

public abstract class Pet {

  protected String name;
  protected String food;
  protected String drink;
  protected String description;
  protected List<String> tricks;
  protected String image;

  public Pet(String name, String food, String drink) {
    this.name = name;
    this.food = food;
    this.drink = drink;
    this.tricks = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public String getFood() {
    return food;
  }

  public String getDrink() {
    return drink;
  }

  public String getDescription() {
    return description;
  }

  public String getImage() {
    return image;
  }

  public List<String> getTricks() {
    return tricks;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setFood(String food) {
    this.food = food;
  }

  public void setDrink(String drink) {
    this.drink = drink;
  }

  public void addTrick(String newTrick) {
    tricks.add(newTrick);
  }
}
