package com.greenfox.programmerspetclub.models.user;

import lombok.Data;

@Data
public class User {

  private boolean isLoggedIn;

  public User() {
    this.isLoggedIn = false;
  }
}
