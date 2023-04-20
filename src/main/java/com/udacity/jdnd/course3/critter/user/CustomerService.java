package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author T.Q
 * @version 1.0
 */
@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    public ResponseEntity<Customer> createCustomer(Customer customer) {
        Customer customerAdded = customerRepo.save(customer);
        return new ResponseEntity<>(customerAdded, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
