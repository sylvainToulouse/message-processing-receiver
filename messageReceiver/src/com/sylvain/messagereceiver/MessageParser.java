package com.sylvain.messagereceiver;

import com.sylvain.model.Sale;
import com.sylvain.utils.MessageUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/**
 *	This class is for message parsing
 * @author Sylvain
 */
public class MessageParser {
		
	private Map<String,List<Sale>> totalSales = new HashMap<>();
	
	public MessageParser(){
		
	};
	
    /**
     * Handles the input message
    */
    public void handleMessage(final String sale){
        int type = MessageUtils.getOrderType(sale);
    	if (type > 0) {
            Sale currentSale = MessageUtils.extractData(sale,type);
            /**
             * Be careful with fruits: ananas problem
             */
            String product = (!currentSale.getProduct().matches("^.*s$")) ? currentSale.getProduct()+"s": currentSale.getProduct();
				
            if(!totalSales.containsKey(product)){
		List<Sale> list = new ArrayList<>();
		list.add(currentSale);
		totalSales.put(product, list);
            } else {
                totalSales.get(product).add(currentSale);
            }
				
            if(null != currentSale.getAdjustmentType()){
				String adjustType = currentSale.getAdjustmentType();
				Integer adjustValue = currentSale.getAdjustmentQuantity();
				List<Sale> list = totalSales.get(product);
				Iterator<Sale> iter = list.iterator();			
                List<Sale> listTemp = new ArrayList<>();
					
				while(iter.hasNext()){
		                    Sale tempSale = iter.next();
		                    int adjustedCost = MessageUtils.performOperation(
		                            tempSale.getCost(),
		                            adjustType,
		                            adjustValue,
		                            tempSale.getQuantity());
		                    tempSale.setCost(adjustedCost);
		                    listTemp.add(tempSale);
				}
				totalSales.put(product,listTemp);
            }
    	} else {
            System.out.println("The connection has been closed");
    	}
    }
    
    /**
     * Returns the map containing as key the product name and as value the associated sales
     * @return
     * 		The map containing as key the product name and as value the associated sales
     */
    public Map<String,List<Sale>> getTotalSalesMap(){
    	return totalSales;
    }
}
