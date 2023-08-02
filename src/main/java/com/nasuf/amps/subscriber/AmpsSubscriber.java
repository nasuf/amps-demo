package com.nasuf.amps.subscriber;

import com.crankuptheamps.client.Client;
import com.crankuptheamps.client.Command;
import com.crankuptheamps.client.Message;
import com.crankuptheamps.client.MessageStream;
import com.crankuptheamps.client.exception.AMPSException;

public class AmpsSubscriber {
    public static void main(String[] args) {
        try (Client client = new Client("subscribe")) {
            client.connect("tcp://10.211.55.6:9007/amps/json");
            client.logon();
            Command command = new Command("subscribe").setTopic("WorkToDo");
//            try (MessageStream ms = client.subscribe("messages"))
            try (MessageStream ms = client.execute(command)) {
                for (Message m : ms) {
//                    m.setAckType(Message.AckType.None);
                    System.out.println(m.getData());
                }
            }
        } catch (AMPSException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }
    }
}
