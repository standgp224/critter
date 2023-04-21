package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = convertPetDTOToPet(petDTO);
        Pet petAdded = petService.createPet(pet);
        return convertEntityToPetDTO(petAdded);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<PetDTO> getPets(){
        try {
            List<Pet> allPets = petService.getAllPets().getBody();
            ArrayList<PetDTO> customerDTOS = new ArrayList<>();
            if (allPets != null) {
                for (Pet customer : allPets
                ) {
                    PetDTO customerDTO = convertEntityToPetDTO(customer);
                    customerDTOS.add(customerDTO);
                }
                return customerDTOS;
            } else return null;

        }
        catch (Exception e) {
            throw new UnsupportedOperationException();
        }
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        ResponseEntity<List<Pet>> petsByCustomer = petService.getPetsByCustomer(ownerId);
        ArrayList<PetDTO> pets = new ArrayList<>();
        if (petsByCustomer.getBody().size() > 0) {
            for (Pet pet: petsByCustomer.getBody()
            ) {
                PetDTO petDTO = convertEntityToPetDTO(pet);
                pets.add(petDTO);
            }
            return pets;
        } else return null;

    }

    private static PetDTO convertEntityToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO, "petType");
        petDTO.setOwnerId(pet.getCustomer().getId());
        petDTO.setType(PetType.valueOf(pet.getPetType().toString()));
        return petDTO;
    }

    private static Pet convertPetDTOToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet, "type");
        pet.setPetType(PetType.valueOf(petDTO.getType().toString()));
        return pet;
    }
}
