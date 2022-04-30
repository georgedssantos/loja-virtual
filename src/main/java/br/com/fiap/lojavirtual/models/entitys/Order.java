package br.com.fiap.lojavirtual.models.entitys;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.fiap.lojavirtual.models.enums.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "PEDIDO")
public class Order {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PEDIDO", nullable = false)
	private Long idOrder;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
	private Customer customer;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", nullable = false)
	private Status status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA", nullable = false)
	private Date date;
	
	@Column(name = "VALOR_TOTAL")
	private BigDecimal amount;
	
}
