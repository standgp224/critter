package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

/**
 * @author T.Q
 * @version 1.0
 */
@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    PetRepo petRepo;

    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    public ResponseEntity<Customer> findById(long id) {
        Optional<Customer> optionalCustomer = customerRepo.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else return null;
    }

    public ResponseEntity<Customer> findCustomerByPetId(long id) {
        Optional<Pet> optionalPet = petRepo.findById(id);
        if (optionalPet.isPresent()) {
            Pet pet = optionalPet.get();
            Customer customer = pet.getCustomer();
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else return null;
    }
}
