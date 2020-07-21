package com.bdc.desafio.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdc.desafio.core.controller.GenericCrudController;
import com.bdc.desafio.model.entity.UnidadeFederativa;
import com.bdc.desafio.services.UnidadeFederativaService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("api/estado")
@Api(value = "Perfil Controller")
public class EstadoController extends GenericCrudController<UnidadeFederativa, Long, UnidadeFederativaService>{


}
