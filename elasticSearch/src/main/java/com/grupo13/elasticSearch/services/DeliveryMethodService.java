package com.grupo13.elasticSearch.services;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.DeliveryMethod;
import com.grupo13.elasticSearch.repositories.DeliveryMethodRepository;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryMethodService {
    private DeliveryMethodRepository deliveryMethodRepository;

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    public DeliveryMethodService(DeliveryMethodRepository deliveryMethodRepository) {
        this.deliveryMethodRepository = deliveryMethodRepository;
    }

    /**
     * @param name nombre del método de delivery a buscar
     * @return
     */
    public Optional<DeliveryMethod> findByName(String name) {
        DeliveryMethod deliveryMethod = this.deliveryMethodRepository.findByName(name);
        Optional<DeliveryMethod> opt = Optional.ofNullable(deliveryMethod);
        return opt;
    }

    /**
     * @param name nombre del método de delivery
     * @param cost precio del delivery
     * @param startWeight peso mínimo del producto admitido para este costo
     * @param endWeight peso máximo del producto admitido para este costo
     * @return el método de delivery creado
     * @throws ElasticSearchException
     */
    public DeliveryMethod create(String name, Float cost, Float startWeight, Float endWeight) throws ElasticSearchException {
        ElasticSearchException ex = new ElasticSearchException();

        if (this.findByName(name).isPresent()) ex.constraintViolation();

        DeliveryMethod newDeliveryMethod = new DeliveryMethod(name, cost, startWeight, endWeight);
        this.deliveryMethodRepository.save(newDeliveryMethod);

        return newDeliveryMethod;
    }

    /**
     * @return el método de Delivery más utilizado
     */
    public DeliveryMethod getMostUsedDeliveryMethod() {
        DeliveryMethod deliveryMethod = new DeliveryMethod();
        try {
            SearchRequest searchRequest = new SearchRequest("purchases");
            List<BucketOrder> bucketOrderList = new ArrayList<>();
            bucketOrderList.add(BucketOrder.aggregation("_count", false));
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().size(0);
            searchSourceBuilder.aggregation(AggregationBuilders.terms("deliveries").field("deliveryMethod.name.keyword").order(bucketOrderList));
            searchRequest.source(searchSourceBuilder);
            SearchResponse res1 = client.search(searchRequest, RequestOptions.DEFAULT);
            Terms terms = res1.getAggregations().get("deliveries");
            var entry = terms.getBuckets().get(0).getKeyAsString();
            deliveryMethod = this.deliveryMethodRepository.findByName(entry);
        }
        catch (Exception e) {}

        return deliveryMethod;
    }
}
