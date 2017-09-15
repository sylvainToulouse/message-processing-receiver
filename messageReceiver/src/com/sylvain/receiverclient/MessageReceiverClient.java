package com.sylvain.receiverclient;

import com.sylvain.messagereceiver.MessageReceiver;

/**
 * Class used to initialize the message receiver
 * @author Sylvain
 */
public class MessageReceiverClient {
    
	/**
	 * Constructor
	 */
    public MessageReceiverClient(){}
   
    public MessageReceiverClient(String[] args){
        
    }
    
    /**
     * Treats the received messages.
     */
    private void receiveMessages(){
        MessageReceiver receiver = new MessageReceiver();
        receiver.receive();
        receiver.close();
    }
    
    public static void main(String[] args){
        MessageReceiverClient messageReceiverClient = new MessageReceiverClient();
        messageReceiverClient.receiveMessages();
    }    
}
