package jpo.broadcast.server;

import jpo.broadcast.common.Interfaces;

//help: https://docs.oracle.com/javase/tutorial/networking/datagrams/broadcasting.html

public class BroadcastStart {
	public static void main(String[] args) {
		Interfaces.listInterfaces();
		Broadcast broadcast = new Broadcast();
		broadcast.start();
	}
}
