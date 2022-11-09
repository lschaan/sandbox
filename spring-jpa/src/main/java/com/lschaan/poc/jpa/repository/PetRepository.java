package com.lschaan.poc.jpa.repository;

import com.lschaan.poc.jpa.repository.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
}
