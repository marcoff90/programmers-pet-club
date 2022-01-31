package com.greenfox.programmerspetclub.services.trickservice;

import com.greenfox.programmerspetclub.models.trick.Trick;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface TrickService {

  void addTrick(String name, String trick);

  List<Trick> getTricks(String name);

  void setIsTrickLearned(boolean isTrickLearned);

  boolean isTrickLearned();

}
