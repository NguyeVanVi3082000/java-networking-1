package com.company.book1._1_stream._1_output_stream;

import java.io.FileOutputStream;
import java.io.IOException;

public class OutputStreamDemo {

    public static void main(String[] args) throws IOException {

        // byte in java is a signed integer based on the two's complement 8 bit
        // -128 <-> 127
        // write in this: ASCII a char is in represent in 7 bit -> can be sent in a single byte


        // Example 1 write single byte
        /*try (OutputStreamDemo out = new FileOutputStream("data.txt")) {
            generateCharactersV1(out);
        }
        */
        // Example 2 write
        try (java.io.OutputStream outputStream = new FileOutputStream("data.txt")) {
            generateCharacterV2(outputStream);
        }

    }


    public static void generateCharacterV2(java.io.OutputStream out) throws IOException {

        // Write(byte[])
            // Something to write
            // fill this to array of byte
            // using write
        int first = 33;
        int numberOfPrint = 94;
        int numberOfCharPerLine = 72;

        int start = first;
        byte[] line = new byte[numberOfCharPerLine+2];
        while (true) {
            for (int i = start; i < start+numberOfCharPerLine; i++) {
                line[i-start] = (byte) ((i-first)%numberOfCharPerLine+first);
            }
            line[72] = (byte) '\r';
            line[73] = (byte) '\n';
            out.write(line);
            start = ((start+1)-first)%numberOfPrint+first;
        }
    }

    public static void generateCharactersV1(java.io.OutputStream out) throws IOException {
        int first = 33;
        int numberOfPrint = 94;
        int numberOfCharPerLine = 72;

        int start = first;
        while (true) {
            for (int i = start; i < start+numberOfCharPerLine; i++) {
                out.write((i-first)%numberOfPrint+first);
            }
            out.write('\r');
            out.write('\n');
            start = ((start+1)-first)%numberOfCharPerLine+first;
        }
    }

}
