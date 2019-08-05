package br.com.alura.Forum.Interface;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.Forum.Model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nome);

}
