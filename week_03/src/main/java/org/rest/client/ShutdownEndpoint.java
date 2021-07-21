package org.rest.client;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/actuator")
public interface ShutdownEndpoint {
    @POST
    @Path("/shutdown")
    String shutdown();
}
