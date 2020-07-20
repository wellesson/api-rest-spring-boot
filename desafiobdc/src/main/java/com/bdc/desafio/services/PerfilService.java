package com.bdc.desafio.services;

import org.springframework.stereotype.Service;

import com.bdc.desafio.core.services.GenericCrudService;
import com.bdc.desafio.model.entity.Perfil;
import com.bdc.desafio.model.repositories.PerfilRepository;

@Service
public class PerfilService extends GenericCrudService<Perfil, Long, PerfilRepository>{

}
