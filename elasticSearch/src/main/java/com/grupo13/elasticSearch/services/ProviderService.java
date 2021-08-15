package com.grupo13.elasticSearch.services;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.Provider;
import com.grupo13.elasticSearch.repositories.ProviderRepository;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {
    private ProviderRepository providerRepository;

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    /**
     *
     * @param cuit cuit del proveedor
     * @return
     */
    public Optional<Provider> findByCuit(Long cuit) {
        Provider provider = this.providerRepository.findByCuit(cuit);
        Optional<Provider> opt = Optional.ofNullable(provider);
        return opt;
    }

    /**
     * @param name nombre del proveedor
     * @param cuit cuil del proveedor
     * @return el proveedor creado
     * @throws ElasticSearchException
     */
    public Provider create(String name, Long cuit) throws ElasticSearchException {
        ElasticSearchException ex = new ElasticSearchException();

        if (this.findByCuit(cuit).isPresent()) ex.constraintViolation();

        Provider newProvider = new Provider(name, cuit);
        this.providerRepository.save(newProvider);

        return newProvider;
    }

    /**
     * @param n
     * @return una lista con los <code>n</code> proveedores que más productos han vendido
     */
    public List<Provider> getTopNProvidersInPurchases(int n) {
        List<Provider> topProviders = new ArrayList<>();

        try {
            SearchRequest searchRequest = new SearchRequest("purchases");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.aggregation(AggregationBuilders.terms("providers").field("productOnSale.provider.cuit").size(n));
            searchRequest.source(searchSourceBuilder.sort(new FieldSortBuilder("quantity").order(SortOrder.DESC)));
            searchRequest.source(searchSourceBuilder);

            SearchResponse res1 = client.search(searchRequest, RequestOptions.DEFAULT);
            Terms terms = res1.getAggregations().get("providers");

            for(Terms.Bucket term : terms.getBuckets()) {
                String cuit = term.getKeyAsString();
                Provider provider = this.providerRepository.findByCuit(Long.parseLong(cuit));
                topProviders.add(provider);
            }
        }
        catch (Exception e) {}

        return topProviders;
    }
}
