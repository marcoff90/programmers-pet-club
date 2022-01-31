package com.greenfox.programmerspetclub.models.trick;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Trick {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private String description;

  public Trick(String name, String description) {
    this.description = description;
    this.name = name;
  }

  public Trick() {
  }

  @Override
  public String toString() {
    return description;
  }
}
