package com.nasuf.amps.dto;

import java.beans.ExceptionListener;

public class CustomExceptionListener implements ExceptionListener {
    @Override
    public void exceptionThrown(Exception e) {
        System.err.println(e.toString());
    }
}
