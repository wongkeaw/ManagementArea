package com.aran.tech.managementArea.log;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.aran.tech.managementArea.domain.Transaction;
import com.aran.tech.managementArea.services.TransactionService;

@Component
public class LoggingInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private TransactionService TransactionService ;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		System.out.println(this.getClass().getName() + ": preHandle");
		
		Transaction transaction = new Transaction() ;
		transaction.setPrincipalName(request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : "" );
		transaction.setRequestURL(request.getRequestURL().toString());
		TransactionService.saveOrUpdateTransaction(transaction) ;
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
		System.out.println(this.getClass().getName() + ": postHandle");
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		System.out.println(this.getClass().getName() + ": afterCompletion");
		return;
	}
}
