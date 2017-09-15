package com.sylvain.utils;

import com.sylvain.model.Sale;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author Sylvain
 */
public class MessageUtils {
	
	/**
	 * Regular expression
	 */
    private static final String REGEXTYPE1 = "^([a-z]+)[a-z ]*([0-9]+)p$";
    private static final String REGEXTYPE2 = "^([0-9]+)[ a-z]+ of ([a-z]+) at ([0-9]+)p each.*$";
    private static final String REGEXTYPE3 = "^([a-z]+) at ([0-9]+)p and (add|multiply|substract)[a-z ]+([0-9]+)[a-z]?[ ]+([a-z]+)$";
    
    /**
     * Operations
     */
    private static final String ADD = "add";
    private static final String MUL = "multiply";
    private static final String SUB = "substract";
    
    /**
    * Gets the message type
    * 
    * 1 for Type1 message, 2 for Type2 message, 3 for Type3 message
    * @param order
    * 		The sale order
    * @return
    * 		The message type: 1, 2 or 3
    */
    public static int getOrderType(String order){
        if(Pattern.matches(REGEXTYPE2,order)) return 2;
        if(Pattern.matches(REGEXTYPE1,order)) return 1;
        if(Pattern.matches(REGEXTYPE3,order)) return 3;
        return 0;
    }
    
    /**
     * Extracts the relevant data from the sale order according to the message type.
     * 
     * 
     * @param saleOrder
     * 		The sale order
     * @param type
     * 		The message type: can be 1, 2 or 3
     * @return
     * 		A sale object with the sale details
     */
    public static Sale extractData(final String saleOrder, int type){
        Sale sale = null;
        
        if (type == 1) {
            Matcher m = Pattern.compile(REGEXTYPE1).matcher(saleOrder);
            while(m.find()){
            	sale = new Sale.Builder(m.group(1),1,Integer.parseInt(m.group(2))).build();
            }
        }
        if(type == 2){
            Matcher m = Pattern.compile(REGEXTYPE2).matcher(saleOrder);
            while(m.find()){
            	sale = new Sale.Builder(m.group(2),Integer.parseInt(m.group(1)),Integer.parseInt(m.group(3))).build();
            }        	

        }
        if(type == 3){
            Matcher m = Pattern.compile(REGEXTYPE3).matcher(saleOrder);
            while(m.find()){
            	sale = new Sale.Builder(m.group(1),1,Integer.parseInt(m.group(2))).
            			adjustmentQuantity(Integer.parseInt(m.group(4))).
            			adjustmentType(m.group(3)).
            			build();
            }        	
        }
        return sale;
    }
    
    /**
     * Performs the adjustment operation
     * 
     * @param cost
     * 		The cost of the current sale
     * @param operation
     * 		The operation to perform
     * @param adjustValue
     * 		The adjustment value
     * @param quantity
     * 		The sale quantity
     * @return
     */
    public static int performOperation(Integer cost, String operation, Integer adjustValue, Integer quantity){
    	if(operation.equals(ADD)) return cost + adjustValue*quantity;
    	if(operation.equals(MUL)) return cost * adjustValue* quantity;
    	if(operation.equals(SUB)) return cost - adjustValue*quantity;
    	return 0;
    }    
}
