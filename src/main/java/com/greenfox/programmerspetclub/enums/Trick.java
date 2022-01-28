package com.greenfox.programmerspetclub.enums;

public enum Trick {

  CODE_WEB_APPS("Code web apps"),
  HYPE_SELF_WITH_TONS_OF_COFFEE("Hype self with tons of coffee"),
  HACK_INTO_NASA("Hack into NASA"),
  FLY_TO_THE_MOON("Fly to the moon"),
  DANCE_WITH_THE_STARS("Dance with the stars"),
  BE_INFLUENCER("Be Influencer");

  public final String label;

  private Trick(String label) {
    this.label = label;
  }
}
