package fr.lavachequicode.web.resources;

import fr.lavachequicode.lib.upnp.model.ZoneCurrentState;
import fr.lavachequicode.lib.upnp.services.GroupControl;
import fr.lavachequicode.lib.upnp.services.ZoneControl;
import fr.lavachequicode.services.HeosUpnpFactoy;
import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.types.UDN;
import org.fourthline.cling.registry.Registry;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/zone")
@Slf4j
public class ZoneResource {

    @Inject
    Registry registry;
    @Inject
    HeosUpnpFactoy heosUpnpFactoy;

    protected ZoneControl getZoneControl(UDN udn) {
        final Device device = registry.getDevice(udn, false);
        if (device == null) {
            throw new NotFoundException();
        }
        return heosUpnpFactoy.createProxy(device.findService(ZoneControl.serviceId), ZoneControl.class);
    }

    @GET()
    @Path("/getCurrentState/{udn}")
    @Produces(MediaType.APPLICATION_JSON)
    public ZoneCurrentState getZoneCurrentState(@PathParam("udn") UDN udn) {
        return getZoneControl(udn).getCurrentState();
    }
}
