package ulebank;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.unileon.springapp.repository.LoanDao;
import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.assets.exceptions.LoanException;
import es.unileon.ulebank.assets.handler.GenericLoanHandler;
import es.unileon.ulebank.assets.support.PaymentPeriod;
import es.unileon.ulebank.handler.MalformedHandlerException;

public class JPALoanDaoTests {
	private ApplicationContext context;
    private LoanDao loanDao;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
        loanDao = (LoanDao) context.getBean("loanDao");
    }

    @Test
    public void testGetLoanList() {
        List<Loan> loans = loanDao.getLoanList();
        assertEquals(loans.size(), 1, 0);	   
    }

    @Test
    public void testSaveLoan() {
        List<Loan> loans = loanDao.getLoanList();

        Loan loan = loans.get(0);
        Double debt = loan.getDebt();
        loan.setDebt(10000);
        loanDao.saveLoan(loan);

        List<Loan> updatedLoans = loanDao.getLoanList();
        Loan updatedLoan = updatedLoans.get(0);
        assertEquals(10000, updatedLoan.getDebt(),0);
        
        loan.setDebt(debt);
        loanDao.saveLoan(loan);
        
    }
    
    @Test
    public void testSaveNewLoan() throws LoanException, MalformedHandlerException {
    	
    	Loan newLoan = new Loan(new GenericLoanHandler("LN-4-2014-ES-KU9PX-6"), 15000, 0.24, PaymentPeriod.ANNUAL, 12);
    	
    	loanDao.saveLoan(newLoan);
    	
    	List<Loan> loans = loanDao.getLoanList();
        assertEquals(loans.size(), 2, 0);	  
        
    }

}
