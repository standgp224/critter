package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * @author T.Q
 * @version 1.0
 */
@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private long id;

    @ManyToMany
    @JoinTable(
            name = "schedule_employee",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> employees;

    @ManyToMany
    @JoinTable(
            name = "schedule_pet",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "pet_id")
    )
    private List<Pet> pets;

    @Column(name = "schedule_date")
    private LocalDate date;

    @ElementCollection(targetClass = EmployeeSkill.class)
    @CollectionTable(name = "schedule_activity", joinColumns = @JoinColumn(name = "schedule_id"))
    @Column(name = "activity")
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> activities;

}
