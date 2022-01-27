package com.greenfox.programmerspetclub.models.pet;

import java.util.Random;

public class Doggo extends Pet {

  public Doggo(String name, String food, String drink) {
    super(name, food, drink);
    this.description = name + " is a good boi, he loves to play fetch, eat his " + food +
        " and stare at his owner while he's coding this page!";
    this.image = chooseImg();
  }

  private String chooseImg() {
    String[] pics = {"src/main/resources/static/img/IMG_0657.JPG", "src/main/resources/static/img/IMG_0658.JPG",
        "src/main/resources/static/img/IMG_0659.JPG", "src/main/resources/static/img/IMG_0660.JPG",
        "src/main/resources/static/img/IMG_0661.JPG"};
    Random rand = new Random();
    return pics[rand.nextInt(pics.length)];
  }
}
