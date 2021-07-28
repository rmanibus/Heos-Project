package fr.lavachequicode.services;

import fr.lavachequicode.lib.upnp.devices.AiosDevice;
import fr.lavachequicode.model.Group;
import fr.lavachequicode.model.GroupMember;
import fr.lavachequicode.model.Zone;
import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.model.types.UDN;
import org.fourthline.cling.registry.Registry;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@ApplicationScoped
@Slf4j
public class HeosZoneService {

    @Inject
    Registry registry;
    @Inject
    HeosStateService heosStateService;

    public Collection<Zone> getZones(){
        return buildZoneMap().values();
    }

    public Zone getGroup(String id){
        return buildZoneMap().get(id);
    }

    Map<String, Zone> buildZoneMap() {

        return registry.getDevices(AiosDevice.type).stream()
                .filter(device -> heosStateService.containsZoneStateForDevice(device.getIdentity().getUdn()))
                .map(device -> new SimpleEntry<>(heosStateService.getDeviceZoneState(device.getIdentity().getUdn()).getZoneUUID().getValue(), device))
                .map(
                        entry -> new SimpleEntry<>(
                                entry.getKey(),
                                new GroupMember(
                                        entry.getValue().getIdentity().getUdn().getIdentifierString(),
                                        entry.getValue().getDetails().getFriendlyName(),
                                        heosStateService.getDeviceZoneState(entry.getValue().getIdentity().getUdn()).getZoneStatus().getValue()
                                )
                        )
                )
                .collect(Collectors.groupingBy(Entry::getKey, Collectors.mapping(Entry::getValue, Collectors.toList())))
                .entrySet().stream()
                .map(
                        entry -> new SimpleEntry<>(
                                entry.getKey(),
                                new Zone(
                                        entry.getKey(),
                                        "No Zone",
                                        entry.getValue().stream().filter(member -> "ZONE_LEAD".equals(member.getStatus())).findFirst().orElse(null),
                                        entry.getValue()
                                )
                        )
                ).peek(
                        entry -> {
                            Zone zone = entry.getValue();
                            if (zone.getLeader() != null) {
                                zone.setFriendlyName(heosStateService.getDeviceZoneState(UDN.valueOf(zone.getLeader().getUdn())).getZoneFriendlyName().getValue());
                            }
                        }
                ).collect(Collectors.toUnmodifiableMap(Entry::getKey, Entry::getValue));
    }
}
