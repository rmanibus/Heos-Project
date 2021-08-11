package fr.lavachequicode.web.resources.upnp;

import fr.lavachequicode.heos.sdk.model.Browse;
import fr.lavachequicode.heos.sdk.model.DIDLLite;
import fr.lavachequicode.heos.sdk.services.ContentDirectory;
import fr.lavachequicode.services.HeosUpnpFactory;
import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.types.UDN;
import org.fourthline.cling.registry.Registry;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/upnp/cd")
@Slf4j
public class CDResource {
  @Inject
  Registry registry;
  @Inject
  HeosUpnpFactory heosUpnpFactory;

  protected ContentDirectory getContentDirectory(UDN udn) {
    final Device device = registry.getDevice(udn, false);
    if (device == null) {
      throw new NotFoundException();
    }
    return heosUpnpFactory.createProxy(device.findService(ContentDirectory.serviceId), ContentDirectory.class);
  }

  @GET()
  @Path("/browse/{udn}")
  @Produces(MediaType.APPLICATION_JSON)
  public Browse getActCurrentState(@PathParam("udn") UDN udn,
                                   @QueryParam("ObjectID") String objectID,
                                   @QueryParam("BrowseFlag") String browseFlag,
                                   @QueryParam("Filter") String filter,
                                   @QueryParam("StartingIndex") String startingIndex,
                                   @QueryParam("RequestedCount") String requestedCount,
                                   @QueryParam("SortCriteria") String sortCriteria) {

    return getContentDirectory(udn).browse(objectID, browseFlag, filter, startingIndex, requestedCount, sortCriteria);
  }

}
