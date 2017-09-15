package com.sylvain.messagesender;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;

public class MessageSenderSocket implements SenderInterface {

	private static final String HOSTNAME = "hostsocket";
	private static final String PORTNAME = "portsocket";
	
	private Socket socket = null;
	PrintWriter out;
	
	
	public MessageSenderSocket(){
		Properties properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("sockets.properties"));
			String hostname= properties.getProperty(HOSTNAME);
			String portString = properties.getProperty(PORTNAME);
			int port = Integer.parseInt(portString);
			socket = new Socket(hostname,port);
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void sendMessage(final String message){
		out.println(message);
	}
	
	public void close(){
		try {
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
