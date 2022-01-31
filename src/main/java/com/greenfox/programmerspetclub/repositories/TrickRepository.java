package com.greenfox.programmerspetclub.repositories;

import com.greenfox.programmerspetclub.models.trick.Trick;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrickRepository extends CrudRepository<Trick, String> {

  List<Trick> findByNameIgnoreCase(String name);

}
