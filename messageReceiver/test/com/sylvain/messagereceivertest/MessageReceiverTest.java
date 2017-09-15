package com.sylvain.messagereceivertest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sylvain.messagereceiver.MessageParser;
import com.sylvain.model.Sale;

public class MessageReceiverTest {
	
	private static Stack<String> stack;
	private final static String PRODUCT = "apples";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		stack = new Stack<>();
	    stack.push("END");
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
	
	@Test
	public void testReceiver(){
		MessageParser messageParser = new MessageParser();
		Map<String,List<Sale>> salesResultMap = null;
        while(!stack.isEmpty()){
            String saleOrder = stack.pop();
            messageParser.handleMessage(saleOrder);   
        }
        salesResultMap = messageParser.getTotalSalesMap();
        Integer totalQuantity = salesResultMap.get(PRODUCT).stream().mapToInt(e->e.getQuantity()).sum();
        Integer totalCost = salesResultMap.get(PRODUCT).stream().mapToInt(e->e.getCost()).sum();
        assertTrue(salesResultMap.containsKey(PRODUCT));
        assertEquals((int) totalQuantity,56);
        assertEquals((int) totalCost,420);
	}
	
}
