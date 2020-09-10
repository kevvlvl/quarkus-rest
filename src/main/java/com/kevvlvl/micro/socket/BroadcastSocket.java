package com.kevvlvl.micro.socket;

import com.kevvlvl.micro.service.BroadcastService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/broadcast/{user}")
@ApplicationScoped
public class BroadcastSocket {

    private BroadcastService service;

    @Inject
    public BroadcastSocket(BroadcastService service) {
        this.service = service;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("user") String user) {
        this.service.join(user, session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("user") String user) {
        this.service.leave(user);
    }

    @OnMessage
    public void onMessage(String message, @PathParam("user") String user) {
        this.service.broadcastMessage(String.format(">> %s: %s", user, message));
    }
}