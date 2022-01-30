package com.greenfox.programmerspetclub.repositories;

import com.greenfox.programmerspetclub.models.history.History;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends CrudRepository<History, String> {

}
