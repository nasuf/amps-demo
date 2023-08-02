package com.nasuf.amps.subscriber;

import com.crankuptheamps.client.*;
import com.crankuptheamps.client.exception.AMPSException;
import com.nasuf.amps.dto.ClientDisconnectionHandler;
import com.nasuf.amps.dto.CustomExceptionListener;
import com.nasuf.amps.dto.MessagePrinter;

public class AmpsAsyncSubscriber {

    public static final String URI = "tcp://10.211.55.6:9007/amps/json";

    public static void main(String[] args) {
        try (Client client = new Client("subscribe")) {
            client.connect(URI);
            client.logon();
            client.setDisconnectHandler(new ClientDisconnectionHandler(URI));
            client.setExceptionListener(new CustomExceptionListener());

            MessagePrinter messagePrinter = new MessagePrinter();
            Command command = new Command(Message.Command.SOWAndSubscribe)
                    .setTopic("Messages");
//                    .setFilter("/sender='dad'");
            client.executeAsync(command, messagePrinter);
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (AMPSException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }
    }
}
