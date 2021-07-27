package fr.lavachequicode.web.resources;

import fr.lavachequicode.lib.upnp.model.ACTCurrentState;
import fr.lavachequicode.lib.upnp.model.AVTCurrentState;
import fr.lavachequicode.lib.upnp.model.GroupCurrentState;
import fr.lavachequicode.lib.upnp.services.ACT;
import fr.lavachequicode.lib.upnp.services.AVTTransport;
import fr.lavachequicode.lib.upnp.services.GroupControl;
import fr.lavachequicode.services.HeosUpnpFactoy;
import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.types.UDN;
import org.fourthline.cling.registry.Registry;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/actions")
@Slf4j
public class ActionsResource {

    @Inject
    Registry registry;
    @Inject
    HeosUpnpFactoy heosUpnpFactoy;

    @GET()
    @Path("/avt/getCurrentState/{udn}")
    @Produces(MediaType.TEXT_PLAIN)
    public AVTCurrentState getAvtCurrentState(@PathParam("udn") UDN udn) {
        final Device device = registry.getDevice(udn, false);
        if (device == null) {
            throw new NotFoundException();
        }
        AVTTransport aVTTransport = heosUpnpFactoy.createProxy(device.findService(AVTTransport.serviceId), AVTTransport.class);
        return aVTTransport.getCurrentState();
    }
    @GET()
    @Path("/avt/getCurrentTransportActions/{udn}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAvtCurrentTransportActions(@PathParam("udn") UDN udn) {
        final Device device = registry.getDevice(udn, false);
        if (device == null) {
            throw new NotFoundException();
        }
        AVTTransport aVTTransport = heosUpnpFactoy.createProxy(device.findService(AVTTransport.serviceId), AVTTransport.class);
        return aVTTransport.getCurrentTransportActions("0");
    }

    @GET()
    @Path("/group/getCurrentState/{udn}")
    @Produces(MediaType.TEXT_PLAIN)
    public GroupCurrentState getGroupCurrentState(@PathParam("udn") UDN udn) {
        final Device device = registry.getDevice(udn, false);
        if (device == null) {
            throw new NotFoundException();
        }
        GroupControl groupControl = heosUpnpFactoy.createProxy(device.findService(GroupControl.serviceId), GroupControl.class);
        return groupControl.getCurrentState();
    }

    @GET()
    @Path("/act/getCurrentState/{udn}")
    @Produces(MediaType.TEXT_PLAIN)
    public ACTCurrentState getActCurrentState(@PathParam("udn") UDN udn) {
        final Device device = registry.getDevice(udn, false);
        if (device == null) {
            throw new NotFoundException();
        }
        ACT act = heosUpnpFactoy.createProxy(device.findService(ACT.serviceId), ACT.class);
        return act.getCurrentState();
    }
}
