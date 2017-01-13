package com.controle.boleto.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controle.boleto.model.Grupo;



public interface Grupos  extends JpaRepository<Grupo, Long> {

	public Optional<Grupo> findByNomeIgnoreCase(String nome);
	
	public Optional<Grupo> findByCodigogrupoIgnoreCase(String codigogrupo);
}
