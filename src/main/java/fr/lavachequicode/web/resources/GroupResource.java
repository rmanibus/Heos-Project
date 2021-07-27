package fr.lavachequicode.web.resources;

import fr.lavachequicode.lib.upnp.model.GroupCurrentState;
import fr.lavachequicode.lib.upnp.services.GroupControl;
import fr.lavachequicode.services.HeosUpnpFactoy;
import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.types.UDN;
import org.fourthline.cling.registry.Registry;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/group")
@Slf4j
public class GroupResource {

    @Inject
    Registry registry;
    @Inject
    HeosUpnpFactoy heosUpnpFactoy;

    protected GroupControl getGroupControl(UDN udn) {
        final Device device = registry.getDevice(udn, false);
        if (device == null) {
            throw new NotFoundException();
        }
        return heosUpnpFactoy.createProxy(device.findService(GroupControl.serviceId), GroupControl.class);
    }

    @GET()
    @Path("/getCurrentState/{udn}")
    @Produces(MediaType.APPLICATION_JSON)
    public GroupCurrentState getGroupCurrentState(@PathParam("udn") UDN udn) {
        return getGroupControl(udn).getCurrentState();
    }

    @GET()
    @Path("/getGroupUUID/{udn}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getGroupUUID(@PathParam("udn") UDN udn) {
        return getGroupControl(udn).getGroupUUID();
    }

    @GET()
    @Path("/getGroupMemberChannel/{udn}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getGroupMemberChannel(@PathParam("udn") UDN udn) {
        GroupControl groupControl = getGroupControl(udn);
        return groupControl.getGroupMemberChannel(groupControl.getGroupUUID());
    }
}
