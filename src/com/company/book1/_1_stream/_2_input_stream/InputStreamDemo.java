package com.company.book1._1_stream._2_input_stream;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamDemo {

    // ByteArrayInputStream: using pure java code that copies the byte from its array
    // TelnetInputStream: using native library that understands how to read data from  network
    public static void main(String[] args) {

    }

    public static void readFromSourceV1(InputStream in) throws IOException {

        // Case 1
        byte[] input =new byte[1024];
        for (int i = 0; i < input.length; i++)
        {
            // signed byte from -128 - 127
            // unsigned int  i = b >= 0 ? i : 256 + b;
            int b = in.read();
            if (b == -1) break;
            input[i] = (byte) b;
        }
        // Case 2
        // Bug 1: If something happen when the array is not filled -> in.read() -> Throw IOException
            // Fix
            byte[] input2 = new byte[1024];
            int byteRead = in.read(input2);
                // it attempts to read 1024 bytes from server. However if if only 512 bytes are available, that's all will be rad
        // Make sure you that all bytes you want are actually read,
        // place the read in a loop that read repeatedly until the array is filled
        // Case 3
        int bytesRead = 0;
        int bytesToRead = 1024;
        byte[] input1 = new byte[bytesToRead];
        while (byteRead < bytesToRead)
        {
            // return the number of actually byte read
            bytesRead = in.read(input1, byteRead, bytesToRead - byteRead);
            // if bytesRead = 10 -> read 10 byte ( 0 -> 9 offset) if continue to read -> write from 10 and available space is ...

        }
        // Case 4
        // Bug 2 It didn't consider the possibility that all 1024 bytes might never arrive, so it will wait until then ->  bug
        int bytesRead1 = 0;
        int bytesToRead1 = 1024;
        byte[] input3 = new byte[bytesRead1];
        while (bytesRead1 < bytesToRead1)
        {
            int result = in.read(input3, bytesRead1,bytesRead1 -  bytesRead1);
            if (result == -1) break;
            byteRead +=result;
        }

        // Case 5
        // if you don't want to wait until all the bytes you need are immediately available, -> using available()
        int bytesAvailable = in.available();
        byte[] input4 = new byte[bytesAvailable];
        int bytesRead4 = in.read(input1, 0, bytesAvailable);
        // process from byte input4

        // skip() less useful for network connection but reading files


    }
}
