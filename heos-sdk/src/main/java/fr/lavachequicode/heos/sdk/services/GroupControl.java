package fr.lavachequicode.heos.sdk.services;

import fr.lavachequicode.heos.sdk.model.GroupCurrentState;
import org.fourthline.cling.binding.annotations.*;
import org.fourthline.cling.model.types.ServiceId;

@UpnpService(
        serviceId = @UpnpServiceId(namespace = "denon-com", value = "GroupControl"),
        serviceType = @UpnpServiceType(namespace = "schemas-denon-com", value = "GroupControl", version = 1)
)
public interface GroupControl {

    ServiceId serviceId = new ServiceId("denon-com", "GroupControl");

    @UpnpAction(name = "SetDeviceFriendlyName")
    void setDeviceFriendlyName(@UpnpInputArgument(name = "DeviceFriendlyName") String deviceFriendlyName);

    @UpnpAction(name = "SetGroupFriendlyName")
    void setGroupFriendlyName(@UpnpInputArgument(name = "GroupUUID") String groupUUID,
                              @UpnpInputArgument(name = "GroupFriendlyName") String groupFriendlyName);

    @UpnpAction(name = "SetGroupBass")
    void SetGroupBass(@UpnpInputArgument(name = "GroupUUID") String groupUUID,
                      @UpnpInputArgument(name = "GroupBass") String groupBass);

    @UpnpAction(name = "SetGroupTreble")
    void setGroupTreble(@UpnpInputArgument(name = "GroupUUID") String groupUUID,
                        @UpnpInputArgument(name = "GroupTreble") String groupTreble);

    @UpnpAction(name = "SetGroupVolume")
    void setGroupVolume(@UpnpInputArgument(name = "GroupUUID") String groupUUID,
                        @UpnpInputArgument(name = "GroupVolume") String groupVolume,
                        @UpnpInputArgument(name = "CommandID") String commandID);

    @UpnpAction(name = "SetGroupBalance")
    void setGroupBalance(@UpnpInputArgument(name = "GroupUUID") String groupUUID,
                         @UpnpInputArgument(name = "GroupBalance") String groupBalance,
                         @UpnpInputArgument(name = "CommandID") String commandID);

    @UpnpAction(name = "DestroyGroup")
    void destroyGroup(@UpnpInputArgument(name = "GroupUUID") String groupUUID,
                      @UpnpInputArgument(name = "PreserveZone") boolean preserveZone);


    @UpnpAction(name = "SetGroupMute")
    void setGroupMute(@UpnpInputArgument(name = "GroupUUID") String groupUUID,
                      @UpnpInputArgument(name = "GroupMute") boolean groupMute,
                      @UpnpInputArgument(name = "CommandID") String commandID);

    @UpnpAction(name = "SetGroupMemberChannel")
    void setGroupMemberChannel(@UpnpInputArgument(name = "GroupUUID") String groupUUID,
                               @UpnpInputArgument(name = "AudioChannel") String audioChannel);

    @UpnpAction(name = "AddMembersToGroup", out = @UpnpOutputArgument(name = "GroupUUIDOut"))
    String addMembersToGroup(@UpnpInputArgument(name = "GroupUUID") String groupUUID,
                             @UpnpInputArgument(name = "GroupMemberIPList") String groupMemberIPList,
                             @UpnpInputArgument(name = "GroupMemberUUIDList") String groupMemberUUIDList,
                             @UpnpInputArgument(name = "GroupMemberChannelList") String groupMemberChannelList);

    @UpnpAction(name = "RemoveMembersFromGroup")
    String RemoveMembersFromGroup(@UpnpInputArgument(name = "GroupUUID") String groupUUID,
                                  @UpnpInputArgument(name = "GroupMemberUUIDList") String groupMemberUUIDList);

    @UpnpAction(name = "CreateGroup", out = @UpnpOutputArgument(name = "GroupUUID"))
    String createGroup(@UpnpInputArgument(name = "GroupFriendlyName") String groupFriendlyName,
                       @UpnpInputArgument(name = "GroupMemberUUIDList") String groupMemberUUIDList,
                       @UpnpInputArgument(name = "GroupMemberChannelList") String groupMemberChannelList);

    @XML
    @UpnpAction(name = "GetCurrentState", out = @UpnpOutputArgument(name = "CurrentState"))
    GroupCurrentState getCurrentState();

    @UpnpAction(name = "GetGroupMemberChannel", out = @UpnpOutputArgument(name = "AudioChannel"))
    String getGroupMemberChannel(@UpnpInputArgument(name = "GroupUUID") String groupUUID);

    @UpnpAction(name = "GetGroupBalance", out = @UpnpOutputArgument(name = "GroupBalance"))
    String getGroupBalance(@UpnpInputArgument(name = "GroupUUID") String groupUUID);

    @UpnpAction(name = "GetConfigDeviceUUID", out = @UpnpOutputArgument(name = "ConfigDeviceUUID"))
    String getConfigDeviceUUID(@UpnpInputArgument(name = "GroupUUID") String groupUUID);

    @UpnpAction(name = "GetSignalStrength", out = @UpnpOutputArgument(name = "SignalStrength"))
    String getSignalStrength(@UpnpInputArgument(name = "GroupUUID") String groupUUID);

    @UpnpAction(name = "GetGroupVolume", out = @UpnpOutputArgument(name = "GroupVolume"))
    String getGroupVolume(@UpnpInputArgument(name = "GroupUUID") String groupUUID);


    @UpnpAction(name = "GetGroupUpdating", out = @UpnpOutputArgument(name = "GroupUpdating"))
    String getGroupUpdating(@UpnpInputArgument(name = "GroupUUID") String groupUUID);

    @UpnpAction(name = "GetDeviceFriendlyName", out = @UpnpOutputArgument(name = "DeviceFriendlyName"))
    String getDeviceFriendlyName();

    @UpnpAction(name = "GetGroupBass", out = @UpnpOutputArgument(name = "GroupBass"))
    String getGroupBass(@UpnpInputArgument(name = "GroupUUID") String groupUUID);

    @UpnpAction(name = "GetGroupStatus", out = @UpnpOutputArgument(name = "GroupStatus"))
    String getGroupStatus(@UpnpInputArgument(name = "GroupUUID") String groupUUID);

    @UpnpAction(name = "GetGroupTreble", out = @UpnpOutputArgument(name = "GroupTreble"))
    String getGroupTreble(@UpnpInputArgument(name = "GroupUUID") String groupUUID);

    @UpnpAction(name = "GetMediaServerUUID", out = @UpnpOutputArgument(name = "MediaServerUUID"))
    String getMediaServerUUID(@UpnpInputArgument(name = "GroupUUID") String groupUUID);

    @UpnpAction(name = "GetGroupUUID", out = @UpnpOutputArgument(name = "GroupUUID"))
    String getGroupUUID();

    @UpnpAction(name = "GetGroupFriendlyName", out = @UpnpOutputArgument(name = "GroupFriendlyName"))
    String getGroupFriendlyName(@UpnpInputArgument(name = "GroupUUID") String groupUUID);

    @UpnpAction(name = "GetGroupMute", out = @UpnpOutputArgument(name = "GroupMute"))
    String getGroupMute(@UpnpInputArgument(name = "GroupUUID") String groupUUID);

    @UpnpAction(name = "GetGroupMemberList", out = @UpnpOutputArgument(name = "GroupMemberUUIDList"))
    String getGroupMemberList(@UpnpInputArgument(name = "GroupUUID") String groupUUID);

}
