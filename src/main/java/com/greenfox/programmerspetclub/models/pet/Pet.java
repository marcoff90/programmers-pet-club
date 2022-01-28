package com.greenfox.programmerspetclub.models.pet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Data;

@Data
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

  public Pet(String name, String food, String drink) {
    this.name = name;
    this.food = food;
    this.drink = drink;
    this.tricks = new ArrayList<>();
    this.history = new ArrayList<>();
    this.isTricksUpdated = false;
    this.isFoodUpdated = false;
    this.isCreated = false;
  }

  public List<String> getHistory() {
    if (history.isEmpty()) {
      return Arrays.asList(name + " doesn't have any history yet");
    }
    return history;
  }

  public List<String> getTricks() {
    if (tricks.isEmpty()) {
      tricks.add(name + " doesn't know any tricks yet");
    }
    return tricks.size() > 1 ? tricks.subList(1, tricks.size()) : tricks;
  }

  public void addTrick(String newTrick) {
    tricks.add(newTrick);
  }

  public void addHistory(String history) {
    this.history.add(history);
  }
}
