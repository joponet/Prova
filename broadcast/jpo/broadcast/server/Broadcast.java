package jpo.broadcast.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

import jpo.broadcast.parameters.Parameters;


public class Broadcast extends Thread{
	
	public Broadcast () {
		
	}
	
	public void run () {
		MulticastSocket socket;
		DatagramPacket packet;
		byte[] buffer = new byte[256];
		boolean error=false;
		//for (int i=0;i<256;i++) buffer[i]=0;
		//buffer =  Parameters.MESSAGE.getBytes();
		String message;

		try {
			socket = new MulticastSocket(Parameters.SERVER_PORT);
			socket.setNetworkInterface(NetworkInterface.getByName("wlan0"));
			//System.out.println(socket.getLocalAddress());
			InetAddress address = InetAddress.getByName(Parameters.ADDRESS);
			
			while (!error) {
				message = Parameters.MESSAGE + " " + String.valueOf(LocalDateTime.now().toString());
				buffer[0] = (byte) message.length();
				System.arraycopy(message.getBytes(), 0, buffer, 1, message.length());
				packet = new DatagramPacket(buffer, buffer.length, address, Parameters.CLIENT_PORT);
				socket.send(packet);
				Thread.sleep(200);
				//System.out.println(Parameters.MESSAGE);
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error=true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error=true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error=true;
		}
	}
}
