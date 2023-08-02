package com.nasuf.amps.dto;

import com.crankuptheamps.client.FailedWriteHandler;
import com.crankuptheamps.client.Message;

public class CustomFailureWriteHandler implements FailedWriteHandler {
    @Override
    public void failedWrite(Message message, int reason) {
        System.out.println("message: " + message + ", reason: " + reason);
    }
}
