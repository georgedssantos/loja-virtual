package br.com.fiap.lojavirtual.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.lojavirtual.exceptions.NotFoundException;
import br.com.fiap.lojavirtual.models.entitys.Customer;
import br.com.fiap.lojavirtual.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer save(Customer customer) {
		customer.setIdCustomer(null);
		return this.customerRepository.save(customer);
	}

	@Override
	public Customer update(Customer customer) {
		checkExistence(customer.getIdCustomer());
		return this.customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerById(Long idCustomer) {
		Customer customer = checkExistence(idCustomer);	
		return customer;
	}
	
	@Override
	public List<Customer> findAll() {
		return this.customerRepository.findAll();
	}
	
	private Customer checkExistence(Long idCustomer) {
		Customer customerFound = this.customerRepository.findById(idCustomer)
				.orElseThrow(() -> new NotFoundException(idCustomer));
		return customerFound;
	}

}
