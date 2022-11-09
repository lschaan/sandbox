package com.lschaan.springquartz.api;

import com.lschaan.springquartz.service.MessageService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageServiceController {

    private final MessageService messageService;

    public MessageServiceController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/{jobName}/start")
    public void startJob(@PathVariable String jobName) {
        messageService.execute(jobName);
    }

}
