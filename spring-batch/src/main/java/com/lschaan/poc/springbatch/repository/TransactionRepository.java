package com.lschaan.poc.springbatch.repository;

import com.lschaan.poc.springbatch.dto.TransactionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDTO, Integer> {
}
