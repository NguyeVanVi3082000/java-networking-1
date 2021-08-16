package com.company.book2.chapter_1_overview._2_multicast;

import java.io.IOException;
import java.net.*;
import java.util.Date;

public class MulticastServer {


    // 1. Multicast
    /*
    * - Multicasting will send an identical message to every member in the group
    * - A group is identified by multicast address. In the range  224.0.0.0 through 239.255.255.255
    * -
    * */
    public static void main(String[] args) {


        System.out.println("Multicast time server: ");
        DatagramSocket datagramSocket  = null;
        try {
            datagramSocket = new DatagramSocket();
            // body an array of byte to hold a date time
            while (true)
            {
                String dateText = new Date().toString();
                byte[] buffer = dateText.getBytes();
                InetAddress group = InetAddress.getByName("224.0.0.0");
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, group, 8888);
                Thread.sleep(2000);
                datagramSocket.send(datagramPacket);
                System.out.println("Time sent: " + dateText);

            }
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
