package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public Employee getEmployeeById(long id) {
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            return employee;
        } else return null;
    }

    public void updateEmployeeAvailability(Employee employee, Set<DayOfWeek> daysAvailable) {
        employee.setDaysAvailable(daysAvailable);
        employeeRepo.save(employee);
    }

    public List<Employee> getEmployeeWithRequest(Employee employee) {
        List<Employee> employees = employeeRepo.findAll();
        ArrayList<Employee> matchedEmp = new ArrayList<>();
        if (employees.size() > 0) {
            for (Employee emp: employees
            ) {
                if (employee.getDate() != emp.getDate() && employee.getSkills().containsAll(emp.getSkills())) {
                    matchedEmp.add(emp);
                }
            }
            return matchedEmp;
        } else return null;

    }
}
