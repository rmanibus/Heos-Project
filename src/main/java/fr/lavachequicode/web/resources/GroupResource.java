package fr.lavachequicode.web.resources;

import fr.lavachequicode.lib.upnp.model.GroupCurrentState;
import fr.lavachequicode.lib.upnp.services.GroupControl;
import fr.lavachequicode.services.HeosUpnpFactoy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.meta.DeviceIdentity;
import org.fourthline.cling.model.types.UDN;
import org.fourthline.cling.registry.Registry;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/groups")
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
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, List<GroupDto>> list() {
        return registry.getDevices().stream()
                .map(Device::getIdentity)
                .map(DeviceIdentity::getUdn)
                .map(udn -> new SimpleEntry<>(getGroupControl(udn).getGroupUUID(), udn))
                .map(entry -> new SimpleEntry<>(entry.getKey(), new GroupDto(entry.getValue().getIdentifierString(), getGroupControl(entry.getValue()).getGroupStatus(entry.getKey()))))
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    @Data
    @AllArgsConstructor
    public static class GroupDto {
        String udn;
        String status;
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

    @GET()
    @Path("/getGroupVolume/{udn}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getGroupVolume(@PathParam("udn") UDN udn) {
        GroupControl groupControl = getGroupControl(udn);
        return groupControl.getGroupVolume(groupControl.getGroupUUID());
    }

    @GET()
    @Path("/getGroupUpdating/{udn}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getGroupUpdating(@PathParam("udn") UDN udn) {
        GroupControl groupControl = getGroupControl(udn);
        return groupControl.getGroupUpdating(groupControl.getGroupUUID());
    }

    @GET()
    @Path("/getGroupStatus/{udn}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getGroupStatus(@PathParam("udn") UDN udn) {
        GroupControl groupControl = getGroupControl(udn);
        return groupControl.getGroupStatus(groupControl.getGroupUUID());
    }

    @GET()
    @Path("/getGroupFriendlyName/{udn}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getGroupFriendlyName(@PathParam("udn") UDN udn) {
        GroupControl groupControl = getGroupControl(udn);
        return groupControl.getGroupFriendlyName(groupControl.getGroupUUID());
    }

    @GET()
    @Path("/getConfigDeviceUUID/{udn}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getConfigDeviceUUID(@PathParam("udn") UDN udn) {
        GroupControl groupControl = getGroupControl(udn);
        return groupControl.getConfigDeviceUUID(groupControl.getGroupUUID());
    }

    @GET()
    @Path("/getGroupBalance/{udn}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getGroupBalance(@PathParam("udn") UDN udn) {
        GroupControl groupControl = getGroupControl(udn);
        return groupControl.getGroupBalance(groupControl.getGroupUUID());
    }

    @GET()
    @Path("/getGroupBass/{udn}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getGroupBass(@PathParam("udn") UDN udn) {
        GroupControl groupControl = getGroupControl(udn);
        return groupControl.getGroupBass(groupControl.getGroupUUID());
    }
}
