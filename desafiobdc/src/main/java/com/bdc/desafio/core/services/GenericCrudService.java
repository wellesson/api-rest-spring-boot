package com.bdc.desafio.core.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.glassfish.jersey.internal.guava.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bdc.desafio.core.exception.AplicacaoException;
import com.bdc.desafio.core.model.GenericCrudRepository;
import com.bdc.desafio.core.model.GenericEntity;


/**
 * @author Wellesson Vieira
 *
 * @param <T>
 */
public abstract class GenericCrudService<T extends GenericEntity<I>, I, R extends GenericCrudRepository<T, I>> {


  @Autowired
  protected R repository;

  public T buscar(final I id) throws AplicacaoException {
    final Optional<T> retorno = this.repository.findById(id);
    if (retorno != null && retorno.isPresent()) {
      return retorno.get();
    }
    return null;
  }

  public boolean existe(final I id) {
    return !Objects.isNull(id) && this.repository.existsById(id);
  }

  public List<T> listar() {
    return Lists.newArrayList(this.repository.findAll());
  }

  @Transactional(rollbackFor = Throwable.class)
  public void remover(final I id) throws AplicacaoException {
    try {
      this.repository.deleteById(id);
    } catch (final Exception e) {
      throw new AplicacaoException("Erro genérico de exclusão.");
    }
  }

  @Transactional(rollbackFor = Throwable.class)
  public void removerTodos() {
    this.repository.deleteAll();
  }

  @Transactional(rollbackFor = Throwable.class)
  public T salvar(T entidade) throws AplicacaoException {
    return this.repository.saveAndFlush(entidade);

  }

}
