package com.grupo13.elasticSearch.services;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.*;
import com.grupo13.elasticSearch.repositories.PurchaseRepository;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

import static com.grupo13.elasticSearch.utils.Mapper.MapPurchase;

@Service
public class PurchaseService {
    private PurchaseRepository purchaseRepository;

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    /**
     * @param id el id de la compra
     * @return Purchase
     */
    public Optional<Purchase> findById(String id) {
        return this.purchaseRepository.findById(id);
    }

    /**
     * @param productOnSale producto que se compra
     * @param quantity cantidad de producto que compra
     * @param client usuario que realiza la compra
     * @param deliveryMethod método de delivery
     * @param paymentMethod método de pago
     * @param address dirección en la cual se debe entregar el pedido
     * @param coordX coordenada X de la dirección de entrega
     * @param coordY coordeada Y de la dirección de entrega
     * @param dateOfPurchase fecha de la compra
     * @return la compra creada
     * @throws ElasticSearchException si el método de delivery enviado no se corresponde con el peso de la compra
     */
    public Purchase create(ProductOnSale productOnSale, Integer quantity, User client, DeliveryMethod deliveryMethod,
                                   PaymentMethod paymentMethod, String address, Float coordX, Float coordY, Date dateOfPurchase) throws ElasticSearchException {
        ElasticSearchException ex = new ElasticSearchException();

        Float totalWeight = productOnSale.getProduct().getWeight() * quantity;
        if (totalWeight < deliveryMethod.getStartWeight() || totalWeight > deliveryMethod.getEndWeight()) ex.deliveryMethodInvalid();

        Purchase newPurchase = new Purchase(productOnSale, quantity, client, deliveryMethod, paymentMethod, address, coordX, coordY, dateOfPurchase);
        this.purchaseRepository.save(newPurchase);

        return newPurchase;
    }

    /**
     * @param username
     * @return Una lista con todas las compras realizadas por el usuario con username <code>username</code>
     */
    public List<Purchase> getAllPurchasesMadeByUser(String username) {
        List<Purchase> purchasesOfUser = new ArrayList<Purchase>();

        try {
            SearchRequest searchRequest = new SearchRequest("purchases");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            MatchPhraseQueryBuilder matchPhraseQueryBuilder = new MatchPhraseQueryBuilder("client.email", username);
            searchSourceBuilder.query(matchPhraseQueryBuilder);
            searchRequest.source(searchSourceBuilder);

            SearchResponse res1 = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = res1.getHits().getHits();

            for(SearchHit hit : hits){
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                Purchase purchase = MapPurchase(sourceAsMap);
                purchasesOfUser.add(purchase);
            }
        }
        catch (Exception e) {}

        return purchasesOfUser;
    }

    /**
     * @param startDate
     * @param endDate
     * @return una lista con las compras realizadas entre dos fechas
     */
    public List<Purchase> getPurchasesInPeriod(Date startDate, Date endDate) throws ParseException {
        List<Purchase> purchasesInPeriod = new ArrayList<Purchase>();

        try {
            SearchRequest searchRequest = new SearchRequest("purchases");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            RangeQueryBuilder queryBuilders = QueryBuilders.rangeQuery("dateOfPurchase").from(startDate).to(endDate);
            searchSourceBuilder.query(queryBuilders);
            searchRequest.source(searchSourceBuilder);

            SearchResponse res1 = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = res1.getHits().getHits();

            for(SearchHit hit : hits){
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                Purchase purchase = MapPurchase(sourceAsMap);
                purchasesInPeriod.add(purchase);
            }
        }
        catch (Exception e) {}

        return purchasesInPeriod;
    }

    /**
     * @param amount
     * @return una lista de los usuarios que han gastando más de <code>amount</code> en alguna compra en la plataforma
     */
    public List<User> getUsersSpendingMoreThanInPurchase(Float amount) {
        List<User> usersThatSpendMore = new ArrayList<User>();

        try {
            SearchRequest searchRequest = new SearchRequest("purchases");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            RangeQueryBuilder queryBuilders = QueryBuilders.rangeQuery("amount").gte(amount);
            searchSourceBuilder.query(queryBuilders);
            searchRequest.source(searchSourceBuilder);

            SearchResponse res1 = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = res1.getHits().getHits();

            for(SearchHit hit : hits){
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                Purchase purchase = MapPurchase(sourceAsMap);

                usersThatSpendMore.add(purchase.getClient());
            }
        }
        catch (Exception e) {}

        return usersThatSpendMore;
    }

    /**
     * @param cuit
     * @return una lista de las compras realizadas por el proveedor con cuit <code>cuit</code>
     */
    public List<Purchase> getPurchasesForProvider(String cuit) {
        var cuitValue = Long.parseLong(cuit);
        List<Purchase> purchasesForProvider = new ArrayList<Purchase>();

        try {
            SearchRequest searchRequest = new SearchRequest("purchases");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            MatchPhraseQueryBuilder matchPhraseQueryBuilder = new MatchPhraseQueryBuilder("productOnSale.provider.cuit", cuitValue);
            searchSourceBuilder.query(matchPhraseQueryBuilder);
            searchRequest.source(searchSourceBuilder);

            SearchResponse res1 = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = res1.getHits().getHits();

            for(SearchHit hit : hits){
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                Purchase purchase = MapPurchase(sourceAsMap);
                purchasesForProvider.add(purchase);
            }
        }
        catch (Exception e) {}

        return purchasesForProvider;
    }
}
