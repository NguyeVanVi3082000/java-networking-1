package com.company.book2.chapter_1_overview._1_client_server.v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws UnknownHostException {




        // Test 1 4:05
        /*DESKTOP-LSEGFR1/172.20.224.1
        localhost/127.0.0.1*/
        System.out.println("Client : ");
        try {
            InetAddress inetAddress = InetAddress.getLoopbackAddress();
            System.out.println(inetAddress);
            Socket clientSock = new Socket(inetAddress, 6000);
            PrintWriter printWriter = new PrintWriter(clientSock.getOutputStream(), true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
            System.out.println("Connected to server : ");
            Scanner scanner = new Scanner(System.in);
            while (true)
            {
                System.out.println("Enter your message : ");
                String input = scanner.nextLine();
                if (input.equals("exit"))
                {
                    return;
                }
                printWriter.println(input);
                String response = bufferedReader.readLine();
                System.out.println("Server Response " + response);
            }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
