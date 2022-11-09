package com.lschaan.poc.springbatch.api;

import com.lschaan.poc.springbatch.launcher.TransactionJobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class TransactionController {

    private final TransactionJobLauncher transactionJobLauncher;

    public TransactionController(TransactionJobLauncher transactionJobLauncher) {
        this.transactionJobLauncher = transactionJobLauncher;
    }

    @PostMapping("/trigger")
    public ResponseEntity<?> generateFile() {
        try {
            transactionJobLauncher.launchTransactionJob();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
