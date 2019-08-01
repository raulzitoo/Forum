package br.com.alura.Forum.Interface;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.Forum.Model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{

	List<Topico> findByCursoNome(String nomeCurso);

	
	
}
