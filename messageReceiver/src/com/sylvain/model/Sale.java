package com.sylvain.model;

/**
 * This class represents a sale
 * @author sylvain
 *
 */
public class Sale {

	private final String product;
    private final Integer unitCost;
	private Integer quantity;
	private Integer cost;
	private String adjustmentType;
	private Integer adjustmentQuantity;
	/**
	 * Constructor
	 * 
	 * @param product
	 * 		The product
	 * @param quantity
	 * 		The sold quantity
	 * @param unitCost
	 * 		The product unit cost
	 * @param adjustmentType
	 * 		The adjustment operation (if present, else null)
	 * @param adjustmentQuantity
	 * 		The adjustment quantity (if present, else null)
	 */
	private Sale(String product, Integer quantity, Integer unitCost, String adjustmentType, Integer adjustmentQuantity){
		this.product =product;
		this.quantity = quantity;
		this.adjustmentType = adjustmentType;
		this.adjustmentQuantity = adjustmentQuantity;
		this.unitCost = unitCost;
		this.cost = unitCost*quantity;
	}
	
	/**
	 * Getter
	 * @return
	 * 		The product name
	 */
	public String getProduct(){
		return product;
	}
	
	public Integer getUnitCost(){
		return unitCost;
	}
	
	public Integer getQuantity(){
		return quantity;
	}
	
	public Integer getCost(){
		return cost;
	}
	
	public void setCost(Integer cost){
		this.cost = cost;
	}
	
	public void setQuantity(Integer quantity){
		this.quantity = quantity;
	}
	
	public String getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	public Integer getAdjustmentQuantity() {
		return adjustmentQuantity;
	}

	public void setAdjustmentQuantity(Integer adjustmentQuantity) {
		this.adjustmentQuantity = adjustmentQuantity;
	}
	
	/**
	 * Builder for the Sale class
	 * @author sylvain
	 *
	 */
	public static class Builder {
		
		private String nestedProduct;
		private Integer nestedQuantity;
		private Integer nestedUnitCost;
		private String nestedadjustmentType = null;
		private Integer nestedadjustmentQuantity = null;
		
		
		public Builder(String inProduct, Integer inQuantity, Integer inUnitCost){
			this.nestedProduct = inProduct;
			this.nestedQuantity = inQuantity;
			this.nestedUnitCost = inUnitCost;
		}

		
		public Builder adjustmentType(String inAdjustmentType){
			this.nestedadjustmentType = inAdjustmentType;
			return this;
		}
		
		public Builder adjustmentQuantity(Integer inAdjustmentQuantity){
			this.nestedadjustmentQuantity = inAdjustmentQuantity;
			return this;
		}
		
		public Sale build(){
			return new Sale(
					nestedProduct,
					nestedQuantity,
					nestedUnitCost,
					nestedadjustmentType,
					nestedadjustmentQuantity
					);
		}
		
	}
}
