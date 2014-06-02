package ulebank;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.unileon.springapp.repository.InMemoryLoanDao;
import es.unileon.springapp.repository.LoanDao;
import es.unileon.springapp.service.SimpleManager;
import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.assets.exceptions.LoanException;
import es.unileon.ulebank.assets.handler.GenericLoanHandler;
import es.unileon.ulebank.assets.support.PaymentPeriod;
import es.unileon.ulebank.handler.MalformedHandlerException;

public class SimpleLoanManagerTests {
	
	private SimpleManager simpleManager;
	
	private List<Loan> loanList;
	
	private static int LOAN_COUNT = 2;
	
	@Before
	public void setUp() throws LoanException, MalformedHandlerException {
		this.simpleManager = new SimpleManager();
		this.loanList = new ArrayList<Loan>();
		
		Loan loanAnnual = new Loan(new GenericLoanHandler("LN-4-2014-ES-PH5DD-7"), 120000, 0.12, PaymentPeriod.ANNUAL, 5);
		Loan loanMonthly = new Loan(new GenericLoanHandler("LN-4-2014-ES-SDN9W-3"), 185000, 0.34, PaymentPeriod.MONTHLY, 24);
		
		loanList.add(loanAnnual);
		loanList.add(loanMonthly);
		
		LoanDao loanDao = new InMemoryLoanDao(this.loanList);
		this.simpleManager.setLoanDao(loanDao);
	}
	
    @Test
    public void testGetLoansWithNoLoans() {
        this.simpleManager = new SimpleManager();
        this.simpleManager.setLoanDao(new InMemoryLoanDao(null));
        assertNotNull(this.simpleManager.getPayments());
        assertEquals(0,this.simpleManager.getPayments().size());
    }

    @Test
    public void testGetLoans() {
    	List<Loan> loans = this.simpleManager.getLoans();
    	assertEquals(2, loans.size());
    }
    
    @Test
    public void testAmortizedWithNullListOfProducts() {
        try {
            this.simpleManager = new SimpleManager();
            this.simpleManager.setLoanDao(new InMemoryLoanDao(null));
            this.simpleManager.setAmortization(18900.5);
        }
        catch(NullPointerException ex) {
        	fail("Products list is null.");
        }
    }

    @Test
    public void testAmortizedWithEmptyListOfLoans() {
        try {
            this.simpleManager = new SimpleManager();
            this.simpleManager.setLoanDao(new InMemoryLoanDao(new ArrayList<Loan>()));
            this.simpleManager.setAmortization(2500);
        }
        catch(Exception ex) {
        	fail("Products list is empty.");
        }           
    }
    
    @Test
    public void testAmortizedWithPositiveQuantity() {
        this.simpleManager.setAmortization(100000);
        double expectedDebtLoanAnnual = 20000;
        double expectedDebtLoanMonthly = 85000;
        
        List<Loan> listLoans = this.simpleManager.getLoans();
        assertNotNull(listLoans);
        assertEquals(2, listLoans.size());
        
        Loan loanAnnual = listLoans.get(0);
        assertEquals(expectedDebtLoanAnnual, loanAnnual.getDebt(), 0);
        
        Loan loanMonthly = listLoans.get(1);
        assertEquals(expectedDebtLoanMonthly, loanMonthly.getDebt(), 0);
        
       
    }

}