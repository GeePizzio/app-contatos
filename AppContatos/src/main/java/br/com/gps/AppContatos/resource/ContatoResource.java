package br.com.gps.AppContatos.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gps.AppContatos.model.Contato;
import br.com.gps.AppContatos.service.ContatoService;

@RestController
@RequestMapping("/api/estoque")
public class ContatoResource {
	
	@Autowired
	private ContatoService contatoService;
	
	@PostMapping
	public ResponseEntity<Contato> save(@RequestBody Contato contato){
		Contato newContato = contatoService.save(contato);
		if(newContato == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(newContato);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Contato>> findById(@PathVariable Long id){
		Optional<Contato> findContato = contatoService.findById(id);
		if(findContato ==  null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findContato);
	}
	
	@GetMapping
	public ResponseEntity<List<Contato>> findAll(){
		List<Contato> contatos = contatoService.findAll();
		if(contatos.size() == 0) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(contatos);
	}
	
	@PutMapping
	public ResponseEntity<Contato> update(@RequestBody Contato contato){
		Contato updContato = contatoService.update(contato);
		if(updContato == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updContato);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		contatoService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
