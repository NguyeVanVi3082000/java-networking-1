package com.company.book2.chapter_1_overview;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Main {

    // 1. Working with Channel, Buffer, Selector
    /*
    * What is a channel ?
    *   - a stream of data between applications
    * What is a buffer ?
    *   - hold temporary data for processing
    * What is a selector?
    *   -  for process multiple slow  channel in a single thread
    */
    // 2. Type of channel
    /*
    * FileChannel: This works with File
    * DataGramChannel: This support for UDP communication
    * SocketChannel: This work with TCP client
    * ServerSocketChannel: This work with TCP server
    * */
    // 3. The client/server architecture
    /*
    * - A server is, thus, identified by its combination of IP address and port number.
    * - For the server to communicate with client we need a special software call socket
    * - There are different types of socket :
    *   + datagram sockets
    *   + stream sockets TCP
    *   + raw sockets for work at ip level
    * */
    public static void main(String[] args) {
        example1("https://neu.edu.vn/");
    }
    // Example 1 Using Channel and Buffer
    public static void example1(String link)
    {
        try {
            URL  url = new URL(link);
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            ReadableByteChannel readableByteChannel = Channels.newChannel(inputStream);
            ByteBuffer byteBuffer = ByteBuffer.allocate(64);
            while (readableByteChannel.read(byteBuffer) > 0) {
                System.out.println(new String(byteBuffer.array()));
                byteBuffer.clear();
            }
            readableByteChannel.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
