package com.bdc.desafio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdc.desafio.core.services.GenericCrudService;
import com.bdc.desafio.core.util.Utils;
import com.bdc.desafio.dtos.request.RequestUsuarioDTO;
import com.bdc.desafio.dtos.response.ResponseUsuarioDTO;
import com.bdc.desafio.exception.RecursoNaoEncontradoException;
import com.bdc.desafio.exception.RegistroJaExistenteException;
import com.bdc.desafio.model.entity.Cidade;
import com.bdc.desafio.model.entity.Endereco;
import com.bdc.desafio.model.entity.Perfil;
import com.bdc.desafio.model.entity.Usuario;
import com.bdc.desafio.model.repositories.UsuarioRepository;

@Service
public class UsuarioService extends GenericCrudService<Usuario, Long, UsuarioRepository> {

	@Autowired
	CidadeService cidadeService;

	@Autowired
	PerfilService perfilService;

	public ResponseUsuarioDTO incluir(RequestUsuarioDTO requestDTO) {

		Optional<Usuario> usuarioCadastrado = this.repository.findByCpf(Utils.removerFormatacao(requestDTO.getCpf()));
		
		if (usuarioCadastrado.isPresent()) {
			throw new RegistroJaExistenteException();
		}

		Usuario usuario = new Usuario(requestDTO.getCpf(), requestDTO.getNome(), requestDTO.getSenha(), 
				obterDadosEndereco(requestDTO), obterDadosPerfil(requestDTO));

		usuario = super.salvar(usuario);

		return convertModelToDto(usuario);

	}

	private Endereco obterDadosEndereco(RequestUsuarioDTO requestUsuarioDTO) {
		
		Cidade cidade = cidadeService.buscar(requestUsuarioDTO.getEndereco().getCodigoCidade());
		
		if (cidade == null) {
			throw new RecursoNaoEncontradoException("Cidade não cadastrada.");
		}
		
		return new Endereco(cidade, requestUsuarioDTO.getEndereco().getLogradouro(),
				requestUsuarioDTO.getEndereco().getNumero(), requestUsuarioDTO.getEndereco().getBairro());
		
	}

	private Perfil obterDadosPerfil(RequestUsuarioDTO requestUsuarioDTO) {
		
		Perfil perfil = perfilService.buscar(requestUsuarioDTO.getCodigoPerfil());
		
		if (perfil == null) {
			throw new RecursoNaoEncontradoException("Perfil não cadastrado.");
		}
		
		return perfil;
	}

	public List<ResponseUsuarioDTO> findAll() {

		List<Usuario> usuarios = this.repository.findAll();

		if (usuarios.isEmpty()) {
			throw new RecursoNaoEncontradoException();
		}

		List<ResponseUsuarioDTO> usuariosDTO = new ArrayList<ResponseUsuarioDTO>();

		usuarios.forEach(usuario -> {
			usuariosDTO.add(convertModelToDto(usuario));
		});

		return usuariosDTO;

	}

	public ResponseUsuarioDTO findById(Long id) {

		Usuario usuario = super.buscar(id);
		
		if (usuario == null) {
			throw new RecursoNaoEncontradoException();
		}

		return convertModelToDto(usuario);
	}

	public List<Usuario> findUsersByUf(Long idUf) {

		List<Usuario> usuarios = this.repository.findUsersByUf(idUf);

		if (usuarios.isEmpty()) {
			throw new RecursoNaoEncontradoException();
		}

		return usuarios;
	}

	public List<ResponseUsuarioDTO> findByPerfil(Long idPerfil) {

		List<Usuario> usuarios = this.repository.findByPerfil(new Perfil(idPerfil));

		if (usuarios.isEmpty()) {
			throw new RecursoNaoEncontradoException();
		}

		List<ResponseUsuarioDTO> usuariosDTO = new ArrayList<ResponseUsuarioDTO>();

		usuarios.forEach(usuario -> {
			usuariosDTO.add(convertModelToDto(usuario));
		});

		return usuariosDTO;
	}

	public ResponseUsuarioDTO atualizar(Long id, RequestUsuarioDTO requestDTO) {

		Optional<Usuario> usuarioCadastrado = Optional.ofNullable(this.repository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException()));

		Usuario usuario = new Usuario(usuarioCadastrado.get().getId(), Utils.removerFormatacao(requestDTO.getCpf()), 
				requestDTO.getNome(), requestDTO.getSenha(), obterDadosEndereco(requestDTO), obterDadosPerfil(requestDTO));
		
		Usuario usuarioAtualizado = super.salvar(usuario);

		return convertModelToDto(usuarioAtualizado);
	}

	public void excluir(Long id) {
		
		Optional.ofNullable(this.repository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException()));
		
		super.remover(id);
	}

	private ResponseUsuarioDTO convertModelToDto(Usuario usuario) {
		
		ModelMapper modelMapper = new ModelMapper();
		ResponseUsuarioDTO usuarioDTO = new ResponseUsuarioDTO();
		modelMapper.map(usuario, usuarioDTO);

		return usuarioDTO;
	}

}
