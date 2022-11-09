package com.lschaan.poc.springbatch.batch.reader;

import com.lschaan.poc.springbatch.dto.TransactionDTO;
import com.lschaan.poc.springbatch.repository.TransactionRepository;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Component
public class TransactionReader extends RepositoryItemReader<TransactionDTO> {

    private final TransactionRepository repository;

    public TransactionReader(TransactionRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        setRepository(repository);
        setSort(new HashMap<>());
        setMethodName("findAll");
    }
}
