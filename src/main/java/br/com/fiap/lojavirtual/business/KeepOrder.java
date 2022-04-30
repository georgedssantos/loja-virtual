package br.com.fiap.lojavirtual.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.lojavirtual.models.entitys.Customer;
import br.com.fiap.lojavirtual.models.entitys.Order;
import br.com.fiap.lojavirtual.models.entitys.OrderItem;
import br.com.fiap.lojavirtual.models.entitys.Product;
import br.com.fiap.lojavirtual.models.enums.Status;
import br.com.fiap.lojavirtual.services.CustomerService;
import br.com.fiap.lojavirtual.services.OrderItemService;
import br.com.fiap.lojavirtual.services.OrderService;
import br.com.fiap.lojavirtual.services.ProductService;

@Service
public class KeepOrder {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private ProductService productService;
	
	/**
	 * CRIAR PEDIDO DO CLIENTE
	 * @param idCustomer
	 * @return
	 */
	public Order createOrderByCustomer(Long idCustomer) {
		Order order = new Order();
		Customer customer = this.customerService.getCustomerById(idCustomer);
		order.setCustomer(customer);
		order.setStatus(Status.CRIADO);
		order.setDate(new Date());
		order.setAmount(null);
		return this.orderService.save(order);		
	}
	
	/**
	 * ADICIONAR PRODUTO NO PEDIDO DO CLEINTE
	 * @param idOrder
	 * @param idProduct
	 * @return
	 */
	public OrderItem addProductToOrder(Long idOrder, Long idProduct) {			
		OrderItem orderItem = new OrderItem();
		Order order = this.orderService.getOrderById(idOrder);
		orderItem.setOrder(order);
		Product product =this.productService.getProductById(idProduct);
		orderItem.setProduct(product);
		orderItem.setQuantity(1);
		return this.orderItemService.save(orderItem);
	}
	

	/**
	 * ATUALIZAR A QTD DO PRODUTO NO PEDIDO DO CLIENTE
	 * @param idOrderItem
	 * @return
	 */
	public OrderItem updateProductOnOrder(OrderItem orderItem) {
		return this.orderItemService.update(orderItem);	
	}

	/**
	 * REMOVER PRODUTO DO PEDIDO
	 * @param idOrderItem
	 */
	public void removeProductToOrder(Long idOrderItem) {
		this.orderItemService.delete(idOrderItem);	
	}
	
	/**
	 * EFETUAR COMPRA DO PEDIDO (MODAR STATUS PARA CONFIRMADO)
	 * @param idOrder
	 */
	public Order registerOrder(Long idOrder) {		
		Order order = this.orderService.getOrderById(idOrder);
		order.setStatus(Status.CONFIRMADO);
		order.setDate(new Date());
		return this.orderService.update(order);
	}
		
}
