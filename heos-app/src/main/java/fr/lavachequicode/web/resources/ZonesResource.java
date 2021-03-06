package fr.lavachequicode.web.resources;

import fr.lavachequicode.model.Zone;
import fr.lavachequicode.services.HeosZoneService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/api/zones")
@Slf4j
public class ZonesResource {

  @Inject
  HeosZoneService heosZoneService;

  @GET()
  @Path("")
  @Produces(MediaType.APPLICATION_JSON)
  public Collection<Zone> list() {
    return heosZoneService.getZones();
  }

  @GET()
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Zone get(@PathParam("id") String id) {
    return heosZoneService.getZone(id);
  }

  @Data
  @AllArgsConstructor
  public static class GroupDto {
    String udn;
    String friendlyName;
    String status;
  }

}
