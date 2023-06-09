package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = convertCustomerDTOToCustomer(customerDTO);
        Customer customerAdded = customerService.createCustomer(customer);
        return convertEntityToCustomerDTO(customerAdded);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        try {
                List<Customer> allCustomers = customerService.getAllCustomers().getBody();
                ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
                if (allCustomers != null) {
                    for (Customer customer : allCustomers
                    ) {
                        CustomerDTO customerDTO = convertEntityToCustomerDTO(customer);
                        customerDTOS.add(customerDTO);
                    }
                    return customerDTOS;
                } else return null;

            }
        catch (Exception e) {
            throw new UnsupportedOperationException();
        }
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        ResponseEntity<Customer> customerByPetId = customerService.findCustomerByPetId(petId);
        return convertEntityToCustomerDTO(customerByPetId.getBody());
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = convertEmployeeDTOToEmployee(employeeDTO);
        return convertEmployeeToEmployeeDTO(employeeService.createEmployee(employee));
    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return convertEmployeeToEmployeeDTO(employeeService.getEmployeeById(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        employeeService.updateEmployeeAvailability(employee, daysAvailable);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        Employee employee = convertEmployeeRequestDTOToEmployee(employeeDTO);
        List<Employee> employeeWithRequest = employeeService.getEmployeeWithRequest(employee);
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee emp: employeeWithRequest
             ) {
            EmployeeDTO toEmployeeDTO = convertEmployeeToEmployeeDTO(emp);
            employeeDTOS.add(toEmployeeDTO);
        }
        return employeeDTOS;
    }

    public static Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        List<Pet> pets = new ArrayList<>();
        if (customerDTO.getPetIds() != null) {
            for (Long petId : customerDTO.getPetIds()) {
                Pet pet = new Pet();
                pet.setId(petId);
                pets.add(pet);
            }
        }
        customer.setPets(pets);
        return customer;
    }

    public static CustomerDTO convertEntityToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        List<Long> petIds = new ArrayList<>();
        if (customer.getPets() != null) {
            for (Pet pet : customer.getPets()) {
                petIds.add(pet.getId());
            }
        }
        customerDTO.setPetIds(petIds);
        return customerDTO;
    }

    public static Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

    public static Employee convertEmployeeRequestDTOToEmployee(EmployeeRequestDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

    public static EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }


}
