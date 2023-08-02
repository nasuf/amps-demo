package com.nasuf.amps.dto;

import com.crankuptheamps.client.Message;
import com.crankuptheamps.client.MessageHandler;
import org.apache.log4j.Logger;


public class MessagePrinter implements MessageHandler {
    final static Logger logger = Logger.getLogger(MessagePrinter.class);

    @Override
    public void invoke(Message message) {
        switch (message.getCommand()) {
            case Message.Command.SOW:
//                message.setIgnoreAutoAck();
//                message.setAckType(Message.AckType.None);
                System.out.println("SOW: " + message.getData());
                break;
            case Message.Command.Publish:
//                message.setAckType(Message.AckType.None);
                System.out.println("Publish: " + message.getData());
                break;
        }
    }
}
