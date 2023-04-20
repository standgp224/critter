package com.udacity.jdnd.course3.critter.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author T.Q
 * @version 1.0
 */
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

}
