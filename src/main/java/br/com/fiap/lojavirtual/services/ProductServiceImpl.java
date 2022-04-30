package br.com.fiap.lojavirtual.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.fiap.lojavirtual.exceptions.DataViolationException;
import br.com.fiap.lojavirtual.exceptions.NotFoundException;
import br.com.fiap.lojavirtual.models.entitys.Product;
import br.com.fiap.lojavirtual.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product save(Product product) {
		product.setIdProduct(null);
		return this.productRepository.save(product);
	}

	@Override
	public Product update(Product product) {		
		checkExistence(product.getIdProduct());
		return this.productRepository.save(product);

	}
	
	@Override
	public Product getProductById(Long idProduct) {
		Product product = checkExistence(idProduct);	
		return product;
	}
	
	@Override
	public List<Product> findAll() {
		return this.productRepository.findAll();
	}
	
	@Override
	public void delete(Long idProduct) {
		checkExistence(idProduct);	
		try {
			this.productRepository.deleteById(idProduct);
		} catch (DataIntegrityViolationException e) {
			throw new DataViolationException(idProduct);
		}
	}
		
	private Product checkExistence(Long idProduct) {
		Product productFound = this.productRepository.findById(idProduct)
				.orElseThrow(() -> new NotFoundException(idProduct));
		return productFound;
	}
	
}
