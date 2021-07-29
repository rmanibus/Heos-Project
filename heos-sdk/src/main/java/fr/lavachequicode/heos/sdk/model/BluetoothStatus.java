package fr.lavachequicode.heos.sdk.model;

import lombok.Data;

@Data
public class BluetoothStatus {

    String connectedStatus;
    String connectedDevice;
    String pairedDevices;
    String hasPairedDevices;
    String connectionType;
}
