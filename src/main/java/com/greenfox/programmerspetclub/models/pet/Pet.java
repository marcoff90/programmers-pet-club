package com.greenfox.programmerspetclub.models.pet;

import java.util.ArrayList;
import java.util.List;

public abstract class Pet {

  protected String name;
  protected String food;
  protected String drink;
  protected String description;
  protected List<String> tricks;
  protected List<String> history;
  protected String image;
  private boolean isFoodUpdated;
  private boolean isTricksUpdated;
  private boolean isCreated;
  private boolean hasntBeenFound;

  public Pet(String name, String food, String drink) {
    this.name = name;
    this.food = food;
    this.drink = drink;
    this.tricks = new ArrayList<>();
    this.history = new ArrayList<>();
    this.hasntBeenFound = false;
    this.isTricksUpdated = false;
    this.isFoodUpdated = false;
    this.isCreated = false;
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

  public List<String> getHistory() {
    if (history.isEmpty()) {
      history.add(name + " doesn't have any history yet");
    }
    return history.size() > 1 ? history.subList(1, history.size()) : history;
  }

  public List<String> getTricks() {
    if (tricks.isEmpty()) {
      tricks.add(name + " doesn't know any tricks yet");
    }
    return tricks.size() > 1 ? tricks.subList(1, tricks.size()) : tricks;
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

  public void addHistory(String history) {
    this.history.add(history);
  }

  public boolean isFoodUpdated() {
    return this.isFoodUpdated;
  }

  public boolean isTricksUpdated() {
    return this.isTricksUpdated;
  }

  public boolean isCreated() {
    return this.isCreated;
  }

  public boolean hasntBeenFound() {
    return this.hasntBeenFound;
  }

  public void setFoodUpdated(boolean isFoodUpdated) {
    this.isFoodUpdated = isFoodUpdated;
  }

  public void setTricksUpdated(boolean isTricksUpdated) {
    this.isTricksUpdated = isTricksUpdated;
  }

  public void setCreated(boolean isCreated) {
    this.isCreated = isCreated;
  }

  public void setHasntBeenFound(boolean hasntBeenFound) {
    this.hasntBeenFound = hasntBeenFound;
  }
}
