package com.bdc.desafio.model.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.bdc.desafio.core.model.GenericCrudRepository;
import com.bdc.desafio.model.entity.Perfil;
import com.bdc.desafio.model.entity.Usuario;

public interface UsuarioRepository extends GenericCrudRepository<Usuario, Long> {

	Optional<Usuario> findByCpfAndSenha(String cpf, String senha);

	@Query("SELECT usuario FROM Usuario usuario, Endereco endereco, Cidade cidade, UnidadeFederativa unidadefederativa WHERE unidadefederativa.id = :idUf and cidade.estado = unidadefederativa.id and cidade.id = endereco.cidade and endereco.id = usuario.endereco")
	List<Usuario> findUsersByUf(Long idUf);

	List<Usuario> findByPerfil(Perfil perfil);

	Optional<Usuario> findByCpf(String cpf);
	
}
