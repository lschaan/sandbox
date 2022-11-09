package com.lschaan.poc.jpa.service;

import com.lschaan.poc.jpa.exception.PetNotFoundException;
import com.lschaan.poc.jpa.repository.PetRepository;
import com.lschaan.poc.jpa.repository.entity.Pet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet create(Pet pet) {
        return petRepository.save(pet);
    }

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public Pet findById(Integer id) {
        return petRepository.findById(id).orElseThrow(PetNotFoundException::new);
    }

    public Pet update(Integer id, Pet pet) {
        Pet entity = petRepository.findById(id).orElseThrow(PetNotFoundException::new);

        if (pet.getName() != null) entity.setName(pet.getName());
        if (pet.getAge() != null) entity.setAge(pet.getAge());
        if (pet.getBreed() != null) entity.setBreed(pet.getBreed());

        return petRepository.save(entity);
    }

    public void delete(Integer id) {
        petRepository.deleteById(id);
    }

}
