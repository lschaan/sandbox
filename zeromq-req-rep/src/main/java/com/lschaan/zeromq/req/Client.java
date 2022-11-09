package com.lschaan.zeromq.req;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lschaan.zeromq.request.MultiplyRequest;
import com.lschaan.zeromq.response.MultiplyResponse;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.io.IOException;
import java.util.Scanner;

public class Client {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Scanner scanner = new Scanner(System.in);
    private static ZMQ.Socket socket;

    public static void main(String[] args) throws IOException {
        socket = connect();

        while (true) {
            System.out.println("Press any key to send request...");

            scanner.nextLine();
            sendRequest(10.0, 20.0);
            MultiplyResponse response = receiveReply();
            System.out.println("Reply received: " + response.getResult() + "\n");
        }
    }

    private static MultiplyResponse receiveReply() throws IOException {
        System.out.println("Waiting to receive reply...");
        String repString = socket.recvStr();
        return objectMapper.readValue(repString, MultiplyResponse.class);
    }

    private static void sendRequest(Double firstNumber, Double secondNumber) throws IOException {
        System.out.println("Sending request...");
        MultiplyRequest request = new MultiplyRequest(firstNumber, secondNumber);

        String reqString = objectMapper.writeValueAsString(request);
        socket.send(reqString);
        System.out.println("Request sent: " + reqString);
    }

    private static ZMQ.Socket connect() {
        SocketType socketType = SocketType.REQ;
        ZContext context = new ZContext();
        ZMQ.Socket socket = context.createSocket(socketType);
        socket.connect("tcp://*:5555");

        System.out.println("Connected!");
        return socket;
    }
}
