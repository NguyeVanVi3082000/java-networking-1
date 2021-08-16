package com.company.book2.chapter_1_overview._1_client_server.v1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class Server {
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
            String readLine;
            while ((readLine = reader.readLine()) != null)
            {
                System.out.println("Server : " + readLine);
                printWriter.println(readLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
