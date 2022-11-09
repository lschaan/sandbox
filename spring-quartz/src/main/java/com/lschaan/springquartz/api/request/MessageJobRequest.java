package com.lschaan.springquartz.api.request;

import com.sun.istack.NotNull;

public class MessageJobRequest {

    @NotNull
    private String name;
    @NotNull
    private String message;
    @NotNull
    private String cron;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
}
