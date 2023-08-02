package com.nasuf.amps.dto;

import com.crankuptheamps.client.Client;
import com.crankuptheamps.client.ClientDisconnectHandler;
import com.crankuptheamps.client.Command;
import com.crankuptheamps.client.CommandId;

public class ClientDisconnectionHandler implements ClientDisconnectHandler {
    private String uri;

    public ClientDisconnectionHandler(String uri) {
        this.uri = uri;
    }

    @Override
    public void invoke(Client client) {
        try {
            client.connect(uri);
            client.logon();
            MessagePrinter messagePrinter = new MessagePrinter();
            Command command = new Command("subscribe").setTopic("messages").setFilter("/sender='dad'");
            CommandId subscriptionId = client.executeAsync(command, messagePrinter);
            Thread.sleep(5000);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
