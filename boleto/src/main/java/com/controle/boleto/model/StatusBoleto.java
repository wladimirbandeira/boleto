package com.controle.boleto.model;


public enum StatusBoleto {
	
	PENDENTE("Pendente"),
	PAGO("Pago");
	
	private String descricao;
	
	StatusBoleto(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
		
}
