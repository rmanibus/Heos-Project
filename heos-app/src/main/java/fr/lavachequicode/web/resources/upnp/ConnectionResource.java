package fr.lavachequicode.web.resources.upnp;

import fr.lavachequicode.heos.sdk.model.ConnectionCurrentState;
import fr.lavachequicode.heos.sdk.services.ConnectionManager;
import fr.lavachequicode.services.HeosUpnpFactoy;
import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.types.UDN;
import org.fourthline.cling.registry.Registry;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/upnp/connection")
@Slf4j
public class ConnectionResource {


    @Inject
    Registry registry;
    @Inject
    HeosUpnpFactoy heosUpnpFactoy;

    protected ConnectionManager getConnectionManager(UDN udn) {
        final Device device = registry.getDevice(udn, false);
        if (device == null) {
            throw new NotFoundException();
        }
        return heosUpnpFactoy.createProxy(device.findService(ConnectionManager.serviceId), ConnectionManager.class);
    }
    @GET()
    @Path("/getCurrentState/{udn}")
    @Produces(MediaType.TEXT_PLAIN)
    public ConnectionCurrentState getConnectionCurrentState(@PathParam("udn") UDN udn) {
        return getConnectionManager(udn).getCurrentState();
    }

}
