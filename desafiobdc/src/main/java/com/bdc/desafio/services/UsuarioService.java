package com.bdc.desafio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdc.desafio.core.services.GenericCrudService;
import com.bdc.desafio.dtos.UsuarioDTO;
import com.bdc.desafio.dtos.request.RequestUsuarioDTO;
import com.bdc.desafio.model.entity.Cidade;
import com.bdc.desafio.model.entity.Endereco;
import com.bdc.desafio.model.entity.Perfil;
import com.bdc.desafio.model.entity.Usuario;
import com.bdc.desafio.model.repositories.UsuarioRepository;
import com.br.desafio.exception.RecursoNaoEncontradoException;
import com.br.desafio.exception.RegistroJaExistenteException;

@Service
public class UsuarioService extends GenericCrudService<Usuario, Long, UsuarioRepository> {

	@Autowired
	CidadeService cidadeService;

	@Autowired
	PerfilService perfilService;

	private ModelMapper modelMapper = new ModelMapper();

	public UsuarioDTO incluir(RequestUsuarioDTO requestUsuarioDTO) {

		Optional<Usuario> usuarioCadastrado = this.repository.findByCpf(requestUsuarioDTO.getCpf());

		if (usuarioCadastrado.isPresent()) {
			throw new RegistroJaExistenteException();
		}
		
		Cidade cidade = cidadeService.buscar(requestUsuarioDTO.getEndereco().getCodigoCidade());
		Perfil perfil = perfilService.buscar(requestUsuarioDTO.getCodigoPerfil());
		Endereco endereco = new Endereco(cidade, requestUsuarioDTO.getEndereco().getLogradouro(),
				requestUsuarioDTO.getEndereco().getNumero(), requestUsuarioDTO.getEndereco().getBairro());
		Usuario usuario = new Usuario(requestUsuarioDTO.getCpf(), requestUsuarioDTO.getNome(),
				requestUsuarioDTO.getSenha(), endereco, perfil);

		usuario = super.salvar(usuario);

		return convertModelToDto(usuario);

	}

	public List<UsuarioDTO> findAll() {
		List<Usuario> usuarios = this.repository.findAll();

		if (usuarios.isEmpty())
			throw new RecursoNaoEncontradoException();

		List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();

		usuarios.forEach(usuario -> {
			usuariosDTO.add(convertModelToDto(usuario));
		});

		return usuariosDTO;

	}

	public UsuarioDTO findById(Long id) {

		Optional<Usuario> usuario = Optional.ofNullable(this.repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException()));
		
		return convertModelToDto(usuario.get());
	}

	public UsuarioDTO findByCpfAndSenha(String cpf, String senha) {
		
		Optional<Usuario> usuario = Optional.ofNullable(this.repository.findByCpfAndSenha(cpf, senha).orElseThrow(() -> new RecursoNaoEncontradoException()));

		return convertModelToDto(usuario.get());
	}

	public List<Usuario> findUsersByUf(Integer idUf) {
		
		List<Usuario> usuarios = this.repository.findUsersByUf(idUf);
		
		if (usuarios.isEmpty()) {
			throw new RecursoNaoEncontradoException();
		}

		return usuarios;
	}

	public List<Usuario> findByPerfil(Long idPerfil) {
		
		List<Usuario> usuarios = this.repository.findByPerfil(new Perfil(idPerfil));
		
		if (usuarios.isEmpty())
			throw new RecursoNaoEncontradoException();
		
		List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
		
		usuarios.forEach(usuario -> {
			usuariosDTO.add(convertModelToDto(usuario));
		});

		return usuarios;
	}
	
	public UsuarioDTO atualizar(Long id, UsuarioDTO usuarioDTO) {
		
		if (!existe(id)) {
			new RecursoNaoEncontradoException();
		}
		
		Usuario usuario = this.repository.saveAndFlush(convertDtoToModel(usuarioDTO));
		
		return convertModelToDto(usuario);
	}
	
	

	private Usuario convertDtoToModel(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		modelMapper.map(usuarioDTO, usuario);

		return usuario;
	}

	private UsuarioDTO convertModelToDto(Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		modelMapper.map(usuario, usuarioDTO);

		return usuarioDTO;
	}

}
