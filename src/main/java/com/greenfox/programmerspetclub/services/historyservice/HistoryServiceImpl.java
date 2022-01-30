package com.greenfox.programmerspetclub.services.historyservice;

import com.greenfox.programmerspetclub.models.history.History;
import com.greenfox.programmerspetclub.models.trick.Trick;
import com.greenfox.programmerspetclub.repositories.HistoryRepository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl implements HistoryService {

  private HistoryRepository historyRepository;

  @Autowired
  public HistoryServiceImpl(HistoryRepository historyRepository) {
    this.historyRepository = historyRepository;
  }

  @Override
  public void addHistoryOfFood(String name, String food, String drink) {
    historyRepository.save(new History(name, getTimeAndDate() + " " + name + " changed his food & drink into " + food + " & " + drink));
  }

  @Override
  public void addHistoryOfTricks(String name, String newTrick) {
    historyRepository.save(new History(name, getTimeAndDate() + " " + name + " has learned to " + newTrick));
  }

  @Override
  public List<History> getHistory(String name) {
    return loadHistory(name).isEmpty() ? Arrays.asList(new History(name, name + " doesn't have any history yet!")) : loadHistory(name);
  }

  private String getTimeAndDate() {
    String date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    return date.substring(6, 8) + "." + date.substring(4, 6) + "." + date.substring(0, 4) + " at "
        + date.substring(9, 11) + ":" + date.substring(
        11, 13);
  }

  private List<History> loadHistory(String name) {
    List<History> allHistory = new ArrayList<>();
    historyRepository.findAll().forEach(allHistory::add);
    List<History> petsHistory = allHistory.stream().filter(history -> history.getName().equalsIgnoreCase(name)).collect(
        Collectors.toList());
    return petsHistory;
  }
}
