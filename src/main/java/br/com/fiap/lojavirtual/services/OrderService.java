package br.com.fiap.lojavirtual.services;

import br.com.fiap.lojavirtual.models.entitys.Order;

public interface OrderService {
	
	Order save(Order order);
	
	Order update(Order order);
	
	Order getOrderById(Long idOrder);

}
