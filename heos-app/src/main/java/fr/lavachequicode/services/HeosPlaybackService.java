package fr.lavachequicode.services;

import fr.lavachequicode.heos.sdk.model.AVTCurrentState;
import fr.lavachequicode.heos.sdk.services.AVTransport;
import fr.lavachequicode.model.Zone;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.types.UDN;
import org.fourthline.cling.registry.Registry;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class HeosPlaybackService {

  @Inject
  Registry registry;
  @Inject
  HeosUpnpFactory heosUpnpFactory;
  @Inject
  HeosZoneService heosZoneService;

  protected AVTransport getAvTransport(UDN udn) {
    final Device device = registry.getDevice(udn, false);
    if (device == null) {
      throw new NotFoundException();
    }
    return heosUpnpFactory.createProxy(device.findService(AVTransport.serviceId), AVTransport.class);
  }

  public AVTCurrentState getStatus(String zoneId){
    return getAvTransport(getZoneLeaderUdn(heosZoneService.getZone(zoneId))).getCurrentState();
  }
  public void play(String zoneId) {
    getAvTransport(getZoneLeaderUdn(heosZoneService.getZone(zoneId))).play("0", "1");
  }

  public void pause(String zoneId) {
    getAvTransport(getZoneLeaderUdn(heosZoneService.getZone(zoneId))).pause("0");
  }

  public void next(String zoneId) {
    getAvTransport(getZoneLeaderUdn(heosZoneService.getZone(zoneId))).next("0");
  }

  public void previous(String zoneId) {
    getAvTransport(getZoneLeaderUdn(heosZoneService.getZone(zoneId))).previous("0");
  }

  protected UDN getZoneLeaderUdn(Zone zone){
    return zone.getLeader().getUdn();
  }

}
