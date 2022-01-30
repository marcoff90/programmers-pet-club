package com.greenfox.programmerspetclub.services.historyservice;

import com.greenfox.programmerspetclub.models.history.History;
import java.util.List;

public interface HistoryService {

  void addHistoryOfFood(String name, String food, String drink);

  void addHistoryOfTricks(String name, String newTrick);

  List<History> getHistory(String name);

}
