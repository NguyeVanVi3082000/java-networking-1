package com.company.book1._2_Thread._1_return_information_from_thread;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class InstanceCallbackDigest implements Runnable {

    private String filename;
    private InstanceCallbackUserInterface callback; // reference of who call run this task

    public InstanceCallbackDigest(String fileName, InstanceCallbackUserInterface callback) {
        this.filename = fileName;
        this.callback = callback;
    }


    @Override
    public void run() {
        try {
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);
            while (din.read() != -1) ; // read entire file
            din.close();
            byte[] digest = sha.digest();
            callback.receiveDigest(digest);
        } catch (IOException | NoSuchAlgorithmException ex) {
            System.err.println(ex);
        }
    }
}
