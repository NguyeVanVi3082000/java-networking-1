package com.company.book1._1_stream._3_filter_stream;

import java.io.*;

public class Main {

    // input stream and output stream just read the raw bytes
    // deciding the those bytes mean -> is completely up to programmer and the code


    // 1. Chaining Filters Together
    // 2. Buffered Stream
        // A protected byte array field named buf until the buffer is full or the stream is flushed
        // A single write of many bytes is almost always much faster than many small writes that add up to the same thing
        // Only when the buffer runs out of data does the stream read from the underlying source.
        // At this point, it reads as much data as it can from the source into the buffer, whether it needs all the data immediately or not
    // 3. PrintStream
        // Other output stream can also be chained to print stream with constructor
        // Print streams should be explicitly flushed, but we can auto
            // auto flush when every time a byte array or linefeed is written or when println() method is invoked
        // 10 overload println() and 9 overload print()
            // converts its argument o a string in a predictable fashion and writes the string to underlying output stream using default encoding
        // Problem with PrintStream
            // -- Platform dependent : lines may sometimes be broken with a linefeed, a carriage return , console is okay, but network is not
            // -- Using default encoding of the platform on which it's running. But it may not be what server or client expects -> can not change the default encoding
            // -- PrintStream eats all exceptions. It relies on an outdated and inadequate error flag. Must check every call. Furthermore, once an error has occurred, there is no wat to
            //    unset a flag -> no error is detect
    // 4. DataStream
            // The Network Time Protocol (NTP) represents times as 64-bit unsigned fixed point numbers with the integer
            // part in the first 32 bits and the fraction part in the last 32 bits
    // 5. Reader and Writer
            // Many modern protocols, that allow a wide variety of localized encodings
            // And Java Native character set is UTF-18 encoding of Unicode -> when the encoding is no longer ASCII -> things breaks down
            // InputStreamReader: reads from raw bytes and translates bytes into Unicode characters
            // OutputStreamReader: receives unicode characters and then translate into bytes
            // Class that extends Reader and Write
                    // FileReader (work with files)
                    // FileWriter (work with files)
                    // StringReader (work inside java)
                    // StringWriter (work inside java)
                    // CharArrayWriter (work inside java)
                    // CharArrayReader (work inside java)
            // 1. Writer
                // How to write
                        // Note Other encodings may write still different sequences of bytes. The exact output depend on the encoding.
                // 1.1 OutputStreamWriter (Most important)
                    // Receives characters from a java program -> converts these into bytes according to a  specified encoding -> writes them onto an underling output stream
            // 2. Reader
                // The read() method returns a single Unicode character as an int with a value from 0 to 65,535 or –1 on end of stream
                // The read(char[] bytes)  tries to fill the array text with characters and returns the actual number of characters read or -1 for end of stream
                // The read(char[] bytes, int offset, int length)
                // mark(int aheadLimit), reset(), markSupported()
                // ready(): return boolean -> indicating whether the reader may  be read without blocking
                // available(): return int specifying a minimum number of bytes that may be read without blocking
            // 2.1 InputStreamReader (Most Important)
                // Reads bytes from underlying input stream (FileInputStream, TelnetInputStream) -> converts these into characters according to a specified encoding and return them
    //Question: what read() return mean ? -> Return the number represent
    // Filter Readers and Writers
        // BufferedReader, BufferedWriter: character-based equivalents of byte-oriented BufferedInputStream,BufferedOutputStream
            // When a program reads from a BufferedReader, text is taken from the buffer rather than directly from the underlying input stream or other text source
        // LineNumberReader, PrintWriter

        // PushBackReader

    // readLine() from DataOutStream deprecated
    // readLine() from InputStreamReader: . The big difference is that by chaining a
    //BufferedReader to an InputStreamReader, you can correctly read lines in character
    //sets other than the default encoding for the platform

    // 6. PrintWriter




    public static void main(String[] args) {

        // 1. Writer
        /*        try(FileWriter fileWriter = new FileWriter("data.txt")) {

                    writer1(fileWriter);

                } catch (IOException e) {
                    e.printStackTrace();
                }*/
        // 2. Reader
        try(FileReader fileReader = new FileReader("data.txt")) {

            reader1(fileReader);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    // Example of BufferReader
    public static String getMacCyrillicString(InputStream inputStream) throws IOException {
        Reader reader = new InputStreamReader(inputStream);
        reader = new BufferedReader(reader, 1024);
        StringBuilder stringBuilder = new StringBuilder();
        int c;
        while ((c = reader.read()) != -1 )
            stringBuilder.append((char) c);
        return stringBuilder.toString();
    }





    public static void reader1(Reader reader) throws IOException {
        int read = reader.read();
        System.out.println(read);
        System.out.println((char) read);

        // Char[] with 10 characters
        int start = 10;
        char[] outPut = new char[start];
        // Print byte in array
        for (int i = 0; i < outPut.length; i++)
        {

            // Empty array
                    // cast to int -> print 0
                    // cast to char ->  can not be copied
            System.out.println((char) outPut[i]);
        }

        int read1 = reader.read(outPut, 0, 5);
        System.out.println("After read first 5 bytes");
        System.out.println(outPut);
        int read2 = reader.read(outPut, 5, 5);
        System.out.println("After read  next 5 bytes");
        System.out.println(outPut);

        System.out.println("Read characters read 1: " + read1);
        System.out.println("Read characters read 2: " + read2);

        System.out.println(outPut);
    }
    public static void writer1(Writer w) throws IOException {

        // Method 1
        char[] network = {'N', 'e', 't', 'w', 'o', 'r', 'k'};
        w.write(network, 0, network.length);
        // The same result
        w.write(network);
        for (int i = 0; i < network.length; i++) w.write(network[i]);
        w.write("Network");
        w.write("Network", 0, 7);

        // Output in bytes hexa-decimal
        // big-endian UTF-16: 00 4E 00 65 00 74 00 77 00 6F 00 72 00 6B
        // little-endian UTF-16: 4E 00 65 00 74 00 77 00 6F 00 72 00 6B 00
        // UTF-8: 4E 65 74 77 6F 72 6B





    }





}
