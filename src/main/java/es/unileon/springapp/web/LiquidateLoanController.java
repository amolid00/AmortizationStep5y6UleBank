package es.unileon.springapp.web;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.unileon.springapp.service.LiquidateQuantity;
import es.unileon.springapp.service.Manager;
import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.assets.exceptions.LoanException;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.TransactionException;
import es.unileon.ulebank.history.GenericTransaction;
import es.unileon.ulebank.history.Transaction;


@Controller
@RequestMapping(value = "/liquidateLoan.htm")
public class LiquidateLoanController {
	
	@Autowired
	private Manager manager;

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@Valid LiquidateQuantity liquidateQuantity, BindingResult result) {
		if (result.hasErrors()) {
			return "liquidateLoan";
		}
		
		double quantityLiquidate = liquidateQuantity.getQuantity();
		
		manager.setAmortization(quantityLiquidate);

		return "redirect:/payments.htm";
	}

	@RequestMapping(method = RequestMethod.GET)
	protected LiquidateQuantity formBackingObject(HttpServletRequest request)
			throws ServletException {
		LiquidateQuantity quantity = new LiquidateQuantity();
		quantity.setQuantity(0);
		return quantity;
	}
	
}
