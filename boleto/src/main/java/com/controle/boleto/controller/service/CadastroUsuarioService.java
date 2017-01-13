package com.controle.boleto.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.controle.boleto.model.Usuario;
import com.controle.boleto.respository.Usuarios;

@Service
public class CadastroUsuarioService {

	@Autowired
	private Usuarios usuarios;
	
	
	public void salvar(Usuario usuario) {
		try{
		usuarios.save(usuario);
		}catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválida");
		}
	}
	
}
