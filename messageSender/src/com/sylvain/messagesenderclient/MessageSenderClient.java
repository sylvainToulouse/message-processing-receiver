package com.sylvain.messagesenderclient;



import java.util.Stack;

import com.sylvain.messagesender.MessageSenderSocket;
import com.sylvain.messagesender.SenderInterface;

/**
 *
 * @author Sylvain
 */
public class MessageSenderClient {
    
    private final Stack<String> stack = new Stack<>();
    {
	    stack.push("END");
	    stack.push("peache at 15p");
	    stack.push("peache at 15p");
	    stack.push("peache at 15p");
	    stack.push("peache at 15p");
	    stack.push("peache at 55p");
	    stack.push("peache at 45p");
	    stack.push("peache at 35p");
	    stack.push("peache at 25p");
	    stack.push("peache at 5p");
		stack.push("peache at 15p");
		stack.push("peache at 25p");
		stack.push("peache at 5p");
		stack.push("peache at 35p");
		stack.push("peache at 45p");
		stack.push("ananas at 55p");
		stack.push("blueberry at 40p");
		stack.push("peache at 40p");
		stack.push("peache at 40p");
		stack.push("peache at 40p");
        stack.push("banana at 30p");
		stack.push("blueberry at 30p");
		stack.push("banana at 30p");
		stack.push("banana at 30p");
		stack.push("banana at 30p");
		stack.push("banana at 30p");
		stack.push("banana at 30p");
		stack.push("banana at 30p");
		stack.push("banana at 30p");
		stack.push("banana at 5p");
		stack.push("banana at 30p");
		stack.push("banana at 40p");
		stack.push("banana at 50p");
		stack.push("banana at 10p");
		stack.push("banana at 15p");
		stack.push("banana at 25p");
		stack.push("banana at 20p");
		stack.push("banana at 20p");
		stack.push("20 sales of blueberry at 20p each");
		stack.push("20 sales of peaches at 10p each");
		stack.push("20 sales of ananas at 10p each");
		stack.push("20 sales of peaches at 30p each");
		stack.push("20 sales of peaches at 50p each");
		stack.push("banana at 10p and multiply by 3p bananas"); 
		stack.push("banana at 10p and substract 10p bananas");
		stack.push("banana at 200p");
		stack.push("banana at 20p");
	    stack.push("30 sales of ananas at 60p each");
	    stack.push("ananas at 40p and substract 30p ananas");
	    stack.push("peache at 40p");
	    stack.push("ananas at 50p");
	    stack.push("ananas at 60p");
	    stack.push("orange at 40p and substract 10p oranges");
		stack.push("20 sales of oranges at 5p each");
	    stack.push("10 sales of oranges at 10p each");
	    stack.push("orange at 20p and add 20p oranges");
	    stack.push("orange at 30p");
	    stack.push("orange at 50p");
	    stack.push("apple at 40p and substract 10p apples");
	    stack.push("20 sales of apples at 10p each");
	    stack.push("10 sales of apples at 5p each");
	    stack.push("apple at 30p and add 20p apples");
	    stack.push("apple at 40p and substract 10p apples");
	    stack.push("20 sales of apples at 10p each");
		stack.push("apple at 40p and multiply by 2 apples");
	    stack.push("apple at 10p");
	    stack.push("apple at 30p");
    }
    
    public MessageSenderClient(){}    
    
    public MessageSenderClient(String args[]){
        
    }
    
    public static void main(String[] args){
        MessageSenderClient senderClient = new MessageSenderClient(args);
        senderClient.sendMessages();
    }
    
    private void sendMessages(){
    	 SenderInterface sender  = new MessageSenderSocket();
        while(!stack.isEmpty()){
            String saleOrder = stack.pop();
            sender.sendMessage(saleOrder);        
        }
        sender.close();
    }
}
