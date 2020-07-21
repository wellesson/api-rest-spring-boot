package com.bdc.desafio.services;

import org.springframework.stereotype.Service;

import com.bdc.desafio.core.services.GenericCrudService;
import com.bdc.desafio.model.entity.UnidadeFederativa;
import com.bdc.desafio.model.repositories.UnidadeFederativaRepository;

@Service
public class UnidadeFederativaService extends GenericCrudService<UnidadeFederativa, Long, UnidadeFederativaRepository>{

}
