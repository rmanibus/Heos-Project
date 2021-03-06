package fr.lavachequicode.web.resources.upnp;

import fr.lavachequicode.heos.sdk.model.AVTCurrentState;
import fr.lavachequicode.heos.sdk.model.PositionInfo;
import fr.lavachequicode.heos.sdk.model.TransportInfo;
import fr.lavachequicode.heos.sdk.services.AVTransport;
import fr.lavachequicode.services.HeosUpnpFactory;
import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.types.UDN;
import org.fourthline.cling.registry.Registry;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/upnp/avt")
@Slf4j
public class AvtResource {

  @Inject
  Registry registry;
  @Inject
  HeosUpnpFactory heosUpnpFactory;

  protected AVTransport getAvTransport(UDN udn) {
    final Device device = registry.getDevice(udn, false);
    if (device == null) {
      throw new NotFoundException();
    }
    return heosUpnpFactory.createProxy(device.findService(AVTransport.serviceId), AVTransport.class);
  }

  @GET()
  @Path("/getCurrentState/{udn}")
  @Produces(MediaType.APPLICATION_JSON)
  public AVTCurrentState getAvtCurrentState(@PathParam("udn") UDN udn) {
    return getAvTransport(udn).getCurrentState();
  }

  @GET()
  @Path("/getCurrentTransportActions/{udn}")
  @Produces(MediaType.TEXT_PLAIN)
  public String getAvtCurrentTransportActions(@PathParam("udn") UDN udn) {
    return getAvTransport(udn).getCurrentTransportActions("0");
  }

  @GET()
  @Path("/getTransportInfo/{udn}")
  @Produces(MediaType.APPLICATION_JSON)
  public TransportInfo getTransportInfo(@PathParam("udn") UDN udn) {
    return getAvTransport(udn).getTransportInfo("0");
  }

  @GET()
  @Path("/getMediaInfo/{udn}")
  @Produces(MediaType.TEXT_PLAIN)
  public String getMediaInfo(@PathParam("udn") UDN udn) {
    return getAvTransport(udn).getMediaInfo("0");
  }

  @GET()
    @Path("/getTransportSettings/{udn}")
  @Produces(MediaType.TEXT_PLAIN)
  public String getTransportSettings(@PathParam("udn") UDN udn) {
    return getAvTransport(udn).getTransportSettings("0");
  }
  @GET()
  @Path("/getMediaInfo_Ext/{udn}")
  @Produces(MediaType.TEXT_PLAIN)
  public String getMediaInfo_Ext(@PathParam("udn") UDN udn) {
    return getAvTransport(udn).getMediaInfo_Ext("0");
  }

  @GET()
  @Path("/getPositionInfo/{udn}")
  @Produces(MediaType.APPLICATION_JSON)
  public PositionInfo getPositionInfo(@PathParam("udn") UDN udn) {
    return getAvTransport(udn).getPositionInfo("0");
  }

  @GET()
  @Path("/getDeviceCapabilities/{udn}")
  @Produces(MediaType.TEXT_PLAIN)
  public String getDeviceCapabilities(@PathParam("udn") UDN udn) {
    return getAvTransport(udn).getDeviceCapabilities("0");
  }

  @GET()
  @Path("/play/{udn}")
  @Produces(MediaType.TEXT_PLAIN)
  public void play(@PathParam("udn") UDN udn) {
    getAvTransport(udn).play("0", "1");
  }

  @GET()
  @Path("/pause/{udn}")
  @Produces(MediaType.TEXT_PLAIN)
  public void pause(@PathParam("udn") UDN udn) {
    getAvTransport(udn).pause("0");
  }

  @GET()
  @Path("/next/{udn}")
  @Produces(MediaType.TEXT_PLAIN)
  public void next(@PathParam("udn") UDN udn) {
    getAvTransport(udn).next("0");
  }
}
