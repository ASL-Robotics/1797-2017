package org.usfirst.frc.team1797.udp.reciever;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.usfirst.frc.team1797.udp.transmission.VisionInformation;

import com.google.gson.Gson;

@Deprecated
public class UDPReciever extends Thread {
	public static String lastDataReceived = "";
	public static VisionInformation message;
	Gson gson = new Gson();
	
    protected DatagramSocket socket = null;
    protected BufferedReader in = null;
    protected boolean moreQuotes = true;
    public UDPReciever() throws IOException {
    this("udpReciever");
    }
 
    public UDPReciever(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(5801);
    }
    
    public void run() {
    	System.out.println("thread start");
    	byte[] buf = new byte[256];
    	 DatagramPacket packet = new DatagramPacket(buf, buf.length);
        while (moreQuotes) {
            try {
                // receive request
            	packet.setLength(buf.length);
                socket.receive(packet);
                byte[] data = packet.getData();
                lastDataReceived = new String(data, 0, packet.getLength());
                message = gson.fromJson(lastDataReceived, VisionInformation.class);
            } catch (IOException e) {
                e.printStackTrace();
                moreQuotes = false;
            }
        }
        socket.close();
    }
}