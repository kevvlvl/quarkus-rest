package com.kevvlvl.micro;

import com.kevvlvl.micro.dto.MessageDto;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/message")
public class MessagingResource {

    @Inject
    EventBus bus;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/send")
    public String sendMessage(MessageDto message) {

        Uni<Object> returnMsg = bus.<String>request("inbox", message.getMessage())
                .onItem().apply(Message::body);

        return message.toString();
    }
}
