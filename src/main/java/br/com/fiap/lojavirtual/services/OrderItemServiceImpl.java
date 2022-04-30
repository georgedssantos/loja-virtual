package br.com.fiap.lojavirtual.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.fiap.lojavirtual.exceptions.DataViolationException;
import br.com.fiap.lojavirtual.exceptions.NotFoundException;
import br.com.fiap.lojavirtual.models.entitys.OrderItem;
import br.com.fiap.lojavirtual.repositories.OrderItemRepository;

@Service
public class OrderItemServiceImpl implements OrderItemService {
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public OrderItem save(OrderItem orderItem) {
		orderItem.setIdOrderItem(null);
		return this.orderItemRepository.save(orderItem);
	}

	@Override
	public OrderItem update(OrderItem orderItem) {
		checkExistence(orderItem.getIdOrderItem());
		return this.orderItemRepository.save(orderItem);
	}

	@Override
	public void delete(Long idOrder) {
		checkExistence(idOrder);	
		try {
			this.orderItemRepository.deleteById(idOrder);
		} catch (DataIntegrityViolationException e) {
			throw new DataViolationException(idOrder);
		}
	}
	
	@Override
	public OrderItem getOrderItemById(Long idOrder) {
		OrderItem orderItem = checkExistence(idOrder);	
		return orderItem;
	}
	
	@Override
	public List<OrderItem> findAll() {
		return this.orderItemRepository.findAll();
	}

	private OrderItem checkExistence(Long idOrderItem) {
		OrderItem orderItemFound = this.orderItemRepository.findById(idOrderItem)
				.orElseThrow(() -> new NotFoundException(idOrderItem));
		return orderItemFound;
	}

}
