package com.company.book1._2_Thread._1_return_information_from_thread;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CallbackDigest implements Runnable{
    private String fileName;
    private byte[] digest;

    public CallbackDigest(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getDigest() {
        return digest;
    }

    @Override
    public void run() {
        try {
            FileInputStream in = new FileInputStream(fileName);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream digestInputStream = new DigestInputStream(in, sha);
            while (digestInputStream.read() != -1)
            digestInputStream.close();
            digest = sha.digest();
            // call back base on static method
            CallbackDigestUserInterface.receiveDigest(digest, fileName);
        } catch (FileNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
