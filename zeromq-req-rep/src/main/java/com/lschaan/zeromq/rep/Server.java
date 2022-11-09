package com.lschaan.zeromq.rep;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lschaan.zeromq.request.MultiplyRequest;
import com.lschaan.zeromq.response.MultiplyResponse;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.io.IOException;

public class Server {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static ZMQ.Socket socket;

    public static void main(String[] args) throws IOException {
        socket = startServer();

        while (true) {
            System.out.println("Waiting for request...");
            MultiplyRequest request = receiveRequest();

            MultiplyResponse response = multiply(request.getFirstNumber(), request.getSecondNumber());
            String repString = sendReply(response);
            System.out.println("Reply sent: " + repString + "\n");
        }
    }

    private static String sendReply(MultiplyResponse response) throws IOException {
        String repString = objectMapper.writeValueAsString(response);
        socket.send(repString);
        return repString;
    }

    private static MultiplyRequest receiveRequest() throws IOException {
        String reqString = socket.recvStr();

        MultiplyRequest request = objectMapper.readValue(reqString, MultiplyRequest.class);
        System.out.println("Request received: " + reqString);
        return request;
    }

    private static MultiplyResponse multiply(Double firstNumber, Double secondNumber) {
        return new MultiplyResponse(firstNumber * secondNumber);
    }

    private static ZMQ.Socket startServer() {
        SocketType socketType = SocketType.REP;
        ZContext context = new ZContext();

        ZMQ.Socket socket = context.createSocket(socketType);

        socket.bind("tcp://*:5555");
        System.out.println("Server started!");
        return socket;
    }
}
