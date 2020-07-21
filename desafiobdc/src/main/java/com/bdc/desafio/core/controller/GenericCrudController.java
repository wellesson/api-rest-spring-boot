package com.bdc.desafio.core.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdc.desafio.core.exception.AplicacaoException;
import com.bdc.desafio.core.model.GenericEntity;
import com.bdc.desafio.core.services.GenericCrudService;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Wellesson Vieira
 *
 * @param <T>
 * @param <S>
 * @param <I>
 */
@Slf4j
@RequestMapping
public abstract class GenericCrudController<T extends GenericEntity<I>, I, S extends GenericCrudService<T, I, ?>> {

	@Autowired
	protected GenericCrudService<T, I, ?> service;

	@Getter
	@Setter
	private transient T entity;

	@Getter
	@Setter
	private transient List<T> list;

	@GetMapping
	public @ResponseBody ResponseEntity<List<T>> listar() throws AplicacaoException {

		GenericCrudController.log.debug("Realizando a chamada do controller: " + this.getClass().getName()
				+ ".listar(). Realizando a chamada do service: " + this.service.getClass().getName() + ".obterTodos()");

		final List<T> result = this.service.listar();

		log.debug("Chamada do controller: " + this.getClass().getName() + ".listar() realizada com sucesso.");

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<T> buscar(final @PathVariable I id) throws AplicacaoException {

		log.debug("Realizando a chamada do controller: " + this.getClass().getName()
				+ ".buscarPorId( " + id + " ). Realizando a chamada do service: " + this.service.getClass().getName() + ".obterUm( " + id + " )");

		final T entity = this.service.buscar(id);

		GenericCrudController.log.debug("Chamada do controller: " + this.getClass().getName() + ".buscar( " + id + " ) realizada com sucesso.");

		return entity == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@PostMapping
	public @ResponseBody ResponseEntity<T> salvar(@ApiParam(value = "Objeto entidade a ser cadastrada.", required = true) @Valid final @RequestBody T entity)
			throws AplicacaoException {

		log.debug("Realizando a chamada do controller: " + this.getClass().getName() + ".salvar( "
				+ entity.getClass().getName() + " ). Realizando a chamada do service: "
				+ this.service.getClass().getName() + ".salvar( " + entity.getClass().getName() + " )");

		final T newEntity = this.service.salvar(entity);

		log.debug("Chamada do controller: " + this.getClass().getName() + ".salvar( " + entity.getClass().getName() + " ) realizada com sucesso.");

		return new ResponseEntity<>(newEntity, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity<T> alterar(	@ApiParam(value = "Objeto entida a ser atualizada.", required = true) @Valid final @RequestBody T entity,
			final @PathVariable I id) throws AplicacaoException {

		log.debug("Realizando a chamada do controller: " + this.getClass().getName() + ".alterar( "
				+ entity.getClass().getName() + " , " + id + " ). Realizando a chamada do service: "
				+ this.service.getClass().getName() + ".salvar( " + entity.getClass().getName() + " )");

		if (((Comparable<I>) id).compareTo(entity.getId()) != 0) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		this.service.salvar(entity);

		log.debug("Chamada do controller: " + this.getClass().getName() + ".alterar( " + entity.getClass().getName() + " ) realizada com sucesso.");

		return new ResponseEntity<>(entity, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(final @PathVariable I id) throws AplicacaoException {

		log.debug("Realizando a chamada do controller: " + this.getClass().getName()+ ".remover( " + id + " ). Realizando a chamada do service: " 
		+ this.service.getClass().getName() + ".remover( " + id + " )");

		this.service.remover(id);

		GenericCrudController.log.debug("Chamada do controller: " + this.getClass().getName() + ".remover( " + id + " ) realizada com sucesso.");

		return ResponseEntity.ok().build();
	}

	
}
