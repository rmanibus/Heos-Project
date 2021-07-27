package fr.lavachequicode.web.resources;

import fr.lavachequicode.lib.upnp.model.ZoneCurrentState;
import fr.lavachequicode.lib.upnp.services.GroupControl;
import fr.lavachequicode.lib.upnp.services.ZoneControl;
import fr.lavachequicode.services.HeosUpnpFactoy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.types.UDN;
import org.fourthline.cling.registry.Registry;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/zones")
@Slf4j
public class ZonesResource {

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
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, List<ZoneDto>> list() {
        return registry.getDevices().stream()

                .map(device -> new AbstractMap.SimpleEntry<>(getZoneControl(device.getIdentity().getUdn()).getZoneUUID(), device))
                .map(
                        entry -> new AbstractMap.SimpleEntry<>(
                                entry.getKey(),
                                new ZoneDto(
                                        entry.getValue().getIdentity().getUdn().getIdentifierString(),
                                        entry.getValue().getDetails().getFriendlyName(),
                                        getZoneControl(entry.getValue().getIdentity().getUdn()).getMemberStatus(entry.getKey())
                                )
                        )
                )
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    @Data
    @AllArgsConstructor
    public static class ZoneDto {
        String udn;
        String friendlyName;
        String status;
    }

    @GET()
    @Path("/getCurrentState/{udn}")
    @Produces(MediaType.APPLICATION_JSON)
    public ZoneCurrentState getZoneCurrentState(@PathParam("udn") UDN udn) {
        return getZoneControl(udn).getCurrentState();
    }
}
