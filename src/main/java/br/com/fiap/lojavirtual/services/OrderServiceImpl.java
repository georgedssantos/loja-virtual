package br.com.fiap.lojavirtual.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.lojavirtual.exceptions.NotFoundException;
import br.com.fiap.lojavirtual.models.entitys.Order;
import br.com.fiap.lojavirtual.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order save(Order order) {
		order.setIdOrder(null);
		return this.orderRepository.save(order);
	}

	@Override
	public Order update(Order order) {
		checkExistence(order.getIdOrder());
		return this.orderRepository.save(order);
	}

	@Override
	public Order getOrderById(Long idOrder) {
		Order order = checkExistence(idOrder);	
		return order;
	}
	
	private Order checkExistence(Long idOrder) {
		Order orderFound = this.orderRepository.findById(idOrder)
				.orElseThrow(() -> new NotFoundException(idOrder));
		return orderFound;
	}

}
