package fr.lavachequicode.web.resources;

import fr.lavachequicode.heos.sdk.model.DIDLLite;
import fr.lavachequicode.model.Device;
import fr.lavachequicode.services.HeosContentDirectoryService;
import fr.lavachequicode.services.HeosDeviceService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/api/devices")
@Slf4j
public class DevicesResource {

  @Inject
  HeosDeviceService heosDeviceService;
  @Inject
  HeosContentDirectoryService heosContentDirectoryService;

  @GET()
  @Path("")
  @Produces(MediaType.APPLICATION_JSON)
  public Collection<Device> list() {
    return heosDeviceService.getDevices();
  }

  @GET()
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Device get(@PathParam("id") String id) {
    return heosDeviceService.getDevice(id);
  }

  @GET()
  @Path("/{id}/inputs")
  @Produces(MediaType.APPLICATION_JSON)
  public DIDLLite inputs(@PathParam("id") String id) {
    return heosContentDirectoryService.getInputs(id);
  }

}
