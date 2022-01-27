package com.greenfox.programmerspetclub.models.pet;

import java.util.Random;

public class Wolf extends Pet {

  public Wolf(String name, String food, String drink) {
    super(name, food, drink);
    this.description = "This wolf is a smart, fearless, independent boi! He might be here and be gone in a second!";
    this.image = chooseImg();
  }

  private String chooseImg() {
    String[] pics = {"IMG_0666.JPG", "IMG_0667.JPG",
        "IMG_0668.JPG", "IMG_0669.JPG",
        "IMG_0670.JPG"};
    Random rand = new Random();
    return pics[rand.nextInt(pics.length)];
  }
}
