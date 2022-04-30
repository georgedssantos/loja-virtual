package br.com.fiap.lojavirtual.models.entitys;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.fiap.lojavirtual.models.enums.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "PRODUTO")
public class Product {
		
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PRODUTO", nullable = false)
	private Long idProduct;
	
	@Column(name = "NOME", nullable = false)
	private String name;
	
	@Column(name = "DESCRICAO", nullable = false)
	private String description;
	
	@Column(name = "QUANTIDADE", nullable = false)
	private Integer quantity;
	
	@Column(name = "PRECO", nullable = false)
	private BigDecimal price;
	
	@Column(name = "FOTO")
	private byte[] photo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "SIZE", nullable = false)
	private Size size;
	
	public Product() {
	
	}
	
    public Product(String name, String description, byte[] photo, Integer quantity, BigDecimal price, Size size) {
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.quantity = quantity;
        this.price = price;
        this.size = size;
    }

}
