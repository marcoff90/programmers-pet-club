package com.greenfox.programmerspetclub.models.history;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class History {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private String description;

  public History(String name, String description) {
    this.description = description;
    this.name = name;
  }

  public History() {

  }

  @Override
  public String toString() {
    return description;
  }
}
