package com.sylvain.reports;

import com.sylvain.model.Sale;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class to generate an adjustment report
 * @author csy
 *
 */
public class AdjustmentReport implements Report{

	/**
	 * Generates an adjustment report containing the adjustment operations
	 */
	@Override
	public void generateReport(Map<String, List<Sale>> map) {
		List<Sale> list = new ArrayList<Sale>();
		System.out.println("---------------Adjustment report---------------");
		for(String product : map.keySet()){
			list = map.get(product).stream().filter(e-> e.getAdjustmentType()!=null).collect(Collectors.toList());
			for(Sale sale:list){
				System.out.println("Product: "+sale.getProduct()+",adjustement type: "+ sale.getAdjustmentType()+",adjustment value: "+sale.getAdjustmentQuantity());
			}
		}
		System.out.println("--------------------------------------------");
	}
}
