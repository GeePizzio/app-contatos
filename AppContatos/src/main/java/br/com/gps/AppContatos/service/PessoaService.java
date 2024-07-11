package br.com.gps.AppContatos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gps.AppContatos.model.Pessoa;
import br.com.gps.AppContatos.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa save(Pessoa pessoa) {
		
		if(pessoa.getNome() == null) {
			System.out.println("Nome da pessoa está vazio!");
			return null;
		}
		
		//Gravar
		try {
			return pessoaRepository.save(pessoa);
		}catch(Exception e){
			System.out.println("ERR: Erro ao inserir pessoa " + pessoa.toString() + ": " + e.getMessage());
			return null;
		}
	}
	
	public List<Pessoa> findAll(){
		return pessoaRepository.findAll();
	}
	
	public Optional<Pessoa> findById(Long id) {
		return pessoaRepository.findById(id);
	}
	
	public Pessoa update(Pessoa pessoa) {
		Optional<Pessoa> findPessoa = pessoaRepository.findById(pessoa.getId());
		if(findPessoa.isPresent()) {
			Pessoa updPessoa = findPessoa.get();
			updPessoa.setNome(pessoa.getNome());
			updPessoa.setEndereco(pessoa.getEndereco());
			updPessoa.setCep(pessoa.getCep());
			updPessoa.setCidade(pessoa.getCidade());
			updPessoa.setUf(pessoa.getUf());
			return pessoaRepository.save(updPessoa);
		}
		return pessoaRepository.save(pessoa);
	}
	
	public void delete(Long id) {
		pessoaRepository.deleteById(id);
	}
}
