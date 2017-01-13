package com.controle.boleto.controller.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controle.boleto.controller.service.exception.NomeGrupoCadastradoException;
import com.controle.boleto.model.Grupo;
import com.controle.boleto.respository.Grupos;

@Service
public class CadastroGrupoService {
	
	
	@Autowired
	private Grupos grupos;
	
	
	public void salvar(Grupo grupo) {
		Optional<Grupo> grupoOptionalCodigo = grupos.findByCodigogrupoIgnoreCase(grupo.getCodigogrupo());
		Optional<Grupo> grupoOptionalNome = grupos.findByNomeIgnoreCase(grupo.getNome());
		
		if(grupoOptionalNome.isPresent()){
			throw new NomeGrupoCadastradoException("O  nome já existem no sistema");
		}else if(grupoOptionalCodigo.isPresent()){
			throw new NomeGrupoCadastradoException("O código já existem no sistema");
		}
		grupos.save(grupo);
		
	}
	

}
