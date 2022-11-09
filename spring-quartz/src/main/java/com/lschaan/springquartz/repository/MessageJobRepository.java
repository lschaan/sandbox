package com.lschaan.springquartz.repository;

import com.lschaan.springquartz.repository.entity.MessageJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MessageJobRepository extends JpaRepository<MessageJob, Integer> {

    Boolean existsByName(String name);

    void deleteByName(String name);

    Optional<MessageJob> findByName(String name);
}
