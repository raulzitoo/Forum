package br.com.alura.Forum.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.Forum.Controller.DTO.TopicoDTO;
import br.com.alura.Forum.Interface.TopicoRepository;
import br.com.alura.Forum.Model.Curso;
import br.com.alura.Forum.Model.Topico;

@RestController
public class Topicos {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@RequestMapping("/topicos")
	public List<TopicoDTO> topico(String nomeCurso){
	
		if( nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return   TopicoDTO.convert(topicos); 	
		}else {
			List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
			return   TopicoDTO.convert(topicos); 	
		}
			
			
		
	}
	
}
