package br.com.alura.Forum.Controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.Forum.Controller.DTO.TopicoDTO;
import br.com.alura.Forum.Controller.Form.TopicoForm;
import br.com.alura.Forum.Interface.CursoRepository;
import br.com.alura.Forum.Interface.TopicoRepository;
import br.com.alura.Forum.Model.Curso;
import br.com.alura.Forum.Model.Topico;

@RestController
@RequestMapping("/topicos")
public class Topicos {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	public List<TopicoDTO> topico(String nomeCurso){
	
		if( nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return   TopicoDTO.convert(topicos); 	
		}else {
			List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
			return   TopicoDTO.convert(topicos); 	
		}
	}
		
	@PostMapping	
	public ResponseEntity<TopicoDTO> cadastrar( @RequestBody TopicoForm form , UriComponentsBuilder uriBuilder ){
		
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
			
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
			
		
	}
	
	
	
	
}
