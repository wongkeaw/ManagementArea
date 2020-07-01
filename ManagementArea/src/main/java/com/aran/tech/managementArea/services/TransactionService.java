/**
 * 
 */
package com.aran.tech.managementArea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aran.tech.managementArea.domain.Transaction;
import com.aran.tech.managementArea.repositories.TransactionRepository;

/**
 * @author oawon
 *
 */
@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository ;
	
   
	public Transaction saveOrUpdateTransaction(Transaction transaction){
			return transactionRepository.save(transaction) ;
	}
	public Transaction findTransactionbyId(long id) {
		return transactionRepository.getById(id) ;
	}
	
}
