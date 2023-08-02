package com.nasuf.amps.publisher;

import com.crankuptheamps.client.Client;
import com.crankuptheamps.client.exception.AMPSException;
import com.nasuf.amps.dto.CustomFailureWriteHandler;

public class AmpsPublisher {

    public static final String URI_A = "tcp://10.211.55.6:9007/amps/json";
    public static final String URI_B = "tcp://10.211.55.8:9007/amps/json";

    public static void main(String[] args) {
        Client client = new Client("Publisher-1");
        try {
            client.connect(URI_A);
            client.logon();
            client.setFailedWriteHandler(new CustomFailureWriteHandler());


            for (int i = 0; i < 5000; i++) {
                if (i % 2 ==  0) {
                    String msg = "{\n" +
                            "    \"sender\" : \"A\",\n" +
                            "    \"text\" : \"Count now: " + i + "\",\n" +
                            "    \"msg\" : \"Call me Thursday!\"\n" +
                            "}";
                    client.publish("Messages", msg);
                    System.out.println("published: " + msg);
                } else {
                    String msg = "{\n" +
                            "    \"sender\" : \"B\",\n" +
                            "    \"text\" : \"Count now: " + i + "\",\n" +
                            "    \"msg\" : \"Call me Thursday!\"\n" +
                            "}";
                    client.publish("Messages", msg);
                    System.out.println("published: " + msg);
                }

                Thread.sleep(1000);
            }
        } catch (AMPSException e) {
            System.err.println("got exception");
        } catch (Exception e) {
            System.err.println("got other exception");
        } finally {
            client.close();
        }
    }
}

/**
 *    topic
 *      |__ subscriber A (Y)
 *      |__ subscriber B (Y)
 */