package fr.lavachequicode.heos.sdk.actions;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
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

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

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
        if (method.getReturnType().equals(Void.class) || upnpActionAnnotation.out().length == 0) {
            return null;
        }
        if (upnpActionAnnotation.out().length == 1) {
            String response = callback.getInvocation().getOutputMap().get(upnpActionAnnotation.out()[0].name()).toString();
            if (String.class.equals(method.getReturnType())) {
                return response;
            }
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
            return xmlMapper.readValue(response, method.getReturnType());
        }
        if (String.class.equals(method.getReturnType())) {
            return callback.getInvocation().getOutputMap().toString();
        }
        final Object response = method.getReturnType().getConstructor().newInstance();
        for (Method setter : method.getReturnType().getMethods()) {
            if (setter.getName().startsWith("set")) {
                Object value = callback.getInvocation().getOutputMap().get(setter.getName().substring(3));
                if(value != null){
                    try {
                        Class<?> setterType = setter.getParameters()[0].getType();
                        if(String.class.equals(setterType)){
                            setter.invoke(response, value.toString());
                        }else if(Integer.class.equals(setterType)) {
                            setter.invoke(response, Integer.valueOf(value.toString()));
                        } else {
                            XmlMapper xmlMapper = new XmlMapper();
                            xmlMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
                            setter.invoke(response, xmlMapper.readValue(value.toString(), setterType));
                        }
                    } catch (Exception e) {
                        log.error("Failed to extract value for field {}", method.getName(), e);
                    }
                }
            }
        }
        return response;
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
