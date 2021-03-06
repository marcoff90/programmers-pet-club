package com.greenfox.programmerspetclub.models.pet;

import javax.persistence.Entity;

@Entity
public class Fox extends Pet {

  public Fox(String name, String food, String drink) {
    super(name, food, drink);
    this.description = name + " is a smart little foxie. She's fearless explorer and "
        + "is looking forward to learn as many tricks as possible!";
    this.image = chooseImg();
  }

  public Fox() {
    this.description = name + " is a smart little foxie. She's fearless explorer and "
        + "is looking forward to learn as many tricks as possible!";
    this.image = chooseImg();
  }

  private String chooseImg() {
    String[] pics = {"IMG_0672.JPG", "IMG_0673.JPG", "IMG_0674.JPG", "IMG_0675.JPG"};
    return pics[rand.nextInt(pics.length)];
  }
}
