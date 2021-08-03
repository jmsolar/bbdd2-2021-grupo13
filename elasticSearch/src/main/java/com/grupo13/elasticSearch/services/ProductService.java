package com.grupo13.elasticSearch.services;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.Category;
import com.grupo13.elasticSearch.models.Product;
import com.grupo13.elasticSearch.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * @param name nombre del producto a buscar
     * @return
     */
    @GetMapping("/{name}")
    public Optional<Product> findByName(String name) {
        Product product = this.productRepository.findByName(name);
        Optional<Product> opt = Optional.ofNullable(product);
        return opt;
    }

    /**
     *  Crea y devuelve un nuevo Producto.
     * @param name nombre del producto a ser creado
     * @param weight peso actual del producto
     * @param category categor{ia del producto
     * @return el producto creado
     * @throws ElasticSearchException
     */
    public Product create(String name, Float weight, Category category) throws ElasticSearchException {
        ElasticSearchException ex = new ElasticSearchException();

        if (this.findByName(name).isPresent()) ex.constraintViolation();

        Product newProduct = new Product(name, weight, category);
        this.productRepository.save(newProduct);

        return newProduct;
    }
}
