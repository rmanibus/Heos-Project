package fr.lavachequicode.lib.upnp.services;

import fr.lavachequicode.lib.upnp.model.ConnectionCurrentState;
import fr.lavachequicode.lib.upnp.model.ProtocolInfo;
import org.fourthline.cling.binding.annotations.*;
import org.fourthline.cling.model.ServiceReference;
import org.fourthline.cling.model.types.ServiceId;
import org.fourthline.cling.model.types.UDAServiceId;

@UpnpService(
        serviceId = @UpnpServiceId("ConnectionManager"),
        serviceType = @UpnpServiceType(value = "ConnectionManager", version = 1)
)
public interface ConnectionManager {

    ServiceId serviceId = new UDAServiceId("ConnectionManager");

    @XML
    @UpnpAction(name = "GetCurrentState", out = @UpnpOutputArgument(name = "CurrentState"))
    ConnectionCurrentState getCurrentState();

    @UpnpAction(name = "GetCurrentConnectionIDs", out = @UpnpOutputArgument(name = "ConnectionIDs"))
    String getCurrentConnectionIDs();

    @UpnpAction(name = "ConnectionComplete")
    void connectionComplete(@UpnpInputArgument(name = "ConnectionID") String connectionID);

    @UpnpAction(name = "GetProtocolInfo", out = {@UpnpOutputArgument(name = "Source"), @UpnpOutputArgument(name = "Sink")})
    String getProtocolInfo();

    @UpnpAction(name = "PrepareForConnection", out = {
            @UpnpOutputArgument(name = "ConnectionID", stateVariable = "A_ARG_TYPE_ConnectionID", getterName = "getConnectionID"),
            @UpnpOutputArgument(name = "AVTransportID", stateVariable = "A_ARG_TYPE_AVTransportID", getterName = "getAvTransportID"),
            @UpnpOutputArgument(name = "RcsID", stateVariable = "A_ARG_TYPE_RcsID", getterName = "getRcsID")
    })
    String prepareForConnection(
            @UpnpInputArgument(name = "RemoteProtocolInfo", stateVariable = "A_ARG_TYPE_ProtocolInfo") ProtocolInfo remoteProtocolInfo,
            @UpnpInputArgument(name = "PeerConnectionManager", stateVariable = "A_ARG_TYPE_ConnectionManager") ServiceReference peerConnectionManager,
            @UpnpInputArgument(name = "PeerConnectionID", stateVariable = "A_ARG_TYPE_ConnectionID") int peerConnectionId,
            @UpnpInputArgument(name = "Direction", stateVariable = "A_ARG_TYPE_Direction") String direction);

    @UpnpAction(name = "GetCurrentConnectionInfo", out = {
            @UpnpOutputArgument(name = "RcsID", getterName = "getRcsID"),
            @UpnpOutputArgument(name = "AVTransportID", getterName = "getAvTransportID"),
            @UpnpOutputArgument(name = "ProtocolInfo", getterName = "getProtocolInfo"),
            @UpnpOutputArgument(name = "PeerConnectionManager", stateVariable = "A_ARG_TYPE_ConnectionManager", getterName = "getPeerConnectionManager"),
            @UpnpOutputArgument(name = "PeerConnectionID", stateVariable = "A_ARG_TYPE_ConnectionID", getterName = "getPeerConnectionID"),
            @UpnpOutputArgument(name = "Direction", getterName = "getDirection"),
            @UpnpOutputArgument(name = "Status", stateVariable = "A_ARG_TYPE_ConnectionStatus", getterName = "getConnectionStatus")
    })
    String getCurrentConnectionInfo(@UpnpInputArgument(name = "ConnectionID") int connectionId);

}
