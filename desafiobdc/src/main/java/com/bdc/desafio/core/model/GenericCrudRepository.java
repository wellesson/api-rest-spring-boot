package com.bdc.desafio.core.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;



/**
 * @author Wellesson Vieira
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface GenericCrudRepository<T extends GenericEntity<I>, I> extends JpaRepository<T, I> {
	

}
