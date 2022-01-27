package com.greenfox.programmerspetclub.models.pet;

import java.util.Random;

public class Paegas extends Pet {

  public Paegas(String name, String food, String drink) {
    super(name, food, drink);
    this.description = "If you wanna fly away from all your problems, " + name + " is here for you! "
        + "Jusst jump up and let him take you to the wonderland!";
    this.image = chooseImg();
  }

  private String chooseImg() {
    String[] pics = {"src/main/resources/static/img/IMG_0662.JPG", "src/main/resources/static/img/IMG_0663.JPG",
        "src/main/resources/static/img/IMG_0664.JPG", "src/main/resources/static/img/IMG_0665.JPG"};
    Random rand = new Random();
    return pics[rand.nextInt(pics.length)];
  }
}
