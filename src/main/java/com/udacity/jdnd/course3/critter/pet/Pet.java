package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author T.Q
 * @version 1.0
 */
@Entity
@Table(name = "Pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate birthDate;

    private String notes;

    private PetType petType;

    private String preferredFood;

    @ManyToOne
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public String getPreferredFood() {
        return preferredFood;
    }

    public void setPreferredFood(String preferredFood) {
        this.preferredFood = preferredFood;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}