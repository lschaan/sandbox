package com.lschaan.poc.springbatch.config;

import com.lschaan.poc.springbatch.dto.TransactionDTO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@ComponentScan(basePackageClasses = DBDataSourceConfig.class)
public class TransactionJobConfig {

    private final JobBuilderFactory jobs;
    private final StepBuilderFactory steps;

    public TransactionJobConfig(JobBuilderFactory jobs, StepBuilderFactory steps) {
        this.jobs = jobs;
        this.steps = steps;
    }

    @Bean
    public Job transactionJob(@Qualifier("transactionStep") Step transactionStep) {
        return jobs.get("transactionJob").flow(transactionStep).end().build();
    }

    @Bean
    public Step transactionStep(
            ItemReader<TransactionDTO> transactionDTOItemReader,
            ItemProcessor<TransactionDTO, TransactionDTO> transactionDTOItemProcessor,
            ItemWriter<TransactionDTO> transactionDTOItemWriter) {
        return steps
                .get("transactionStep")
                .<TransactionDTO, TransactionDTO>chunk(1)
                .reader(transactionDTOItemReader)
                .processor(transactionDTOItemProcessor)
                .writer(transactionDTOItemWriter)
                .allowStartIfComplete(true)
                .build();
    }
}
