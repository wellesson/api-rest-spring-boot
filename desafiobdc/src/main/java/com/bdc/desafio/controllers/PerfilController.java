package com.bdc.desafio.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdc.desafio.core.controller.GenericCrudController;
import com.bdc.desafio.model.entity.Cidade;
import com.bdc.desafio.services.CidadeService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("api/cidade")
@Api(value = "Cidade Controller")
public class PerfilController extends GenericCrudController<Cidade, Long, CidadeService>{


}
