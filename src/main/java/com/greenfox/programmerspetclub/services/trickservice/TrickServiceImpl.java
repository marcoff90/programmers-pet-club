package com.greenfox.programmerspetclub.services.trickservice;

import com.greenfox.programmerspetclub.models.trick.Trick;
import com.greenfox.programmerspetclub.repositories.TrickRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrickServiceImpl implements TrickService {

  private final TrickRepository trickRepository;
  private boolean isTrickLearned;

  @Autowired
  public TrickServiceImpl(
      TrickRepository trickRepository) {
    this.trickRepository = trickRepository;
    this.isTrickLearned = false;
  }

  @Override
  public void addTrick(String name, String trick) {
    if (trickRepository.findByNameIgnoreCase(name).stream().noneMatch(trickObject -> trickObject.getDescription().equalsIgnoreCase(trick))) {
      trickRepository.save(new Trick(name, trick));
    } else {
      this.isTrickLearned = true;
    }
  }

  @Override
  public List<Trick> getTricks(String name) {
    return trickRepository.findByNameIgnoreCase(name).isEmpty() ? Arrays.asList(
        new Trick(name, name + " doesn't know any tricks yet!")) : trickRepository.findByNameIgnoreCase(name);
  }

  @Override
  public void setIsTrickLearned(boolean isTrickLearned) {
    this.isTrickLearned = isTrickLearned;
  }

  @Override
  public boolean isTrickLearned() {
    return this.isTrickLearned;
  }
}
