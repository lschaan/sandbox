package com.lschaan.poc.springbatch.launcher;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TransactionJobLauncher {
    private final Job job;
    private final JobLauncher jobLauncher;

    public TransactionJobLauncher(@Qualifier("transactionJob") Job job, JobLauncher jobLauncher) {
        this.job = job;
        this.jobLauncher = jobLauncher;
    }

    public JobExecution launchTransactionJob() throws Exception {
        return jobLauncher.run(job, newExecution());
    }

    private JobParameters newExecution() {
        return new JobParameters();
    }
}
