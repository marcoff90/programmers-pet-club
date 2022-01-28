package com.greenfox.programmerspetclub.enums;

public enum Food {

  PIZZA("Pizza"),
  HAMBURGER("Hamburger"),
  CHINEESE("Chineese"),
  PADTHAI("Pad Thai"),
  SPINACH("Spinach"),
  AIR("Air");

  public final String label;

  private Food(String label) {
    this.label = label;
  }
}
