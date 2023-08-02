package com.nasuf.amps.subscriber.sow;

import com.crankuptheamps.client.Client;
import com.crankuptheamps.client.Command;
import com.crankuptheamps.client.CommandId;
import com.crankuptheamps.client.Message;
import com.crankuptheamps.client.exception.AMPSException;
import com.nasuf.amps.dto.ClientDisconnectionHandler;
import com.nasuf.amps.dto.CustomExceptionListener;
import com.nasuf.amps.dto.MessagePrinter;

public class AmpsSowSubscriber {
    public static final String URI = "tcp://10.211.55.6:9007/amps/json";

    public static void main(String[] args) {
        try (Client client = new Client("Subscriber-1")) {
            client.connect(URI);
            client.logon();

            for (Message m : client.sow("Messages", "/sender = 'mom'")) {
                if (m.getCommand() == Message.Command.GroupBegin) {
                    System.out.println("--- Begin SOW Results ---");
                }
                if (m.getCommand() == Message.Command.SOW) {
                    System.out.println(m.getData());
                }
                if (m.getCommand() == Message.Command.GroupEnd) {
                    System.out.println("--- End SOW Results ---");
                }
            }
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
