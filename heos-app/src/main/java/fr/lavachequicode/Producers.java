package fr.lavachequicode;

import org.fourthline.cling.transport.Router;
import org.fourthline.cling.transport.RouterImpl;
import org.fourthline.cling.transport.impl.DatagramProcessorImpl;
import org.fourthline.cling.transport.spi.DatagramProcessor;

import javax.enterprise.inject.Produces;

public class Producers {

    @Produces
    DatagramProcessor produceDatagramProcessor(){
        return new DatagramProcessorImpl();
    }

}
