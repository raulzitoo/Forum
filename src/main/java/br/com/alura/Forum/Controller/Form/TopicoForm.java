package br.com.alura.Forum.Controller.Form;

import br.com.alura.Forum.Interface.CursoRepository;
import br.com.alura.Forum.Model.Curso;
import br.com.alura.Forum.Model.Topico;

public class TopicoForm {

	private String titulo;
	private String mensagem;
	private String nomeDoCurso;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getNomeDoCurso() {
		return nomeDoCurso;
	}
	public void setNomeDoCurso(String nomeDoCurso) {
		this.nomeDoCurso = nomeDoCurso;
	}
	public Topico converter(CursoRepository cursoRepository) {
		Curso curso = cursoRepository.findByNome(nomeDoCurso);
		return new Topico(titulo,mensagem,curso);
	}
	
	
	
}
