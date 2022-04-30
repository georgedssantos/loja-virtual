package br.com.fiap.lojavirtual.services;

import java.util.List;

import br.com.fiap.lojavirtual.models.entitys.Product;

public interface ProductService {

	Product save(Product product);

	Product update(Product product);

	Product getProductById(Long idProduct);

	List<Product> findAll();

	void delete(Long idProduct);

}
