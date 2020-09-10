package com.kevvlvl.micro.service;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@ApplicationScoped
public class BroadcastService {

    Map<String, Session> sessions;

    @PostConstruct
    public void init() {
        log.info("PostConstruct initialization - init()");
        this.sessions = new ConcurrentHashMap<>();
    }

    @PreDestroy
    public void destroy() {
        log.info("PreDestroy cleanup - destroy()");
        broadcastMessage("Closing session...");
        this.sessions.clear();
    }

    public void join(String user, Session session) {
        sessions.put(user, session);
        broadcastMessage(String.format("User %s joined the channel", user));
    }

    public void leave(String user) {
        sessions.remove(user);
        broadcastMessage(String.format("User %s left the channel", user));
    }

    public void broadcastMessage(String message) {

        sessions.values().forEach(s -> {
            s.getAsyncRemote().sendObject(message, result -> {
                if(result.getException() != null) {
                    log.error("Unable to send message: {}", result.getException());
                }
            });
        });
    }
}
