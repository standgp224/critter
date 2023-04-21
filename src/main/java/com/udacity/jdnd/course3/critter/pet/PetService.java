package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    PetRepo petRepo;

    @Autowired
    CustomerRepo customerRepo;

    public Pet createPet(Pet pet) {
        return petRepo.save(pet);
    }

    public ResponseEntity<List<Pet>> getAllPets() {
        List<Pet> pets = petRepo.findAll();
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    public ResponseEntity<List<Pet>> getPetsByCustomer(long id) {
        Optional<Customer> optionalCustomer = customerRepo.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return new ResponseEntity<>(customer.getPets(), HttpStatus.OK);
        }
        else return null;
    }
}
