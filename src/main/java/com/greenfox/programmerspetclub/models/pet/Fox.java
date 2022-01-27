package com.greenfox.programmerspetclub.models.pet;

import java.util.Random;

public class Fox extends Pet {

  public Fox(String name, String food, String drink) {
    super(name, food, drink);
    this.description = name + " is a smart little foxie. She's fearless explorer and "
        + "is looking forward to learn as many tricks as possible!";
    this.image = chooseImg();
  }

  private String chooseImg() {
    String[] pics = {"src/main/resources/static/img/IMG_0672.JPG", "src/main/resources/static/img/IMG_0673.JPG",
        "src/main/resources/static/img/IMG_0674.JPG", "src/main/resources/static/img/IMG_0675.JPG"};
    Random rand = new Random();
    return pics[rand.nextInt(pics.length)];
  }
}
