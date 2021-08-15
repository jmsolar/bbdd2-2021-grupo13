package com.grupo13.elasticSearch.services;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.Product;
import com.grupo13.elasticSearch.models.ProductOnSale;
import com.grupo13.elasticSearch.models.Provider;
import com.grupo13.elasticSearch.models.Purchase;
import com.grupo13.elasticSearch.repositories.ProductOnSaleRepository;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.grupo13.elasticSearch.utils.Mapper.MapProductOnSale;
import static com.grupo13.elasticSearch.utils.Mapper.MapPurchase;

@Service
public class ProductOnSaleService {
    private ProductOnSaleRepository productOnSaleRepository;

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    public ProductOnSaleService(ProductOnSaleRepository productOnSaleRepository) {
        this.productOnSaleRepository = productOnSaleRepository;
    }

    /**
     * @param id del producto en venta a buscar
     * @return
     */
    public Optional<ProductOnSale> findById(String id) {
        return this.productOnSaleRepository.findById(id);
    }

    /**
     *
     * @param product producto al cual se le va a dar precio
     * @param provider proveedor del producto al cual se le va a dar precio
     * @param price precio del producto
     * @param initialDate fecha desde cuando el producto vale ese precio
     * @return el precio para el producto
     * @implNote si el producto ya tiene un precio para el proveedor se actualiza la fecha de fin en un día antes a la initialDate
     *  y se el crea el nuevo precio
     * @throws ElasticSearchException si initialDate es anterior a la initialDate actual.
     */
    public ProductOnSale create(Product product, Provider provider, Float price, Date initialDate) throws ElasticSearchException {
        ElasticSearchException ex = new ElasticSearchException();

        /*ProductOnSale prodOnSale = this.findLastById(provider.getId(), product.getId());
        if (prodOnSale.getId() != null && prodOnSale.getInitialDate().after(initialDate)) ex.priceValidity();

        if (prodOnSale.getId() != null) {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(initialDate);
            cal.add(Calendar.DATE, -1);
            prodOnSale.setFinalDate(cal.getTime());
            this.productOnSaleRepository.save(prodOnSale);
        }
*/
        ProductOnSale newProductOnSale = new ProductOnSale(product, provider, price, initialDate);
        //product.getProductOnSale().add(newProductOnSale);
        this.productOnSaleRepository.save(newProductOnSale);

        return newProductOnSale;
    }

    /**
     * @param providerId del proveedor
     * @param productId del producto
     * @return ProductOnSale
     */
    private ProductOnSale findLastById(String providerId, String productId) {
        ProductOnSale productOnSale = new ProductOnSale();

        try {
            SearchRequest searchRequest = new SearchRequest("products_on_sale");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            QueryBuilder query = QueryBuilders.boolQuery()
                    .must(QueryBuilders.termQuery("provider.id.keyword", providerId))
                    .must(QueryBuilders.termQuery("product.id.keyword", productId))
                    .filter(QueryBuilders.existsQuery("finalDate"));
            searchSourceBuilder.query(query);
            searchRequest.source(searchSourceBuilder);

            SearchResponse res1 = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = res1.getHits().getHits();

            for(SearchHit hit : hits){
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                productOnSale = MapProductOnSale(sourceAsMap);
            }
        }
        catch (Exception e) {}

        return productOnSale;
    }

    /**
     * @param day
     * @return una lista los productOnSale vendidos en un <code>day</code>
     */
    public List<ProductOnSale> getSoldProductsOn(Date day) {
        List<ProductOnSale> soldProduct = new ArrayList<ProductOnSale>();

        try {
            SearchRequest searchRequest = new SearchRequest("purchases");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.aggregation(AggregationBuilders.terms("productsOnSale").field("productsOnSale.id.keyword"));
            MatchPhraseQueryBuilder matchPhraseQueryBuilder = new MatchPhraseQueryBuilder("dateOfPurchase", day);
            searchSourceBuilder.query(matchPhraseQueryBuilder);
            searchRequest.source(searchSourceBuilder);

            SearchResponse res1 = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = res1.getHits().getHits();

            for(SearchHit hit : hits){
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                Purchase purchase = MapPurchase(sourceAsMap);

                var pos = soldProduct.stream().filter(sp -> purchase.getProductOnSale().getId().equals(sp.getId())).findAny().orElse(null);
                if (pos == null) {
                    soldProduct.add(purchase.getProductOnSale());
                }

            }
        }
        catch (Exception e) {}

        return soldProduct;
    }
}
