package com.lschaan.springquartz.service;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Component
public class MessageJobExecutor implements Job {

    private final MessageService messageService;

    public MessageJobExecutor(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void execute(JobExecutionContext context) {
        messageService.execute(context.getTrigger().getJobKey().getName());
    }
}
