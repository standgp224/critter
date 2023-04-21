package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author T.Q
 * @version 1.0
 */
@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    public Employee createEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }


}
