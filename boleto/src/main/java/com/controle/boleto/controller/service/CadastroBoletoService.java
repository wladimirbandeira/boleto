package com.controle.boleto.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.controle.boleto.model.Boleto;
import com.controle.boleto.model.StatusBoleto;
import com.controle.boleto.respository.Boletos;

@Service
public class CadastroBoletoService {

	@Autowired
	private Boletos boletos;

	public void salvar(Boleto boleto) {
		try{
		boletos.save(boleto);
		}catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválida");
		}
	}

	public void excluir(Long codigo) {
		boletos.delete(codigo);
	}

	public String receber(Long codigo) {
		Boleto boleto = boletos.findOne(codigo);
		boleto.setStatus(StatusBoleto.PAGO);
		boletos.save(boleto);
		
		return StatusBoleto.PAGO.getDescricao();
	}
}
