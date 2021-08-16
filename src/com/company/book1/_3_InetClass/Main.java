package com.company.book1._3_InetClass;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class Main {
    // The inet class
    public static void main(String[] args) throws SocketException {

        // Creating a new InetAddress Objects
            // Make a call to dns server
        // Note
            // Look up with an IP address as argument  -> getByName() -> create an inet object with input string but a DNS lookup for the actual host name is only performed when the hostname is requested
            // Security issues - TODO
                //-> Create an inet object from host name -> it requires a DNS look up -> a potentially insecure operation

        try {
            // Look up by name
            InetAddress address = InetAddress.getByName("daihocchinhquy.neu.edu.vn");
            System.out.println("Address "  + address);


            // Look up by ip
            InetAddress address1 = InetAddress.getByName("101.96.116.37");
            System.out.println("Address 1 " + address1);


            // you need the all address of the host
            InetAddress[] addresses = InetAddress.getAllByName("www.oreilly.com");
            for (InetAddress inetAddress : addresses) {
                System.out.println(inetAddress);
            }


            // get localhost
            InetAddress myAddress =InetAddress.getLocalHost();
            System.out.println("My Address " + myAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
            // If you know a numeric address -> you can create an InetAddress object from that address without talking to DNS using
                // InetAddress.getByAddress(): one only byte array (had to be cast), host name and byte array
            // Note:
                // -> Make no guarantees that such a host exists or host name is correctly mapped with ip address
                // -> Throw only byte array of illegal size
                // -> This could be useful if a domain name server is not available or might have inaccurate information.

            // Address Types
                // 1. isAnyLocalAddress() return true if address is a wildcard address -> wildcard address is address that matches any address of the local system -> for system with multiple interfaces
                // 2. isLoopbackAddress()
                // 3. isLinkLocalAddress() return true if the address is an ipv6 link-local address, false otherwise
                    // to help ipv6 network sel-config, much like DHCP server but without a server. -> do not need to forward package addressed to a link local
                    // All link-local address  begin with the eight byte: FE80:0000:0000:0000. The next 8bytes are filled with link local address ->   often copied from the Ethernet MAC address assigned by the Ethernet card manufacturer
                // 4. isSiteLocalAddress() return true if the address is an ipv6 site-local address, false other wise
                    // Site-local address are similar to the link local address except that they may be forwarded by routers within a site or campus
                    // FEC0:0000:0000:0000
                // 5. isMulticastAddress()
                    //  Multicasting broadcasts content to all subscribed computers rather than to one particular computer . In IPv4, multicast addresses all fall in the range 224.0.0.0
                    // to 239.255.255.255. In IPv6, they all begin with byte FF Chapter 13
                // 6. isMCGlobal(): is a global-multicast address, may have subscriber around the world. All multicast address begin with FF or FF1E. It using TTL time to live value to control scope rather than addressing
                // 7. isMCOrgLocal(): is an organization-wide multicast address. An organization-wide multicast address may have subscribers within all the sites of a company or organization.
                           // FF08, FF18  depending on whether the multicast address is a well known permanently assigned address or a transient address.
                // 8. isMCSiteLocal(): site-wide multicast address FF05,FF15. Packets addressed to a site-wide address will only be transmitted within their local site
                // 9. isMCLinkLocal(): if the address is a subnet-wide multicast address FF02, FF12.  Packets addressed to a link-local address will only be transmitted within their own subnet.
                // 10. isMCNodeLocal(): if the address is the interface local multicast address. Packets addressed to an interface local address are not sent beyond the network interface from which the originate
                                // only for debugging and testing
            // Testing reachability
                // Connections can be blocked for many reasons:
                        // - firewalls, proxy servers
                        // - misbehaving routers
                        // - broken cables
                        // - The remote host is not turned on when you try to connect
                // Methods
                    // isReachable(Timeout)
                    // isReachable(Interface,TTL,Timeout)
            // Inet4Address and Inet6Address
                // Inet6Address: isIPv4CompatibleAddress(): return true if the address is essentially an IPv4 address stuffed into an IPv6the
                //              in other word the address has the form 0:0:0:0:0:0:0:xxxx -> pull of the last four bytes from the array getBytes() -> using it to create ipv4
            // The NetworkInterface class
                // Network interface class represents a local ip address: can be physical interface ethernet card (Mac) or Virtual interface bound to the same physical hardware
                // The NetworkInterface class provides methods to enumerate all the local addresses, regardless of interface, and to create InetAddress objects from them
                // Method
                    // -> getByName(): Unix system "eth0", "eth1" ... loopback "lo". On windows: the name are strings like "CE31"... from the name of the vendor
                    // We can get the information about the interface with inet address
                // A single network interface may be bound to more thant one IP address
        //List all the network interface
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements())
        {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            System.out.println(networkInterface);
            while (inetAddresses.hasMoreElements())
            {
                System.out.println("         - " + inetAddresses.nextElement());

            }
        }


    }
}
