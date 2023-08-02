package com.nasuf.amps.subscriber.sow;

import com.crankuptheamps.client.Client;
import com.crankuptheamps.client.Command;
import com.crankuptheamps.client.Message;
import com.crankuptheamps.client.exception.AMPSException;
import com.nasuf.amps.dto.MessagePrinter;

public class AmpsAsyncSowSubscriber2 {
    public static final String URI_A = "tcp://10.211.55.6:9007/amps/json";
    public static final String URI_B = "tcp://10.211.55.8:9007/amps/json";

    public static void main(String[] args) {
        try (Client client = new Client("subscriber-2")) {
            client.connect(URI_A);
            client.logon();
            client.setAutoAck(true);

            Command command = new Command(Message.Command.SOWAndSubscribe) // or pure SOW
                                    .setTopic("Messages") // or conflation topic Messages-c
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
