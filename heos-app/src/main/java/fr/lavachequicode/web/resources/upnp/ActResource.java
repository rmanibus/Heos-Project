package fr.lavachequicode.web.resources.upnp;

import fr.lavachequicode.heos.sdk.model.ACTCurrentState;
import fr.lavachequicode.heos.sdk.services.ACT;
import fr.lavachequicode.services.HeosUpnpFactoy;
import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.types.UDN;
import org.fourthline.cling.registry.Registry;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/upnp/act")
@Slf4j
public class ActResource {

    @Inject
    Registry registry;
    @Inject
    HeosUpnpFactoy heosUpnpFactoy;

    protected ACT getAct(UDN udn) {
        final Device device = registry.getDevice(udn, false);
        if (device == null) {
            throw new NotFoundException();
        }
        return heosUpnpFactoy.createProxy(device.findService(ACT.serviceId), ACT.class);
    }

    @GET()
    @Path("/getCurrentState/{udn}")
    @Produces(MediaType.APPLICATION_JSON)
    public ACTCurrentState getActCurrentState(@PathParam("udn") UDN udn) {
        return getAct(udn).getCurrentState();
    }

    @GET()
    @Path("/getHEOSNetID/{udn}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getHEOSNetID(@PathParam("udn") UDN udn) {
        return getAct(udn).getHEOSNetID();
    }
}
