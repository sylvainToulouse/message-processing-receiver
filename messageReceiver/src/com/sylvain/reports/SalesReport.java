package com.sylvain.reports;

import com.sylvain.model.Sale;
import java.util.List;
import java.util.Map;

/**
 * Class to generate a sale report
 * @author Sylvain
 *
 */
public class SalesReport implements Report{

	/**
	 * Generates a sale report with the product, the total sold quantity and the global sale amount
	 */
	@Override
	public void generateReport(Map<String, List<Sale>> map) {
		System.out.println("-------------------Sales report-----------------");
		for(String product : map.keySet()){
			Integer totalSale = map.get(product).stream().mapToInt(e->e.getCost()).sum();
			Integer totalQuantity = map.get(product).stream().mapToInt(e->e.getQuantity()).sum();
			System.out.println("Product: "+ product + " Quantities: "+totalQuantity+" Sales: "+totalSale);
		}
		System.out.println("--------------------------------------------");
		
	}
}
