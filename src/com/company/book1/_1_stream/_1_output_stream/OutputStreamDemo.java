package com.company.book1._1_stream._1_output_stream;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{"+
                "name='"+name+'\''+
                ", age="+age+
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class OutputStreamDemo {

    public static void main(String[] args) throws IOException, InterruptedException {

        // byte in java is a signed integer based on the two's complement 8 bit
        // -128 <-> 127
        // write in this: ASCII a char is in represent in 7 bit -> can be sent in a single byte
        // Example 1 write single byte
        /*try (OutputStreamDemo out = new FileOutputStream("data.txt")) {
            generateCharactersV1(out);
        }
        */
        // Example 2 write
        /*try (java.io.OutputStream outputStream = new FileOutputStream("data.txt")) {
            generateCharacterV0(outputStream);
        }*/
        // Example 3 Wake Up  Spleeping Thread
        /*Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("Start Working " + i);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        System.err.println("Some one call me ");
                    }
                }
            }
        });
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();*/
        // Example 4 Joining Thread and Interrupt
        /*System.out.println("Start Executing ..... ");
        Thread t = new Thread(() -> {
            System.out.println("Start The Child Thread ");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Some on call me");
            }
        });

        Thread t1 = new Thread(() -> {
            System.out.println("Start The Child Thread 1");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Interrupt The Thread");
            t.interrupt();
        });
        t.start();
        t1.start();
        t.join();

        // Normal Case
        System.out.println("Return Executing For The Main Thread");*/
        // Example 5 Wait() and notify(), notifyAll()
            // Example 5.1 Thread 1 Calculate sum until equal something -> call Thread 2 to continue to process. And in the mean while the thread 1 will continue to do the job
        /*Scanner scanner = new Scanner(System.in);
        Person p = new Person();
        // It will wait on Person object. As soon as the name is fill -> notifyAll -> print the name of Person
        // The interested thread could create a custom ManifestFile object  and Pass a reference to this object and waiting on it
        // -> Thread 2 create Person
        Thread t = new Thread(() -> {
            // Enter name for a person
            synchronized (p) {
                System.out.println("In the first thread :");
                System.out.println("Enter the name : ");
                p.setName(scanner.nextLine());
                // As son as, name is entered -> notify for all thread
                p.notifyAll();
            }
            System.out.println("Enter the age: ");
            p.setAge(scanner.nextInt());
        });

        Thread t2 = new Thread(() -> {
            synchronized (p) {
                System.out.println("In the second thread :");
                System.out.println("     "+p.getName());
            }
        });

        synchronized (p) {
            t.start();
            try {
                p.wait();
                // Start a thread to work with Person
                t2.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

    }

    // -> take in an integer -> write as ASCII
    public static void generateCharacterV0(OutputStream outputStream) throws IOException {
        outputStream.write(124);
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
