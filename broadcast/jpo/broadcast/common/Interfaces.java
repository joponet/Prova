package jpo.broadcast.common;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public class Interfaces {
	public static void listInterfaces () {
        Enumeration<NetworkInterface> nets;
		try {
			nets = NetworkInterface.getNetworkInterfaces();
	        for (NetworkInterface netint : Collections.list(nets))
	            displayInterfaceInformation(netint);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}     
    static void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
    	if (!netint.isUp()) return;
        System.out.printf("Display name: %s\n", netint.getDisplayName());
        System.out.printf("Name: %s\n", netint.getName());
        System.out.printf("Up: %s Virtual: %s\n", netint.isUp(), netint.isVirtual());
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
        	System.out.printf("InetAddress: %s\n", inetAddress);
        }
        System.out.printf("\n");
    }
    public NetworkInterface selectInterfaces () {
    	NetworkInterface selected = null;
    	return selected;
    }
}
