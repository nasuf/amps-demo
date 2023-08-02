package com.nasuf.amps.subscriber.queue;

import com.crankuptheamps.client.Client;
import com.crankuptheamps.client.Command;
import com.crankuptheamps.client.Message;
import com.crankuptheamps.client.exception.AMPSException;
import com.nasuf.amps.dto.MessagePrinter;

public class AmpsQueueSubscriber {
    public static final String URI = "tcp://10.211.55.6:9007/amps/json";

    public static void main(String[] args) {
        try (Client client = new Client("queue-subscriber")) {
            client.connect(URI);
            client.logon();

            Command command = new Command(Message.Command.Subscribe) // or pure SOW
                                    .setTopic("Messages")
//                                    .setFilter("/sender='son'")
                                    .setBatchSize(100);
            client.executeAsync(command, new MessagePrinter());
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
