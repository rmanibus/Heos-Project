package fr.lavachequicode.services;

import fr.lavachequicode.heos.sdk.model.PositionInfo;
import fr.lavachequicode.heos.sdk.services.AVTransport;
import fr.lavachequicode.model.Device;
import fr.lavachequicode.model.Group;
import fr.lavachequicode.model.Zone;
import fr.lavachequicode.web.dto.PlaybackStatusDto;
import org.fourthline.cling.model.types.UDN;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class HeosPlaybackService {

  @Inject
  HeosUpnpFactory heosUpnpFactory;
  @Inject
  HeosZoneService heosZoneService;


  public PlaybackStatusDto getStatus(String zoneId) {
    AVTransport avTransport = heosUpnpFactory.getAvTransport(getZoneLeaderUdn(heosZoneService.getZone(zoneId)));
    return new PlaybackStatusDto(avTransport.getPositionInfo("0"), avTransport.getTransportInfo("0"));
  }

  public void play(String zoneId) {
    heosUpnpFactory.getAvTransport(getZoneLeaderUdn(heosZoneService.getZone(zoneId))).play("0", "1");
  }

  public void pause(String zoneId) {
    heosUpnpFactory.getAvTransport(getZoneLeaderUdn(heosZoneService.getZone(zoneId))).pause("0");
  }

  public void next(String zoneId) {
    heosUpnpFactory.getAvTransport(getZoneLeaderUdn(heosZoneService.getZone(zoneId))).next("0");
  }

  public void previous(String zoneId) {
    heosUpnpFactory.getAvTransport(getZoneLeaderUdn(heosZoneService.getZone(zoneId))).previous("0");
  }

  public void volume(String zoneId, Integer volume) {
    Zone zone = heosZoneService.getZone(zoneId);
    zone.getMembers().forEach(
      member -> {
        if (member instanceof Group) {
          ((Group) member).getMembers().forEach((Device device) -> heosUpnpFactory.getGroupControl(device.getUdn()).setGroupVolume(member.getId(), volume.toString(), "0"));
        }
      }
    );
  }

  protected UDN getZoneLeaderUdn(Zone zone) {
    return zone.getLeader().getUdn();
  }

}
