package fr.lavachequicode.services;

import fr.lavachequicode.heos.sdk.model.DIDLLite;
import fr.lavachequicode.heos.sdk.services.ContentDirectory;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.types.UDN;
import org.fourthline.cling.registry.Registry;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class HeosContentDirectoryService {
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

  public DIDLLite getInputs(String udn){
    return getContentDirectory(UDN.valueOf(udn)).browse("inputs/", "BrowseDirectChildren", "dc:title,dc:date,upnp:artist,dc:creator,upnp:class,upnp:album,upnp:genre,upnp:albumArtURI,res,res@duration,res@reliability,upnp:originalTrackNumber,avega_media_server:media_library_database,container@searchable", "0", "50", "");
  }
}
