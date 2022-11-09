package com.lschaan.poc.jpa.api;

import com.lschaan.poc.jpa.api.request.PetRequest;
import com.lschaan.poc.jpa.api.response.PetResponse;
import com.lschaan.poc.jpa.converter.PetConverter;
import com.lschaan.poc.jpa.repository.entity.Pet;
import com.lschaan.poc.jpa.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/pets")
public class PetApi {

    private final PetService petService;

    public PetApi(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<PetResponse> createPet(@RequestBody PetRequest request) {
        Pet pet = PetConverter.fromRequest(request);
        Pet result = petService.create(pet);
        return ResponseEntity.ok(PetConverter.toResponse(result));
    }

    @GetMapping
    public ResponseEntity<List<PetResponse>> findAll() {
        List<PetResponse> petsResponse = petService.findAll()
                .stream()
                .map(PetConverter::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(petsResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetResponse> getPetById(@PathVariable Integer id) {
        Pet pet = petService.findById(id);
        return ResponseEntity.ok(PetConverter.toResponse(pet));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetResponse> updatePet(@PathVariable Integer id, @RequestBody PetRequest request) {
        Pet pet = PetConverter.fromRequest(request);
        Pet result = petService.update(id, pet);
        return ResponseEntity.ok(PetConverter.toResponse(result));
    }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Integer id) {
        petService.delete(id);
    }
}
