package com.greenfox.programmerspetclub.enums;

public enum TypeOfPet {

  CUTE_FOX("Cute Fox"),
  CUTE_WOLF("Cute Wolf"),
  CUTE_DOGGO("Cute Doggo"),
  CUTE_UNICORN("Cute Unicorn"),
  PAEGAS_UNICORN("Paegas Unicorn");

  public final String label;

  private TypeOfPet(String label) {
    this.label = label;
  }
}
