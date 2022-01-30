package com.greenfox.programmerspetclub.repositories;

import com.greenfox.programmerspetclub.models.trick.Trick;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrickRepository extends CrudRepository<Trick, String> {

}
