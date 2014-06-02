package es.unileon.springapp.repository;

import java.util.List;

import es.unileon.ulebank.assets.Loan;

public class InMemoryLoanDao implements LoanDao {
	private List<Loan> loanList;
	
	public InMemoryLoanDao(List<Loan> loanList) {
		this.loanList = loanList;
	}

	@Override
	public List<Loan> getLoanList() {
		return this.loanList;
	}

	@Override
	public void saveLoan(Loan loan) {
//		this.loanList.add(loan);
	}
	
	
}
