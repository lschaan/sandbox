package com.lschaan.springquartz.service;

import com.lschaan.springquartz.errors.JobNotFoundException;
import com.lschaan.springquartz.repository.MessageJobRepository;
import com.lschaan.springquartz.repository.entity.MessageJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final static Logger logger = LoggerFactory.getLogger(MessageService.class);
    private final MessageJobRepository messageJobRepository;

    public MessageService(MessageJobRepository messageJobRepository) {
        this.messageJobRepository = messageJobRepository;
    }

    public void execute(String jobName) {
        MessageJob job = messageJobRepository.findByName(jobName).orElseThrow(JobNotFoundException::new);
        logger.info("Executing job: {} - {}", job.getName(), job.getMessage());
        /*
        Business logic
        ...
         */
    }
}
