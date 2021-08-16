package com.company.book1._1_return_information_from_thread;

public class Main {

    public static void main(String[] args) {



        // Approach 1
            // -> NullPointerException: The main program gets the digest and use it before the thread had a chance to initialized it.
            // It may work in single thread program
            // The calculations that dr.start() kicks off may or may not finish before the main() method reaches the call to dr.getDigest()
        CallbackDigest[] instanceCallBackDigests = new CallbackDigest[args.length];


        for (String fileName : args)
        {
            CallbackDigest instanceCallBackDigest = new CallbackDigest(fileName);
            Thread t = new Thread(instanceCallBackDigest);
            t.start();

            // Now print the result
            StringBuilder result = new StringBuilder(fileName);
            result.append(": ");
            byte[] digest = instanceCallBackDigest.getDigest();
            result.append(digest);
            System.out.println(result);
        }

        // Approach 2 - Race Condition
            // The result may be correct or not, the program may be hangup. If the first for loop is too fast and the second for loop
            // is entered before the threads spawned by the first loop start finishing, you’re back where you started.
            // The race condition: Getting the correct result depends on the relative speeds of different thread and you can't control those !
        for (int i = 0; i < args.length; i++) {
            instanceCallBackDigests[i] = new CallbackDigest(args[i]);
            Thread t = new Thread(instanceCallBackDigests[i]);
        }
        for (int i = 0; i < args.length; i++) {
            StringBuilder result = new StringBuilder();
            result.append(": ");
            byte[] digest = instanceCallBackDigests[i].getDigest();
            result.append(digest);
            System.out.println(result);
        }
        // Approach 3 - Polling
            // It gives the correct result in the correct order but, it's doing a lot of work than it need to do
            // The main thread can take all the available time and leaves no time for the actual worker threads. The main thread to busy to check whether the thread is completed
        for (int i = 0; i < args.length; i++) {
            while (true) {
                // Now print the result
                byte[] digest = instanceCallBackDigests[i].getDigest();
                if (digest != null) {
                    StringBuilder result = new StringBuilder(args[i]);
                    result.append(": ");
                    result.append(digest);
                    System.out.println(result);
                    break;
                }
            }
        }
        // Approach 4 - Call Back
            // It  calls back to the creator when it's done
            // receiveDigest() which is not invoked by the main() method or by any method that can be reached by following the flow of control from the main() method. Instead, it is invoked by each
            //thread separately.

            // To call back, we need reference of its
            // Call back with static method
            // Call back with instance method






    }

    public static void receiveDigest(byte[] digest, String name) {
        StringBuilder result = new StringBuilder(name);
        result.append(": ");
        result.append(digest);
        System.out.println(result);
    }

}
