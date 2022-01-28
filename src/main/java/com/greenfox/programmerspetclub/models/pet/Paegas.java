package com.greenfox.programmerspetclub.models.pet;

public class Paegas extends Pet {

  public Paegas(String name, String food, String drink) {
    super(name, food, drink);
    this.description = "If you wanna fly away from all your problems, " + name + " is here for you! "
        + "Just jump up and let him take you to the wonderland!";
    this.image = chooseImg();
  }

  private String chooseImg() {
    String[] pics = {"IMG_0662.JPG", "IMG_0663.JPG", "IMG_0664.JPG", "IMG_0665.JPG"};
    return pics[rand.nextInt(pics.length)];
  }
}
