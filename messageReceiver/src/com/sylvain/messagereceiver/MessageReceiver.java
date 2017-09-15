package com.sylvain.messagereceiver;

import com.sylvain.model.Sale;
import com.sylvain.reports.AdjustmentReport;
import com.sylvain.reports.ReportContext;
import com.sylvain.reports.SalesReport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Message Receiver class
 * @author Sylvain
 */
public class MessageReceiver {
	
	private static final String PORTNAME = "portsocket";
    Map<String,List<Sale>> totalSales = new HashMap<>();
    private ServerSocket socket = null;
    
    public MessageReceiver(){
    	Properties prop = new Properties();
    	try {
    		prop.load(getClass().getClassLoader().getResourceAsStream("sockets.properties"));
    		String portName = prop.getProperty(PORTNAME);
			socket = new ServerSocket(Integer.parseInt(portName));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    
    /**
     * receives a message from the client. 
     */
    public void receive(){
    	
    	MessageParser messageParser = new MessageParser();
    	int count = 0;
    	ReportContext contextReport = null;
    	BufferedReader plec = null;
    	Socket server = null;
    	try {
			server = (Socket) socket.accept();
			plec = new BufferedReader(new InputStreamReader(server.getInputStream()));
            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            
	        while(true) {
	               String message = plec.readLine();
	               messageParser.handleMessage(message);
	               
	               if(message.equals("END")) {
	            	   break;
	               }
	               
	               count +=1;
	               /**
	                * Each 10th message: print the sale report
	                */
	               if(count % 10 == 0){
	            	   totalSales = messageParser.getTotalSalesMap();
	                   contextReport = new ReportContext(new SalesReport(),totalSales);
	                   contextReport.generateReport();                            
	               }
	               /**
	                * Each 50th message, print an adjustment report 
	                */
	               if(count % 50 == 0) {
	            	   System.out.println("pausing...");
	            	   totalSales = messageParser.getTotalSalesMap();
	                   contextReport = new ReportContext(new AdjustmentReport(),totalSales);
	                   contextReport.generateReport();
	                   Thread.currentThread();
	                   Thread.sleep(20000);
	               }
	         }
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
		}
    }
    
    /**
     * Closes all the remaining connections
     */
    public void close(){
    		try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

    }
}
    