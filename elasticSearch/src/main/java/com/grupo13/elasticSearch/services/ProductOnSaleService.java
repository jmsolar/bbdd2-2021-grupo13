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
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.PipelineAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.BucketsAggregator;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

        /*
        ProductOnSale prodOnSale = this.productOnSaleRepository.findLastById(provider.getId(), product.getId());
        if (prodOnSale != null && prodOnSale.getInitialDate().after(initialDate)) ex.priceValidity();

        if (prodOnSale != null) {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(initialDate);
            cal.add(Calendar.DATE, -1);
            prodOnSale.setFinalDate(cal.getTime());
            this.productOnSaleRepository.save(prodOnSale);
        }
*/
        ProductOnSale newProductOnSale = new ProductOnSale(product, provider, price, initialDate);
        //product.getProductsOnSale().add(newProductOnSale);
        this.productOnSaleRepository.save(newProductOnSale);

        return newProductOnSale;
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
            MatchPhraseQueryBuilder matchPhraseQueryBuilder = new MatchPhraseQueryBuilder("dateOfPurchase", day);
            searchSourceBuilder.query(matchPhraseQueryBuilder).aggregations();
            searchRequest.source(searchSourceBuilder);

            SearchResponse res1 = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = res1.getHits().getHits();

            for(SearchHit hit : hits){
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                Purchase purchase = MapPurchase(sourceAsMap);
                soldProduct.add(purchase.getProductOnSale());
            }
        }
        catch (Exception e) {}

        return soldProduct;
    }
}
