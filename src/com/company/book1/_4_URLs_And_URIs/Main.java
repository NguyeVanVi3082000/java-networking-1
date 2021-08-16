package com.company.book1._4_URLs_And_URIs;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {

        testQueryString();
    }

    public static void setProxy() {

        System.setProperty("http.proxyHost", "192.168.254.254");
        System.setProperty("http.proxyPort", "9000");
        System.setProperty("http.nonProxyHosts", "java.oreilly.com|xml.oreilly.com");


    }

    public static void testQueryString() {
        QueryString qs = new QueryString();
        qs.add("hl", "en");
        qs.add("as_q", "Java");
        qs.add("as_epq", "I/O");
        String url = "http://www.google.com/search?"+qs;
        System.out.println(url);

    }

    public static void testEncoderByPeaces() {
        // https://www.google.com/search?hl=en&as_q=Java&as_epq=I/O
        try {
            String url = "https://www.google.com/search?";
            url += URLEncoder.encode("hl", StandardCharsets.UTF_8);
            url += "=";
            url += URLEncoder.encode("en", StandardCharsets.UTF_8);
            url += "&";
            url += URLEncoder.encode("as_q", StandardCharsets.UTF_8);
            url += "=";
            url += URLEncoder.encode("Java", StandardCharsets.UTF_8);
            url += "&";
            url += URLEncoder.encode("as_epq", StandardCharsets.UTF_8);
            url += "=";
            url += URLEncoder.encode("I/O", StandardCharsets.UTF_8);
            System.out.println(url);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void testEncoder() {
        System.out.println(URLEncoder.encode("This string has spaces",
                StandardCharsets.UTF_8));
        System.out.println(URLEncoder.encode("This*string*has*asterisks",
                StandardCharsets.UTF_8));
        System.out.println(URLEncoder.encode("This%string%has%percent%signs",
                StandardCharsets.UTF_8));
        System.out.println(URLEncoder.encode("This+string+has+pluses",
                StandardCharsets.UTF_8));
        System.out.println(URLEncoder.encode("This/string/has/slashes",
                StandardCharsets.UTF_8));
        System.out.println(URLEncoder.encode("This\"string\"has\"quote\"marks",
                StandardCharsets.UTF_8));
        System.out.println(URLEncoder.encode("This:string:has:colons",
                StandardCharsets.UTF_8));
        System.out.println(URLEncoder.encode("This~string~has~tildes",
                StandardCharsets.UTF_8));
        System.out.println(URLEncoder.encode("This(string)has(parentheses)",
                StandardCharsets.UTF_8));
        System.out.println(URLEncoder.encode("This.string.has.periods",
                StandardCharsets.UTF_8));
        System.out.println(URLEncoder.encode("This=string=has=equals=signs",
                StandardCharsets.UTF_8));
        System.out.println(URLEncoder.encode("This&string&has&ampersands",
                StandardCharsets.UTF_8));
        System.out.println(URLEncoder.encode("Thiséstringéhasé non-ASCII characters", StandardCharsets.UTF_8));

    }

    public static void testGetContent(String input) {
        try {
            URL u = new URL(input);
            Object o = u.getContent();
            System.out.println("I got a "+o.getClass().getName());
        } catch (MalformedURLException ex) {
            System.err.println(input+" is not a parseable URL");
        } catch (IOException ex) {
            System.err.println(ex);
        }
        // Hard to see what kind of object you may get
        // Another version -> you choose which class the content to be return


    }

    public static void testUrlConnection(String input) {
        try {
            URL url = new URL(input);
            URLConnection urlConnection = url.openConnection();
            // read from the connection
            // The URL class let you write data to as well as read from it
            // The overrides any proxy server set with socksProxyHost, socksProxyPort
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testOpenStreamV1(String input) {
        InputStream inputStream = null;

        try {
            URL url = new URL(input);
            inputStream = url.openStream();
            inputStream = new BufferedInputStream(inputStream);
            Reader reader = new InputStreamReader(inputStream);
            int c;
            while ((c = reader.read()) != -1)
                System.out.println(c);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void testOpenStream(String input) {

        InputStream inputStream = null;
        try {
            URL url = new URL(input);
            inputStream = url.openStream();
            int c;
            while ((c = inputStream.read()) != -1)
                System.out.write(c);
            inputStream.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static void testProtocol(String url) {
        try {
            URL u = new URL(url);
            System.out.println(u.getProtocol()+" is supported ");
        } catch (MalformedURLException e) {
            String protocol = url.substring(0, url.indexOf(':'));
            System.out.println(protocol+" is not supported ");
        }
    }

    public void testProtocolExample() {
        // hypertext transfer protocol
        testProtocol("http://www.adc.org");
        // secure http
        testProtocol("https://www.amazon.com/exec/obidos/order2/");
        // file transfer protocol
        testProtocol("ftp://ibiblio.org/pub/languages/java/javafaq/");
        // Simple Mail Transfer Protocol
        testProtocol("mailto:elharo@ibiblio.org");
        // telnet
        testProtocol("telnet://dibner.poly.edu/");
        // local file access
        testProtocol("file:///etc/passwd");
        // gopher
        testProtocol("gopher://gopher.anc.org.za/");
        // Lightweight Directory Access Protocol
        testProtocol(
                "ldap://ldap.itd.umich.edu/o=University%20of%20Michigan,c=US?postalAddress");
        // JAR
        testProtocol(
                "jar:http://cafeaulait.org/books/javaio/ioexamples/javaio.jar!"
                        +"/com/macfaq/io/StreamCopier.class");
        // NFS, Network File System
        testProtocol("nfs://utopia.poly.edu/usr/tmp/");
        // a custom protocol for JDBC
        testProtocol("jdbc:mysql://luna.ibiblio.org:3306/NEWS");
        // rmi, a custom protocol for remote method invocation
        testProtocol("rmi://ibiblio.org/RenderEngine");
        // custom protocols for HotJava
        testProtocol("doc:/UsersGuide/release.html");
        testProtocol("netdoc:/UsersGuide/release.html");
        testProtocol("systemresource://www.adc.org/+/index.html");
        testProtocol("verbatim:http://www.adc.org/");

    }
}
