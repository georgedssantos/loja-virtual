package br.com.fiap.lojavirtual.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Status {
	
	CRIADO("Criado"),
	CONFIRMADO("Confirmado"),
	ENTREGUE("Entregue"),
	CANCELADO("Cancelado");
	
	@Getter
	private String description;

}
