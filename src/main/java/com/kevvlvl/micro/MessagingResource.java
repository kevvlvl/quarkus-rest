package com.kevvlvl.micro;

import com.kevvlvl.micro.dto.MessageDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/message")
public class MessagingResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/send")
    public String sendMessage(MessageDto message) {

        // TODO: implement async messaging between controller and an inbox where we can log reception of messages and implement workflow

        return message.toString();
    }
}
