package es.unileon.springapp.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.springapp.service.Manager;
import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.assets.strategy.loan.ScheduledPayment;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.TransactionException;
import es.unileon.ulebank.history.GenericTransaction;
import es.unileon.ulebank.history.Transaction;

@Controller
public class LoanPaymentsController {
	
	@Autowired
	private Manager manager;

	@RequestMapping(value = "/payments.htm")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<ScheduledPayment> payments = this.manager.getPayments();
		Map<String, ArrayList<ScheduledPayment>> myModel = new HashMap<>();

		myModel.put("payments", payments);

		return new ModelAndView("payments", "model", myModel);
	}
	
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	public Manager getManager() {
		return this.manager;
	}

}
