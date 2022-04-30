package br.com.fiap.lojavirtual;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.fiap.lojavirtual.business.KeepOrder;
import br.com.fiap.lojavirtual.models.entitys.Customer;
import br.com.fiap.lojavirtual.models.entitys.OrderItem;
import br.com.fiap.lojavirtual.models.entitys.Product;
import br.com.fiap.lojavirtual.models.enums.Size;
import br.com.fiap.lojavirtual.models.enums.Status;
import br.com.fiap.lojavirtual.services.CustomerService;
import br.com.fiap.lojavirtual.services.OrderItemService;
import br.com.fiap.lojavirtual.services.ProductService;

@SpringBootTest
@DisplayName("LOJA VIRTUAL TESTES DE PERSISTÊNCIA E CONSULTAS")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersistenceAndQueryTests {
		
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private KeepOrder keepOrder;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderItemService orderItemService;

	
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
		this.productService.findAll().forEach(System.out::println);
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
		System.out.println("CLIENTE "+ customer.getName());
		assertThat(customer).isNotNull();
		assertThat(customer.getIdCustomer()).isNotNull();
	}
	
	
	@Test
	@Order(3)
	@DisplayName("CRIAR PEDIDO")
	public void testarCadastroDePedidoDoClienteComSucesso() {		
		// AÇÃO
		br.com.fiap.lojavirtual.models.entitys.Order order = this.keepOrder.createOrderByCustomer(1l);
	
		// VALIDAÇÃO		
		System.out.println("PEDIDO "+ order.getStatus());
		System.out.println("CODIGO "+ order.getIdOrder());
		System.out.println("DATA "+ order.getDate());
		assertEquals(order.getStatus(), Status.CRIADO);
	}
	
	@Test
	@Order(4)
	@DisplayName("ADICIONAR PRODUTO NO PEDIDO")
	public void cadastroDoItemDoPedidoComSucesso() {	
		// CENÁRIO
		this.productService.findAll().forEach(System.out::println);
		
    	// AÇÃO
    	this.keepOrder.addProductToOrder(1L, 1l);
    	this.keepOrder.addProductToOrder(1L, 2l);
    	this.keepOrder.addProductToOrder(1L, 3l);
				
		// VALIDAÇÃO
		this.orderItemService.findAll().forEach(product -> {
			System.out.println("PRODUTO "+product.getProduct().getIdProduct()+ " ADICIONADO");
		});
	}
	
	@Test
	@Order(5)
	@DisplayName("ALTERAR A QTD DOS PRODUTOS ADICIONADOS")
	public void testarAlerarQtdDosProdutosAdicionadosComSucesso() {			
		// CENÁRIO
		this.orderItemService.findAll().forEach(orderItem -> {
			// AÇÃO
			orderItem.setQuantity(2);
			this.keepOrder.updateProductOnOrder(orderItem);
		});
		
		// VALIDAÇÃO
		this.orderItemService.findAll().forEach(product -> {
			System.out.println("PRODUTO "+product.getProduct().getIdProduct()+ " QTD "+ product.getQuantity());
		});
	}
	
	@Test
	@Order(6)
	@DisplayName("REMOVER PRODUTO NO PEDIDO")
	public void testarRetiradaDoProdutoNoPedidoComSucesso() {
		// CENÁRIO
		OrderItem orderItem = this.orderItemService.getOrderItemById(3L);
		
		// AÇÃO
		this.keepOrder.removeProductToOrder(orderItem.getIdOrderItem());
		
		// VALIDAÇÃO
		List<OrderItem> productList = this.orderItemService.findAll();
		productList.forEach(product -> {
			System.out.println("PRODUTO "+product.getProduct().getIdProduct()+ " QTD "+ product.getQuantity());
		});
	}
	
	@Test
	@Order(7)
	@DisplayName("COMPRAR")
	public void testarCompraDosProdutosComSucesso() {			
		// AÇÃO
		br.com.fiap.lojavirtual.models.entitys.Order order = this.keepOrder.registerOrder(1l);
		
		// VALIDAÇÃO
		System.out.println("PEDIDO "+ order.getStatus());
	}
	
}
