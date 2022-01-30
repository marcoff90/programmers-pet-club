package com.greenfox.programmerspetclub.repositories;

import com.greenfox.programmerspetclub.models.pet.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends CrudRepository<Pet, String> {

}
