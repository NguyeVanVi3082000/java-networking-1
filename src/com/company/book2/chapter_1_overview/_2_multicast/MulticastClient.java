package com.company.book2.chapter_1_overview._2_multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastClient {
    public static void main(String[] args) {
        System.out.println("Multicast client : ");

        try {
            MulticastSocket multicastSocket = new MulticastSocket(8888);
            InetAddress group = InetAddress.getByName("224.0.0.0");
            multicastSocket.joinGroup(group);

            byte[] buffer = new byte[256];
            DatagramPacket datagramPacket =  new DatagramPacket(buffer, buffer.length);
            for (int i = 0; i < 5; i++) {
                multicastSocket.receive(datagramPacket);
                String received = new String(datagramPacket.getData());
                System.out.println(received.trim());
            }
            multicastSocket.leaveGroup(group);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
