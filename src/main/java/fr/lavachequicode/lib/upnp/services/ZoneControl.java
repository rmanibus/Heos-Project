package fr.lavachequicode.lib.upnp.services;

import fr.lavachequicode.lib.upnp.model.ZoneCurrentState;
import org.fourthline.cling.binding.annotations.*;
import org.fourthline.cling.model.types.ServiceId;
import org.fourthline.cling.model.types.UDAServiceId;

@UpnpService(
        serviceId = @UpnpServiceId(namespace = "denon-com", value = "ZoneControl"),
        serviceType = @UpnpServiceType(namespace = "schemas-denon-com", value = "ZoneControl", version = 1)
)
public interface ZoneControl {

    ServiceId serviceId = new ServiceId("denon-com", "ZoneControl");

    @UpnpAction(name = "GetZoneStatus", out = @UpnpOutputArgument(name = "ZoneStatus"))
    String GetZoneStatus(@UpnpInputArgument(name = "ZoneUUID") String zoneUUID);

    @UpnpAction(name = "DestroyZone")
    void destroyZone(@UpnpInputArgument(name = "ZoneUUID") String zoneUUID);


    @UpnpAction(name = "CreateZone", out = @UpnpOutputArgument(name = "ZoneUUID"))
    String createZone(@UpnpInputArgument(name = "ZoneFriendlyName") String zoneFriendlyName,
                           @UpnpInputArgument(name = "GroupIP") String groupIP);

    @UpnpAction(name = "AddMemberToZone", out = @UpnpOutputArgument(name = "GroupUUIDOut"))
    String addMemberToZone(@UpnpInputArgument(name = "ZoneUUID") String zoneUUID,
                             @UpnpInputArgument(name = "GroupIP") String groupIP);

    @UpnpAction(name = "RemoveMemberFromZone", out = @UpnpOutputArgument(name = "GroupUUIDOut"))
    String removeMemberFromZone(@UpnpInputArgument(name = "ZoneUUID", stateVariable = "A_ARG_TYPE_ZoneUUID") String zoneUUID,
                             @UpnpInputArgument(name = "GroupIP", stateVariable = "A_ARG_TYPE_GroupIP") String groupIP);

    @UpnpAction(name = "SetZoneFriendlyName")
    void setZoneFriendlyName(@UpnpInputArgument(name = "ZoneUUID") String zoneUUID,
                             @UpnpInputArgument(name = "ZoneFriendlyName") String zoneFriendlyName);

    @UpnpAction(name = "SetZoneVolume")
    void setZoneVolume(@UpnpInputArgument(name = "ZoneUUID") String zoneUUID,
                             @UpnpInputArgument(name = "ZoneVolume") String zoneVolume);

    @XML
    @UpnpAction(name = "GetCurrentState", out = @UpnpOutputArgument(name = "CurrentState"))
    ZoneCurrentState getCurrentState();

    @UpnpAction(name = "GetMemberStatus", out = @UpnpOutputArgument(name = "ZoneMemberStatusList"))
    String getMemberStatus(@UpnpInputArgument(name = "ZoneUUID") String zoneUUID);

    @UpnpAction(name = "GetZoneMute", out = @UpnpOutputArgument(name = "ZoneMute"))
    String getZoneMute(@UpnpInputArgument(name = "ZoneUUID") String zoneUUID);

    @UpnpAction(name = "GetZoneUUID", out = @UpnpOutputArgument(name = "ZoneUUID"))
    String getZoneUUID();

    @UpnpAction(name = "GetZoneVolume", out = @UpnpOutputArgument(name = "ZoneVolume"))
    String getZoneVolume(@UpnpInputArgument(name = "ZoneUUID") String zoneUUID);

    @UpnpAction(name = "SetZoneMute")
    void setZoneMute(@UpnpInputArgument(name = "ZoneUUID") String zoneUUID,
                      @UpnpInputArgument(name = "GroupMute") boolean groupMute);


    @UpnpAction(name = "GetZoneMemberList", out = @UpnpOutputArgument(name = "ZoneMemberList"))
    String GetZoneMemberList(@UpnpInputArgument(name = "ZoneUUID") String zoneUUID);

    @UpnpAction(name = "SetZoneMinimise")
    void setZoneMinimise(@UpnpInputArgument(name = "ZoneUUID") String zoneUUID,
                     @UpnpInputArgument(name = "ZoneMinimise") boolean zoneMinimise);

    @UpnpAction(name = "GetZoneMinimise", out = @UpnpOutputArgument(name = "ZoneMinimise"))
    String getZoneMinimise(@UpnpInputArgument(name = "ZoneUUID") String zoneUUID);

    @UpnpAction(name = "TestZoneConnectivity", out = {@UpnpOutputArgument(name = "SupportedGroupList"), @UpnpOutputArgument(name = "SupportedMediaType")})
    String testZoneConnectivity(@UpnpInputArgument(name = "ZoneIPList") String zoneIPList);

    @UpnpAction(name = "GetZoneConnectedList", out = @UpnpOutputArgument(name = "ZoneConnectedList"))
    String getZoneConnectedList(@UpnpInputArgument(name = "ZoneUUID") String zoneUUID);

    @UpnpAction(name = "GetZoneFriendlyName", out = @UpnpOutputArgument(name = "ZoneFriendlyName"))
    String getZoneFriendlyName(@UpnpInputArgument(name = "ZoneUUID") String zoneUUID);

}
