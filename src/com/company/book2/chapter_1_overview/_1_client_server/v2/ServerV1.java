package com.company.book2.chapter_1_overview._1_client_server.v2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ServerV1 {
    public static void main(String[] args) {

        System.out.println("Eco Server");

        try {
            ServerSocket socket = new ServerSocket(6000);
            System.out.println("Waiting for connection ... ");
            Socket accept = socket.accept();
            System.out.println("Connected !");
            // Get input stream for writing out
            InputStream inputStream = accept.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Get output stream to response back
            PrintWriter printWriter = new PrintWriter(accept.getOutputStream(), true);

            // Create a supplier
            Stream<String> generate = Stream.generate(() -> {
                try {
                    return reader.readLine();
                } catch (IOException e) {
                    return null;
                }
            });
            generate.map(item -> {
                System.out.println("Client Request : " + item);
                printWriter.println(item + " response ");
                return item;
            }).allMatch(item -> item != null);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
