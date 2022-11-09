package com.lschaan.poc.jpa.converter;

import com.lschaan.poc.jpa.api.request.PetRequest;
import com.lschaan.poc.jpa.api.response.PetResponse;
import com.lschaan.poc.jpa.repository.entity.Pet;

public class PetConverter {

    public static Pet fromRequest(PetRequest request) {
        Pet pet = new Pet();
        pet.setName(request.getName());
        pet.setAge(request.getAge());
        pet.setBreed(request.getBreed());
        return pet;
    }

    public static PetResponse toResponse(Pet pet) {
        PetResponse response = new PetResponse();
        response.setId(pet.getId());
        response.setName(pet.getName());
        response.setAge(pet.getAge());
        response.setBreed(pet.getBreed());
        return response;
    }
}
