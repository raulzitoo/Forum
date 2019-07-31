package br.com.alura.Forum.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.Forum.Controller.DTO.TopicoDTO;
import br.com.alura.Forum.Model.Curso;
import br.com.alura.Forum.Model.Topico;

@RestController
public class Topicos {

	@RequestMapping("/topicos")
	public List<TopicoDTO> topico(){
	
		Topico topico = new Topico("Problema ao autenticar a API", "Duvida de Mensagem de Erro", new Curso("Java", "Programação"));	
			
		return   TopicoDTO.convert(Arrays.asList(topico,topico,topico)); 	
	}
	
}
