package br.com.fiap.lojavirtual;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.fiap.lojavirtual.models.entitys.Customer;
import br.com.fiap.lojavirtual.models.entitys.OrderItem;
import br.com.fiap.lojavirtual.models.entitys.Product;
import br.com.fiap.lojavirtual.models.enums.Size;
import br.com.fiap.lojavirtual.models.enums.Status;
import br.com.fiap.lojavirtual.repositories.OrderItemRepository;
import br.com.fiap.lojavirtual.services.CustomerService;
import br.com.fiap.lojavirtual.services.OrderItemService;
import br.com.fiap.lojavirtual.services.OrderService;
import br.com.fiap.lojavirtual.services.ProductService;

@SpringBootTest
@DisplayName("LOJA VIRTUAL TESTES DE PERSISTÊNCIA E CONSULTAS")
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersistenceAndQueryTests {
		
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	
    @BeforeEach
    public void init() {   	
    	//this.flyway.migrate();
    }
    
	@Test
	@Order(1)
	@DisplayName("CADASTRAR PRODUTOS")
	public void testarCadastroDeProdutosComSucesso() {
		// CENÁRIO
		Product productWhite = new Product("CAMISA BRANCA", "CAMISA BRANCA", null, 6, new BigDecimal(39.99), Size.M);
		Product productBlue = new Product("CAMISA AZUL", "CAMISA AZUL", null, 6, new BigDecimal(32.39), Size.M);
		Product productRed = new Product("CAMISA VERMELHA", "CAMISA VERMELHA", null, 6, new BigDecimal(16.90), Size.M);
		Product productBlack = new Product("CAMISA PRETA", "CAMISA PRETA", null, 6, new BigDecimal(42.99), Size.M);
		Product productGreen = new Product("CAMISA VERDE", "CAMISA VERDE", null, 6, new BigDecimal(16.99), Size.M);
		// AÇÃO
		this.productService.save(productWhite);
		this.productService.save(productBlue);
		this.productService.save(productRed);
		this.productService.save(productBlack);
		this.productService.save(productGreen);
		
		// VALIDAÇÃO
		this.productService.findAll().stream().map(Product::getIdProduct)
			.forEach(Assertions::assertNotNull);
				
		//this.productService.findAll().forEach(System.out::println);
	}

	@Test
	@Order(2)
	@DisplayName("CADASTRAR CLIENTE")
	public void testarCadastroClienteComSucesso() {
		// CENÁRIO
		Customer customer = new Customer();
		customer.setName("GEORGE");
		customer.setEmail("SANTOS.S.GEORGE@GMAIL.COM");
		customer.setAddress("SÃO PAULO");
		
		// AÇÃO
		customer = this.customerService.save(customer);
		
		// VALIDAÇÃO			
	   this.customerService.findAll().stream()   
	                .filter(client -> "SANTOS.S.GEORGE@GMAIL.COM".equals(client.getEmail()))  
	                .forEach(Assertions::assertNotNull);
		
		assertThat(customer).isNotNull();
		assertThat(customer.getIdCustomer()).isNotNull();
	}
	
	
	@Test
	@Order(3)
	@DisplayName("CRIAR PEDIDO")
	public void testarCadastroDePedidoDoClienteComSucesso() {
		// CENÁRIO
		br.com.fiap.lojavirtual.models.entitys.Order order = new br.com.fiap.lojavirtual.models.entitys.Order();
		Customer customer = this.customerService.getCustomerById(1L);
		order.setCustomer(customer);
		order.setStatus(Status.CRIADO);
		order.setDate(new Date());
		order.setAmount(null);
			
		// AÇÃO
		br.com.fiap.lojavirtual.models.entitys.Order orderSave = this.orderService.save(order);
	
		// VALIDAÇÃO
		assertEquals(orderSave.getStatus(), Status.CRIADO);
		
	}
	
	@Test
	@Order(4)
	@DisplayName("ADICIONAR PRODUTO NO PEDIDO DO CLEINTE")
	public void cadastroDoItemDoPedidoComSucesso() {	
		// CENÁRIO		
		br.com.fiap.lojavirtual.models.entitys.Order order = this.orderService.getOrderById(1L);
		Product productWhite =this.productService.getProductById(1L);		
		OrderItem orderItemProductWhite = new OrderItem(productWhite, order, 6);
		
		Product productBlue =this.productService.getProductById(2L);		
		OrderItem orderItemProductBlue = new OrderItem(productBlue, order, 6);
		
    	// AÇÃO		
		this.orderItemService.save(orderItemProductWhite);
		this.orderItemService.save(orderItemProductBlue);
					
		// VALIDAÇÃO
		
		List<OrderItem> orderItemlist = this.orderItemService.findAll();
		
		boolean isProductWhite = orderItemlist
	                .stream()
	                .filter(orderItem -> productWhite.getIdProduct() == orderItem.getProduct().getIdProduct())
	                .findAny().isPresent();
		
		boolean isProductBlue = orderItemlist
                .stream()
                .filter(orderItem -> productBlue.getIdProduct() == orderItem.getProduct().getIdProduct())
                .findAny().isPresent();
		
		assertTrue(isProductWhite);
		assertTrue(isProductBlue);
	}
	
	@Test
	@Order(5)
	@DisplayName("ALTERAR A QTD DO PRODUTO")
	public void testarAlerarQtdDosProdutosAdicionadosComSucesso() {			
		// CENÁRIO
		OrderItem orderItemProductWhite = this.orderItemService.getOrderItemById(1L);
		orderItemProductWhite.setQuantity(2);
		
		// AÇÃO
		OrderItem OrderItemSave = this.orderItemService.save(orderItemProductWhite);
		
		// VALIDAÇÃO
		assertEquals(OrderItemSave.getQuantity(), 2);
	}
	
	@Test
	@Order(6)
	@DisplayName("REMOVER PRODUTO NO PEDIDO")
	public void testarRetiradaDoProdutoNoPedidoComSucesso() {
		// CENÁRIO
		OrderItem orderItemFound = this.orderItemService.getOrderItemById(3L);
		
		// AÇÃO
		this.orderItemService.delete(orderItemFound.getIdOrderItem());
		
		// VALIDAÇÃO
		OrderItem orderItem = this.orderItemRepository.findById(3L).orElse(null);
	
		
		OrderItem orderInList = this.orderItemRepository.findAll().stream().filter(item -> item.getIdOrderItem() == 3L)
	       .findFirst().orElse(null);
		
		assertThat(orderItem).isNull();
		assertThat(orderInList).isNull();
	}
	
	@Test
	@Order(7)
	@DisplayName("COMPRAR")
	public void testarCompraDosProdutosComSucesso() {
		// CENÁRIO
		br.com.fiap.lojavirtual.models.entitys.Order order = this.orderService.getOrderById(1L);
		order.setStatus(Status.CONFIRMADO);
		order.setDate(new Date());

		// AÇÃO
		br.com.fiap.lojavirtual.models.entitys.Order orderUpdate = this.orderService.update(order);
		
		// VALIDAÇÃO
		assertEquals(orderUpdate.getStatus(), Status.CONFIRMADO);
	}
	
}
