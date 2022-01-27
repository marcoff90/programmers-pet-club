package com.greenfox.programmerspetclub.models.pet;

import java.util.Random;

public class Unicorn extends Pet {

  public Unicorn(String name, String food, String drink) {
    super(name, food, drink);
    this.description = name + " is the cutest of them all! Look at those fabulous colors!";
    this.image = chooseImg();
  }

  private String chooseImg() {
    String[] pics = {"src/main/resources/static/img/IMG_0653.JPG", "src/main/resources/static/img/IMG_0654.JPG",
        "src/main/resources/static/img/IMG_0655.JPG", "src/main/resources/static/img/IMG_0656.JPG"};
    Random rand = new Random();
    return pics[rand.nextInt(pics.length)];
  }
}
