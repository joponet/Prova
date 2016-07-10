package jpo.broadcast.common;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public class Interfaces {
	
	static NetworkInterface selectedInterface = null;
	static InetAddress selectedAddress = null;
    static Enumeration<NetworkInterface> nets;
    static int ValidIntefaces; 

	public static void main (String args[]) {
		listInterfaces();
	}
	
	public static void listInterfaces () {
//        Enumeration<NetworkInterface> nets;
		try {
			nets = NetworkInterface.getNetworkInterfaces();
			ValidIntefaces = 0;
	        for (NetworkInterface netint : Collections.list(nets)) {
	            if (netint.isUp() && !netint.isLoopback() && !netint.isPointToPoint() && !netint.isVirtual()) {
	            	selectedInterface = netint;
	            	ValidIntefaces++;
	            }
	            displayInterfaceInformation(netint);
	        }
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}     
    static void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
    	if (!netint.isUp()) return;
        System.out.printf("Display name: %s\n", netint.getDisplayName());
        System.out.printf("Name: %s\n", netint.getName());
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
        	System.out.printf("InetAddress: %s\n", inetAddress.getHostAddress());
        	System.out.printf("Local: %b\n", (inetAddress instanceof Inet4Address));
        	if (inetAddress instanceof Inet4Address) selectedAddress = inetAddress;
        }
        System.out.printf("Up: %s Virtual: %s\n", netint.isUp(), netint.isVirtual());
        System.out.printf("Is PTP: %b\n", netint.isPointToPoint());
        System.out.printf("Is LoopBack: %b\n", netint.isLoopback());
        System.out.printf("Is Selected: %b\n", netint==selectedInterface);
        System.out.printf("\n");
    }
    static public NetworkInterface getInterface () {
    	return selectedInterface;
    }
}
