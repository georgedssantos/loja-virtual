package br.com.fiap.lojavirtual.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Size {
	
	P("Pequeno"),
	M("Médio"),
	G("Grande");
	
	@Getter
	private String description;

}
