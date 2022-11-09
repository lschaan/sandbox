package com.lschaan.springquartz.api;


import com.lschaan.springquartz.api.request.MessageJobRequest;
import com.lschaan.springquartz.converter.MessageJobConverter;
import com.lschaan.springquartz.repository.entity.MessageJob;
import com.lschaan.springquartz.service.MessageJobSchedulerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs/messages")
public class MessageJobController {

    private final MessageJobSchedulerService messageJobService;

    public MessageJobController(MessageJobSchedulerService jobService) {
        this.messageJobService = jobService;
    }

    @PostMapping
    public ResponseEntity<MessageJob> createJob(@RequestBody MessageJobRequest request) {
        MessageJob job = MessageJobConverter.fromRequest(request);
        MessageJob result = messageJobService.create(job);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{jobName}")
    public ResponseEntity<MessageJob> getJob(@PathVariable String jobName) {
        return ResponseEntity.ok(messageJobService.findJobByName(jobName));
    }

    @GetMapping
    public ResponseEntity<List<MessageJob>> findAllJobs() {
        return ResponseEntity.ok(messageJobService.findAll());
    }

    @DeleteMapping("/{jobName}")
    public void deleteJob(@PathVariable String jobName) {
        messageJobService.delete(jobName);
    }
}
