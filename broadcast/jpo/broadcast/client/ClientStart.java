package jpo.broadcast.client;

// https://en.wikipedia.org/wiki/Multicast_address
	
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

import jpo.broadcast.parameters.Parameters;

public class ClientStart {

	public static void main(String[] args) {
		MulticastSocket socket;
		DatagramPacket packet;
		byte[] buffer = new byte[256];
		String message;
		
		boolean error=false;
		packet = new DatagramPacket(buffer, buffer.length);
		error = false;
		
		try {
			while (!error) {
				socket = new MulticastSocket(Parameters.CLIENT_PORT);
				// socket.setInterface(InetAddress.getByName("192.168.1.36"));
				socket.setNetworkInterface(NetworkInterface.getByName("wlan0"));
				socket.joinGroup(InetAddress.getByName(Parameters.ADDRESS));
				socket.receive(packet);
				buffer = packet.getData();
				int length = buffer[0];
				message = new String(buffer, 1, length);
				System.out.println("|" + message + "|" + packet.getAddress() + ":" + packet.getPort());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error=true;
		}
	}

}
