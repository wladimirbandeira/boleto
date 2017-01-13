package com.controle.boleto.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Boleto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotEmpty(message = "O Numero da pasta é obrigatória")
	@Size(max = 10, message = "O Número da pasra não podem conter mais de 10 caracteres")
	private String numero_pasta;

	@NotEmpty(message = "O número é obrigatória")
	@Size(max = 10, message = "O npumero não podem conter mais de 10 caracteres")
	private String numero;

	@NotNull(message = "Valor é obrigatório")
	@DecimalMin(value = "0.01", message = "Valor não pode ser menor que 0,01")
	@DecimalMax(value = "9999999.99", message = "Valor não pode ser maior que 9.999.999,99")
	@NumberFormat(pattern = "#,##0,00")
	private BigDecimal valor;

	@NotEmpty(message = "O comentário é obrigatória")
	@Size(max = 60, message = "Os Comentários não podem conter mais de 60 caracteres")
	private String comentario;

	@NotNull(message = "Data do boleto é obrigatória")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataBoleto;

	@Enumerated(EnumType.STRING)
	private StatusBoleto status;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNumero_pasta() {
		return numero_pasta;
	}

	public void setNumero_pasta(String numero_pasta) {
		this.numero_pasta = numero_pasta;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getDataBoleto() {
		return dataBoleto;
	}

	public void setDataBoleto(Date dataBoleto) {
		this.dataBoleto = dataBoleto;
	}

	public StatusBoleto getStatus() {
		return status;
	}

	public void setStatus(StatusBoleto status) {
		this.status = status;
	}

	public boolean isPendente() {
		return StatusBoleto.PENDENTE.equals(this.status);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Boleto other = (Boleto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	

}
