package com.udacity.jdnd.course3.critter.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author T.Q
 * @version 1.0
 */
@Repository
public interface PetRepo extends JpaRepository<Pet, Long> {
}
