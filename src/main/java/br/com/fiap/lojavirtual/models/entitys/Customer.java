package br.com.fiap.lojavirtual.models.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "CLIENTE")
public class Customer {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CLIENTE", nullable = false)
	private Long idCustomer;
	
	@Column(name = "NOME", nullable = false)
	private String name;
	
	@Column(name = "EMAIL", nullable = false)
	private String email;
	
	@Column(name = "ENDERECO", nullable = false)
	private String address;

}
