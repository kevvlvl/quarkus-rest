package com.kevvlvl.micro;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/health")
public class HealthResource {

    @ConfigProperty(name = "system.name")
    String systemName;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String health() {

        String message = String.format("Api is up %s", systemName);

        return message;
    }
}