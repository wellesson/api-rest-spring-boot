package com.bdc.desafio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdc.desafio.dtos.UsuarioDTO;
import com.bdc.desafio.dtos.request.RequestUsuarioDTO;
import com.bdc.desafio.model.entity.Usuario;
import com.bdc.desafio.services.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/usuarios")
@Api(value = "Usuario Controller")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@GetMapping
	@ApiOperation(value = "Listar todos os usuários por UF")
	@ApiResponses(@ApiResponse(code = 200, message = "Consulta realizada com sucesso."))
	public ResponseEntity<List<UsuarioDTO>> listar() {
		return ResponseEntity.ok(this.usuarioService.findAll());
	}
	
	@PostMapping
	@ApiOperation(value = "Cadastra usuário")
	@ApiResponses(@ApiResponse(code = 200, message = "Cadastro realizada com sucesso.", response = UsuarioDTO.class))
	public ResponseEntity<UsuarioDTO> incluir(@Validated @RequestBody RequestUsuarioDTO usuarioDTO) {
		return ResponseEntity.ok(this.usuarioService.incluir(usuarioDTO));
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Busca usuário por ID")
	@ApiResponses(@ApiResponse(code = 200, message = "Consulta realizada com sucesso.", response = UsuarioDTO.class))
	public ResponseEntity<UsuarioDTO> consultarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(this.usuarioService.findById(id));
	}

	@GetMapping("/uf/{codigoUf}")
	@ApiOperation(value = "Listar todos os usuários por UF")
	@ApiResponses(@ApiResponse(code = 200, message = "Consulta realizada com sucesso.", response = UsuarioDTO.class))
	public ResponseEntity<List<Usuario>> consultarPorUf(@PathVariable Long codigoUf) {
		return ResponseEntity.ok(this.usuarioService.findUsersByUf(codigoUf));
	}

	@GetMapping("/perfil/{codigoPerfil}")
	@ApiOperation(value = "Listar todos os usuários por Perfil")
	@ApiResponses(@ApiResponse(code = 200, message = "Consulta realizada com sucesso.", response = UsuarioDTO.class))
	public ResponseEntity<List<Usuario>> consultarPorPerfil(@PathVariable Long codigoPerfil) {
		return ResponseEntity.ok(this.usuarioService.findByPerfil(codigoPerfil));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Exclui usuário por ID")
	@ApiResponses(@ApiResponse(code = 200, message = "Exclusão realizada com sucesso.", response = UsuarioDTO.class))
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		usuarioService.remover(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualizar usuário")
	@ApiResponses(@ApiResponse(code = 200, message = "Atualização realizada com sucesso.", response = UsuarioDTO.class))
	public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long id, @RequestBody RequestUsuarioDTO requestUsuarioDTO) {
		return ResponseEntity.ok(usuarioService.atualizar(id, requestUsuarioDTO));
	}

	@GetMapping("/autenticacao/{cpf}/{senha}")
	public ResponseEntity<UsuarioDTO> autenticar(@PathVariable String cpf, @PathVariable String senha) {
		UsuarioDTO usuario = usuarioService.findByCpfAndSenha(cpf, senha);
		return ResponseEntity.ok(usuario);
	}

}
