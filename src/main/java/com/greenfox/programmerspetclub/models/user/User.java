package com.greenfox.programmerspetclub.models.user;

import lombok.Data;

@Data
public class User {

  private boolean isLoggedIn;
  private boolean isRedirected;

  public User() {
    this.isLoggedIn = false;
    this.isRedirected = false;
  }
}
