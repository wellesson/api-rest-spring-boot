package com.bdc.desafio.services;

import org.springframework.stereotype.Service;

import com.bdc.desafio.core.services.GenericCrudService;
import com.bdc.desafio.model.entity.Cidade;
import com.bdc.desafio.model.repositories.CidadeRepository;

@Service
public class CidadeService extends GenericCrudService<Cidade, Long, CidadeRepository>{

}
