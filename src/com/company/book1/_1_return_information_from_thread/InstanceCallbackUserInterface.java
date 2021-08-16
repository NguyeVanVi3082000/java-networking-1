package com.company.book1._1_return_information_from_thread;

import java.util.Arrays;

public class InstanceCallbackUserInterface {

    private String fileName;
    private byte[] digest;

    public InstanceCallbackUserInterface(String fileName) {
        this.fileName = fileName;
    }

    public void calculateDigest()
    {
        // When we create an instance of InstanceCallbackUserInterface -> calculate
        InstanceCallbackDigest instanceCallbackDigest = new InstanceCallbackDigest(fileName, this);
        Thread thread = new Thread(instanceCallbackDigest);
        thread.start();
        // This instance create an thread and pass this reference to it. When the thread is done.
        // It call method of this referenceeeeeeee
    }
    public void receiveDigest(byte[] digest)
    {
        // do something when receive
        this.digest = digest;
        System.out.println(this.digest);
    }

    public static void main(String[] args) {
        for (String filename : args)
        {
            InstanceCallbackUserInterface instanceCallbackUserInterface = new InstanceCallbackUserInterface(filename);
            instanceCallbackUserInterface.calculateDigest();

        }
    }

    @Override
    public String toString() {
        return "InstanceCallbackUserInterface{"+
                "fileName='"+fileName+'\''+
                ", digest="+Arrays.toString(digest)+
                '}';
    }
}
