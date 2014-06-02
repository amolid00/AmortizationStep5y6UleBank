package es.unileon.springapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.unileon.springapp.repository.LoanDao;
import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.assets.exceptions.LoanException;
import es.unileon.ulebank.assets.strategy.loan.GermanMethod;
import es.unileon.ulebank.assets.strategy.loan.ScheduledPayment;

@Component
public class SimpleManager implements Manager {
	

	@Autowired
    private LoanDao loanDao;
	
	@Override
	public void setAmortization(double quantity) {
		List<Loan> loans = loanDao.getLoanList();
		if(loans != null){
			for(Loan loan : loans) {
				try {
					loan.amortize(quantity);
					loanDao.saveLoan(loan);
				} catch (LoanException e) {

				}
			}
		}

	}

	@Override
	public ArrayList<ScheduledPayment> getPayments() {
		List<Loan> loans = loanDao.getLoanList();
		if(loans != null) {
			if(loans.size() > 0){
				return new GermanMethod(loans.get(0)).doCalculationOfPayments();
			}else {
				return new ArrayList<ScheduledPayment>();
			}
		}else{
			return new ArrayList<ScheduledPayment>();
		}
	}
	
    public LoanDao getLoanDao() {
		return loanDao;
	}

	public void setLoanDao(LoanDao loanDao) {
		this.loanDao = loanDao;
	}

	@Override
	public void setStrategy() {
		List<Loan> loans = loanDao.getLoanList();
		if(loans != null){
			for(Loan loan : loans) {
				loan.setStrategy(new GermanMethod(loan));
				loan.setPayments(loan.calcPayments());
			}
		}
		
	}

	@Override
	public List<Loan> getLoans() {
		return loanDao.getLoanList();
	}
}
