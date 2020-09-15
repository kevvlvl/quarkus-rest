package com.kevvlvl.micro.resource;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Duration;

@Path("/health")
public class HealthResource {

    private static final int MILLISECONDS_HUNDRED = 100;

    @ConfigProperty(name = "system.name")
    String systemName;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String health() {

        return getApiMsg();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/async")
    public Uni<String> healthAsync() {

        return Uni.createFrom()
                .item(() -> getApiMsg())
                .onItem().delayIt().by(Duration.ofMillis(MILLISECONDS_HUNDRED));
    }

    private String getApiMsg() {
        return String.format("Api is up %s", systemName);
    }
}