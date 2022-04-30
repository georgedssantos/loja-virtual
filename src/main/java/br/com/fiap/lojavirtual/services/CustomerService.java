package br.com.fiap.lojavirtual.services;

import java.util.List;

import br.com.fiap.lojavirtual.models.entitys.Customer;

public interface CustomerService {
	
	Customer save(Customer customer);

	Customer update(Customer customer);

	Customer getCustomerById(Long idCustomer);
	
	List<Customer> findAll();

}
