package services;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;

public class ConsoleLoggerHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext soapMessageContext) {
        Boolean outboundProperty = (Boolean)soapMessageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outboundProperty.booleanValue()) {
            System.out.println("\nOutbound message:");
        } else {
            System.out.println("\nInbound message:");
        }

        SOAPMessage message = soapMessageContext.getMessage();
        try {
            message.writeTo(System.out);
            System.out.println("");   // just to add a newline
        } catch (Exception e) {
            System.out.println("Exception in handler: " + e);
        }

        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext soapMessageContext) {
        return false;
    }

    @Override
    public void close(MessageContext messageContext) {

    }
}