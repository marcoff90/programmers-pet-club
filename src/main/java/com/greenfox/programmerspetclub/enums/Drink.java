package com.greenfox.programmerspetclub.enums;

public enum Drink {

  COFFEE("Coffee"),
  COLA("Coca Cola"),
  VODKA("Vodka"),
  TEA("Tea"),
  WHISKEY("Whiskey"),
  WATER("Water");

  public final String label;

  private Drink(String label) {
    this.label = label;
  }
}
