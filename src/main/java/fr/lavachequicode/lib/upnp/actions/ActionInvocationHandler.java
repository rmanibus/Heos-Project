package fr.lavachequicode.lib.upnp.actions;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import fr.lavachequicode.lib.upnp.services.XML;
import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.binding.annotations.UpnpAction;
import org.fourthline.cling.binding.annotations.UpnpInputArgument;
import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.meta.Action;
import org.fourthline.cling.model.meta.Service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;

@Slf4j
public class ActionInvocationHandler implements InvocationHandler {

    final Service service;
    final UpnpService upnpService;

    public ActionInvocationHandler(UpnpService upnpService, Service service) {
        this.service = service;
        this.upnpService = upnpService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        UpnpAction upnpActionAnnotation = method.getAnnotation(UpnpAction.class);
        Action action = service.getAction(coalesc(upnpActionAnnotation.name(), method.getName()));
        ActionInvocation actionInvocation = new ActionInvocation(action);

        int index = 0;
        for (Parameter parameter : method.getParameters()) {
            UpnpInputArgument inputArgumentAnnotation = parameter.getAnnotation(UpnpInputArgument.class);
            if (inputArgumentAnnotation != null) {
                actionInvocation.setInput(inputArgumentAnnotation.name(), args[index]);
            }
            index++;
        }
        GenericActionCallback callback = new GenericActionCallback(actionInvocation);
        upnpService.getControlPoint().execute(callback).get();
        if (method.getReturnType().equals(Void.class)) {
            return null;
        }
        //Object o = method.getReturnType().getConstructor().newInstance();
        //log.info("generated {}", o);
        if (upnpActionAnnotation.out().length == 1) {

            String response = callback.getInvocation().getOutputMap().get(upnpActionAnnotation.out()[0].name()).toString();
            XML xml = method.getAnnotation(XML.class);
            if(xml == null){
                return response;
            }
            XmlMapper xmlMapper = new XmlMapper();
            return xmlMapper.readValue(response, method.getReturnType());
        }
        return callback.getInvocation().getOutputMap().toString();
    }

    private static String coalesc(String... values) {
        return Arrays.stream(values).filter(value -> value != null && value.length() > 0).findFirst().orElse("");
    }

    public static <T> T createProxy(UpnpService upnpService, Service service, Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                ActionInvocationHandler.class.getClassLoader(),
                new Class[]{clazz},
                new ActionInvocationHandler(upnpService, service));
    }

}
