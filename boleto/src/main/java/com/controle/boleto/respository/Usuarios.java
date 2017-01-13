package com.controle.boleto.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controle.boleto.model.Usuario;

public interface Usuarios extends JpaRepository<Usuario, Long> {

	public Optional<Usuario> findByEmail(String email);

}