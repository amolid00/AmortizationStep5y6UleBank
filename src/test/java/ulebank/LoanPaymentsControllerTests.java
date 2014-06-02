package ulebank;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.springapp.repository.InMemoryLoanDao;
import es.unileon.springapp.service.SimpleManager;
import es.unileon.springapp.web.LoanPaymentsController;
import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.assets.strategy.loan.ScheduledPayment;

public class LoanPaymentsControllerTests {

	@Test
	public void testHandleRequestView() throws ServletException, IOException {
		LoanPaymentsController controller = new LoanPaymentsController();
		SimpleManager sm = new SimpleManager();
		sm.setLoanDao(new InMemoryLoanDao(new ArrayList<Loan>()));
		controller.setManager(sm);
		ModelAndView modelAndView = controller.handleRequest(null, null);
		assertEquals("payments", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
        Map modelMap = (Map) modelAndView.getModel().get("model");
        ArrayList<ScheduledPayment> myPayments = (ArrayList<ScheduledPayment>) modelMap.get("payments");
        assertNotNull(myPayments);
	}
	
}
