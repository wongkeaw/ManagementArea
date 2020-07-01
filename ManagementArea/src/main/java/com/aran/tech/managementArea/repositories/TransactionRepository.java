/**
 * 
 */
package com.aran.tech.managementArea.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aran.tech.managementArea.domain.Transaction;

/**
 * @author oawon
 *
 */
@Repository
public interface TransactionRepository  extends CrudRepository<Transaction, Long> {
	
    Transaction getById(Long id);

}
