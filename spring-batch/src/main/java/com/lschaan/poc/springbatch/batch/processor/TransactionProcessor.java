package com.lschaan.poc.springbatch.batch.processor;

import com.lschaan.poc.springbatch.dto.TransactionDTO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class TransactionProcessor implements ItemProcessor<TransactionDTO, TransactionDTO> {

    @Override
    public TransactionDTO process(TransactionDTO item) {
        return (item.getDescription().startsWith("ab") ? item : null); //random business rule
    }
}
