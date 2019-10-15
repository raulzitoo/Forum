package br.com.alura.Forum.Interface;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.Forum.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{


	Optional<Usuario> findByEmail(String username);

	
	
}
