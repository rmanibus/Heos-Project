package fr.lavachequicode.lib.upnp.model;

import lombok.Data;

@Data
public class BluetoothStatus {

    String connectedStatus;
    String connectedDevice;
    String pairedDevices;
    String hasPairedDevices;
    String connectionType;
}
