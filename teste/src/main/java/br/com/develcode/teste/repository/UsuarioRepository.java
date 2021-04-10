package br.com.develcode.teste.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.develcode.teste.entidades.Usuario;

//PAra evitar refazer todos os codigos básicos, já existem JPA
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


	List<Usuario> findByNome(String nome);
	
	@Query("SELECT u FROM Usuario u WHERE u.nome = :nome")
	List<Usuario> buscaPeloNome(@Param("nome") String nome);

}
