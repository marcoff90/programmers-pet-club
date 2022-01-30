package com.greenfox.programmerspetclub.services.trickservice;

import com.greenfox.programmerspetclub.models.trick.Trick;
import com.greenfox.programmerspetclub.repositories.TrickRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrickServiceImpl implements TrickService {

  private TrickRepository trickRepository;

  @Autowired
  public TrickServiceImpl(
      TrickRepository trickRepository) {
    this.trickRepository = trickRepository;
  }

  @Override
  public void addTrick(String name, String trick) {
    trickRepository.save(new Trick(name, trick));
  }

  @Override
  public List<Trick> getTricks(String name) {
    return loadTricks(name).isEmpty() ? Arrays.asList(
        new Trick(name, name + " doesn't know any tricks yet!")) : loadTricks(name);
  }

  private List<Trick> loadTricks(String name) {
    List<Trick> allTricks = new ArrayList<>();
    trickRepository.findAll().forEach(allTricks::add);
    List<Trick> petsTricks = allTricks.stream()
        .filter(trick -> trick.getName().equalsIgnoreCase(name)).collect(
            Collectors.toList());
    return petsTricks;
  }
}
