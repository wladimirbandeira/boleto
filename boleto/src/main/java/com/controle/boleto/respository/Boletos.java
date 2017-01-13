package com.controle.boleto.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controle.boleto.model.Boleto;


public interface Boletos extends JpaRepository<Boleto, Long> {

}
