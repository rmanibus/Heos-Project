package fr.lavachequicode.web.resources.upnp;

import fr.lavachequicode.heos.sdk.model.ZoneCurrentState;
import fr.lavachequicode.heos.sdk.services.ZoneControl;
import fr.lavachequicode.services.HeosUpnpFactory;
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

@Path("/api/upnp/zones")
@Slf4j
public class ZonesResource {

    @Inject
    Registry registry;
    @Inject
    HeosUpnpFactory heosUpnpFactory;


    protected ZoneControl getZoneControl(UDN udn) {
        final Device device = registry.getDevice(udn, false);
        if (device == null) {
            throw new NotFoundException();
        }
        return heosUpnpFactory.createProxy(device.findService(ZoneControl.serviceId), ZoneControl.class);
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
