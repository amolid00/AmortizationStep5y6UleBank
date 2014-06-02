package es.unileon.springapp.service;

import javax.validation.constraints.Min;


public class LiquidateQuantity {

    @Min(0)
    private double quantity;
    
    public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
    
    

}
