package fr.lavachequicode.web.resources;

import fr.lavachequicode.model.Zone;
import fr.lavachequicode.services.HeosDeviceService;
import fr.lavachequicode.services.HeosZoneService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/devices")
@Slf4j
public class DevicesResource {

    @Inject
    HeosDeviceService heosDeviceService;

    @GET()
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Zone  > list() {
        return heosDeviceService.getZones();
    }


    @Data
    @AllArgsConstructor
    public static class GroupDto {
        String udn;
        String friendlyName;
        String status;
    }

}
