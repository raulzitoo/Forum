package br.com.alura.Forum.Controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.Forum.Controller.DTO.DetalhesDoTopicoDto;
import br.com.alura.Forum.Controller.DTO.TopicoDTO;
import br.com.alura.Forum.Controller.Form.AtualizarTopicoForm;
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
	public ResponseEntity<TopicoDTO> cadastrar( @RequestBody @Valid TopicoForm form , UriComponentsBuilder uriBuilder ){
		
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
			
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
			
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesDoTopicoDto> detalhe(@PathVariable Long id) {
		
		Optional<Topico> topico = topicoRepository.findById(id);
		if(topico.isPresent()) {
			return ResponseEntity.ok(new DetalhesDoTopicoDto(topico.get()));
		}
		
		return ResponseEntity.notFound().build() ;
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<TopicoDTO> atualiza(@PathVariable Long id, @RequestBody @Valid AtualizarTopicoForm form ){
		
		Optional<Topico> optional = topicoRepository.findById(id);
		
		if (optional.isPresent()) {
			
			Topico topico = form.atualizar(id,topicoRepository);			
			return ResponseEntity.ok(new TopicoDTO(topico));
			
		}
		
		
		return ResponseEntity.notFound().build();
		
	}
	
	
	

	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Topico> optional = topicoRepository.findById(id);
		if (optional.isPresent()) {
			topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
		
	}
	
	
}
