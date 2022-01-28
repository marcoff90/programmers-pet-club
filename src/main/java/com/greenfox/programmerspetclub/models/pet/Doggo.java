package com.greenfox.programmerspetclub.models.pet;

public class Doggo extends Pet {

  public Doggo(String name, String food, String drink) {
    super(name, food, drink);
    this.description = name + " is a good boi, he loves to play fetch, eat his " + food +
        " and stare at his owner while he's coding this page!";
    this.image = chooseImg();
  }

  private String chooseImg() {
    String[] pics = {"IMG_0657.JPG", "IMG_0658.JPG", "IMG_0659.JPG", "IMG_0660.JPG", "IMG_0661.JPG"};
    return pics[rand.nextInt(pics.length)];
  }
}
