package es.unileon.springapp.service;


import java.util.ArrayList;
import java.util.List;

import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.assets.strategy.loan.ScheduledPayment;

public interface Manager {

	/**
	 * Method that changes the number debt
	 * @param numFees
	 */
	public void setAmortization(double quantity);
	
	public ArrayList<ScheduledPayment> getPayments();
	
	public List<Loan> getLoans();
	
	public void setStrategy();
}
