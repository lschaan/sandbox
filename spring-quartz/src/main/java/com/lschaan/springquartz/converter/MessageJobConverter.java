package com.lschaan.springquartz.converter;

import com.lschaan.springquartz.api.request.MessageJobRequest;
import com.lschaan.springquartz.repository.entity.MessageJob;

public class MessageJobConverter {

    public static MessageJob fromRequest(MessageJobRequest request) {
        MessageJob job = new MessageJob();
        job.setName(request.getName());
        job.setMessage(request.getMessage());
        job.setCron(request.getCron());
        return job;
    }
}
