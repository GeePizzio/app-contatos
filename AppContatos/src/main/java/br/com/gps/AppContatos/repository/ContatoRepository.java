package br.com.gps.AppContatos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gps.AppContatos.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{

}
