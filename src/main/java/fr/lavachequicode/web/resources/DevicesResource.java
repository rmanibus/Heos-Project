package fr.lavachequicode.web.resources;

import fr.lavachequicode.web.dto.DeviceDto;
import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.types.UDN;
import org.fourthline.cling.registry.Registry;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/devices")
@Slf4j
public class DevicesResource {

    @Inject
    Registry registry;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<DeviceDto> list() {
        return registry.getDevices().stream().map(DeviceDto::new).collect(Collectors.toList());
    }

    @GET()
    @Path("/{udn}")
    @Produces(MediaType.APPLICATION_JSON)
    public DeviceDto list(@PathParam("udn") UDN udn) {
        final Device device = registry.getDevice(udn, false);
        if(device == null){
            throw new NotFoundException();
        }
        return new DeviceDto(device);
    }
}