package br.com.fiap.lojavirtual.services;

import java.util.List;

import br.com.fiap.lojavirtual.models.entitys.OrderItem;

public interface OrderItemService {
	
	OrderItem save(OrderItem orderItem);

	OrderItem update(OrderItem orderItem);
	
	void delete(Long idOrder);
	
	OrderItem getOrderItemById(Long idOrder);
	
	List<OrderItem> findAll();
	
}
