package com.grupo13.elasticSearch.services;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.Category;
import com.grupo13.elasticSearch.models.Product;
import com.grupo13.elasticSearch.models.ProductOnSale;
import com.grupo13.elasticSearch.repositories.ProductRepository;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.grupo13.elasticSearch.utils.Mapper.*;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    private RestHighLevelClient client;

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

    /**
     * @return el producto más pesado
     */
    public Product getHeaviestProduct() {
        Product product = new Product();
        try {
            SearchRequest searchRequest = new SearchRequest("products");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().size(1);
            searchRequest.source(searchSourceBuilder.sort(new FieldSortBuilder("weight").order(SortOrder.DESC)));

            SearchResponse res1 = client.search(searchRequest, RequestOptions.DEFAULT);
            var mapProduct = res1.getHits().getAt(0).getSourceAsMap();

            product = MapProduct(mapProduct);
        }
        catch (Exception e) {}

        return product;
    }

    /**
     * @return una lista con los 3 productos diferentes más costosos
     */
    public List<Product> getTop3MoreExpensiveProducts() {
        List<Product> top3MoreExpensive = new ArrayList<Product>();

        try {
            SearchRequest searchRequest = new SearchRequest("products_on_sale");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().size(3);
            searchSourceBuilder.aggregation(AggregationBuilders.terms("products").field("price"));
            searchRequest.source(searchSourceBuilder.sort(new FieldSortBuilder("price").order(SortOrder.DESC)));

            SearchResponse res1 = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits  = res1.getHits().getHits();

            for(SearchHit hit : hits){
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                ProductOnSale productOnSale = MapProductOnSale(sourceAsMap);
                top3MoreExpensive.add(productOnSale.getProduct());
            }
        }
        catch (Exception e) {}

        return top3MoreExpensive;
    }
}
