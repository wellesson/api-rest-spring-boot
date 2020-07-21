package com.bdc.desafio.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdc.desafio.core.controller.GenericCrudController;
import com.bdc.desafio.model.entity.Perfil;
import com.bdc.desafio.services.PerfilService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("api/perfil")
@Api(value = "Perfil Controller")
public class CidadeController extends GenericCrudController<Perfil, Long, PerfilService>{


}
