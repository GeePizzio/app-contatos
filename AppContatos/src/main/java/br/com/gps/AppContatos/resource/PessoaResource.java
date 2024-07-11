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

import br.com.gps.AppContatos.model.Pessoa;
import br.com.gps.AppContatos.service.PessoaService;

@RestController
@RequestMapping("/api/pessoas") //http://localhost:8080/api/pessoas
public class PessoaResource {
	
	@Autowired
	PessoaService pessoaService;
	
	@GetMapping("pessoa") //http://localhost:8080/api/pessoas/pessoa
	public ResponseEntity<Pessoa> getPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(1L);
		pessoa.setNome("João Silva");
		pessoa.setEndereco("Rua A, 2");
		pessoa.setCidade("São Paulo");
		pessoa.setCep("01111-111");
		pessoa.setUf("SP");
		
		return ResponseEntity.ok(pessoa);
	}
	
	@GetMapping("salvar") //http://localhost:8080/api/pessoas/salvar
	public ResponseEntity<Pessoa> save() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("João Silva");
		pessoa.setEndereco("Rua A, 2");
		pessoa.setCidade("São Paulo");
		pessoa.setCep("01111-111");
		pessoa.setUf("SP");
		
		Pessoa pessoaResposta = pessoaService.save(pessoa);
		if(pessoaResposta == null) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pessoaResposta);
	}
	
	@GetMapping //http://localhost:8080/api/pessoas
	public ResponseEntity<List<Pessoa>> findAllPessoas(){
		List<Pessoa> pessoas = pessoaService.findAll();
		if(pessoas == null) {
			return ResponseEntity.notFound().build();
		}
		if(pessoas.size() == 0) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pessoas);
	}
	
	@GetMapping("/{id}") //http://localhost:8080/api/pessoas/10
	public ResponseEntity<Optional<Pessoa>> findById(@PathVariable Long id){
		Optional<Pessoa> pessoa = pessoaService.findById(id);
		if(pessoa.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pessoa);
	}
	
	@PostMapping //http://localhost:8080/api/pessoas
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa){
		Pessoa newPessoa = pessoaService.save(pessoa);
		if(newPessoa == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(newPessoa);
	}
	
	@PutMapping //http://localhost:8080/api/pessoas
	public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa){
		Pessoa updPessoa = pessoaService.update(pessoa);
		if(updPessoa == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updPessoa);
	}
	
	@DeleteMapping("/{id}") //http://localhost:8080/api/pessoas/2
	public ResponseEntity<?> delete(@PathVariable Long id){
		pessoaService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //Status code 204
	}
}

