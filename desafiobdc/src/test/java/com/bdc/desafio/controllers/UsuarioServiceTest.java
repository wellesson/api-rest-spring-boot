package com.bdc.desafio.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bdc.desafio.dtos.UsuarioDTO;
import com.bdc.desafio.dtos.request.RequestEnderecoDTO;
import com.bdc.desafio.dtos.request.RequestUsuarioDTO;
import com.bdc.desafio.model.entity.Cidade;
import com.bdc.desafio.model.entity.Endereco;
import com.bdc.desafio.model.entity.UnidadeFederativa;
import com.bdc.desafio.model.entity.Usuario;
import com.bdc.desafio.model.repositories.UsuarioRepository;
import com.bdc.desafio.services.CidadeService;
import com.bdc.desafio.services.PerfilService;
import com.bdc.desafio.services.UsuarioService;

@SpringBootTest
public class UsuarioServiceTest {

	@InjectMocks
	private UsuarioService usuarioService;

	@InjectMocks
	private CidadeService cidadeService;

	@InjectMocks
	private PerfilService perfilService;

	@MockBean
	private UsuarioRepository usuarioRepository;

	private List<Usuario> usuarios;
	private Usuario usuario;

	private RequestUsuarioDTO request;

	private RequestEnderecoDTO requestEndereco;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		usuarios = new ArrayList<Usuario>();
		usuario = new Usuario();
		usuario.setCpf("00011122233");
		usuario.setNome("Wellesson Vieira");
		usuario.setSenha("1234");
		Endereco endereco = new Endereco();
		endereco.setBairro("Portal do sol");
		UnidadeFederativa estado = new UnidadeFederativa();
		estado.setNome("Paraíba");
		estado.setSigla("PB");
		Cidade cidade = new Cidade();
		cidade.setEstado(estado);
		cidade.setNome("Cajazeiras");
		endereco.setCidade(cidade);
		usuario.setEndereco(endereco);
		usuarios.add(usuario);
		requestEndereco = new RequestEnderecoDTO();
		request = new RequestUsuarioDTO();
	}

	@Test
	public void incluirTest() {
		when(usuarioRepository.saveAndFlush(usuario)).thenReturn(usuario);
		assertEquals(usuario, usuarioService.salvar(usuario));
	}

	@Test
	public void atualizarTest() {
		when(usuarioRepository.saveAndFlush(usuario)).thenReturn(usuario);
		assertEquals(usuario, usuarioService.salvar(usuario));
	}

}
