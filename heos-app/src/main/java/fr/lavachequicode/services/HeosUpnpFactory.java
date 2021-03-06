package fr.lavachequicode.services;

import fr.lavachequicode.heos.sdk.actions.ActionInvocationHandler;
import fr.lavachequicode.heos.sdk.services.AVTransport;
import fr.lavachequicode.heos.sdk.services.GroupControl;
import fr.lavachequicode.heos.sdk.services.ZoneControl;
import lombok.SneakyThrows;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.model.types.ServiceId;
import org.fourthline.cling.model.types.UDN;
import org.fourthline.cling.registry.Registry;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.lang.reflect.Field;

@ApplicationScoped
public class HeosUpnpFactory {

    @Inject
    UpnpService upnpService;
    @Inject
    Registry registry;

    @SneakyThrows
    public <T> T createProxy(UDN udn, Class<T> tClass) {
        final Device device = registry.getDevice(udn, false);
        if (device == null) {
            throw new NotFoundException();
        }
        Field serviceIdField = tClass.getField("serviceId");
        return ActionInvocationHandler.createProxy(upnpService, device.findService((ServiceId) serviceIdField.get(null)), tClass);
    }
    public <T> T createProxy(Service service, Class<T> tClass) {
        return ActionInvocationHandler.createProxy(upnpService, service, tClass);
    }

  protected AVTransport getAvTransport(UDN udn) {
    final Device device = registry.getDevice(udn, false);
    if (device == null) {
      throw new NotFoundException();
    }
    return createProxy(device.findService(AVTransport.serviceId), AVTransport.class);
  }

  protected ZoneControl getZoneControl(UDN udn) {
    final Device device = registry.getDevice(udn, false);
    if (device == null) {
      throw new NotFoundException();
    }
    return createProxy(device.findService(ZoneControl.serviceId), ZoneControl.class);
  }

  protected GroupControl getGroupControl(UDN udn) {
    final Device device = registry.getDevice(udn, false);
    if (device == null) {
      throw new NotFoundException();
    }
    return createProxy(device.findService(GroupControl.serviceId), GroupControl.class);
  }
}
