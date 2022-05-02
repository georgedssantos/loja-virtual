package br.com.fiap.lojavirtual.models.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "PEDIDO_ITEM")
public class OrderItem {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PEDIDO_ITEM", nullable = false)
	private Long idOrderItem;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PRODUTO", referencedColumnName = "ID_PRODUTO")
	private Product product;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PEDIDO", referencedColumnName = "ID_PEDIDO")
	private Order order;
	
	@Column(name = "QUANTIDADE", nullable = false)
	private Integer quantity;
	
	public OrderItem() {
		
	}
	
    public OrderItem(Product product, Order order, Integer quantity) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
    }
	
}
