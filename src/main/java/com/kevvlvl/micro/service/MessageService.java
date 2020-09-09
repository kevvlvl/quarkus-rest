package com.kevvlvl.micro.service;

import io.quarkus.vertx.ConsumeEvent;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
public class MessageService {

    @ConsumeEvent("inbox")
    public String consume(String message) {

        log.info("Inbox Message consumed: {}", message);
        return "Message Consumed: " + message;
    }
}
