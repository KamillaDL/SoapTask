import services.BookServiceImpl;
import services.ConsoleLoggerHandler;

import javax.xml.ws.Endpoint;
import javax.xml.ws.handler.Handler;
import java.util.List;


public class Application {
    public static void main(String[]args) {
        Endpoint ep = Endpoint.create(new BookServiceImpl());
        List<Handler> handlerChain = ep.getBinding().getHandlerChain();
        handlerChain.add(new ConsoleLoggerHandler());
        ep.getBinding().setHandlerChain(handlerChain);
        ep.publish("http://localhost:8080/bookservice");
    }
}
