package com.bdc.desafio.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bdc.desafio.model.entity.Cidade;
import com.bdc.desafio.model.entity.Endereco;
import com.bdc.desafio.model.entity.UnidadeFederativa;
import com.bdc.desafio.model.entity.Usuario;
import com.bdc.desafio.model.repositories.UsuarioRepository;

@SpringBootTest
public class UsuarioServiceTest {

	@InjectMocks
	private UsuarioService usuarioService;

	@MockBean
	private UsuarioRepository usuarioRepository;

	private List<Usuario> usuarios;
	
	private Usuario usuario;

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
	}

	@Test
	public void salvarTest() {
		when(usuarioRepository.saveAndFlush(usuario)).thenReturn(usuario);
		assertEquals(usuario, usuarioService.salvar(usuario));
	}

}
