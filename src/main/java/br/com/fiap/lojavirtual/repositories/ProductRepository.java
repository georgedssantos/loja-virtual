package br.com.fiap.lojavirtual.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.lojavirtual.models.entitys.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
