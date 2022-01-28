package com.greenfox.programmerspetclub.models.pet;

public class Unicorn extends Pet {

  public Unicorn(String name, String food, String drink) {
    super(name, food, drink);
    this.description = name + " is the cutest of them all! Look at those fabulous colors!";
    this.image = chooseImg();
  }

  private String chooseImg() {
    String[] pics = {"IMG_0653.JPG", "IMG_0654.JPG", "IMG_0655.JPG", "IMG_0656.JPG"};
    return pics[rand.nextInt(pics.length)];
  }
}
