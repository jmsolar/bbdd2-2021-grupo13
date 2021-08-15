package com.grupo13.elasticSearch.services;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.User;
import com.grupo13.elasticSearch.repositories.UserRepository;
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

import java.util.*;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @param email email del usuario
     * @return
     */
    public Optional<User> findByEmail(String email) {
        User user = this.userRepository.findByEmail(email);
        Optional<User> opt = Optional.ofNullable(user);
        return opt;
    }

    /**
     *
     * @param email email del usuario con el cual ingresa al sitio
     * @param fullname nombre y apellido del usuario
     * @param password clave con la que el usuario ingresa al sitio
     * @param dayOfBirth fecha de nacimiento del usuario
     * @return el usuario creado
     * @throws ElasticSearchException
     */
    public User create(String email, String fullname, String password, Date dayOfBirth) throws ElasticSearchException {
        ElasticSearchException ex = new ElasticSearchException();

        if (this.findByEmail(email).isPresent()) ex.constraintViolation();

        User newUser = new User(email, fullname, password, dayOfBirth);
        this.userRepository.save(newUser);

        return newUser;
    }

    /**
     * @param n
     * @return una lista con los <code>n</code> usuarios que más cantidad de compras han realizado
     */
    public List<User> getTopNUsersMorePurchase(int n) {
        List<User> topUsers = new ArrayList<>();

        try {
            SearchRequest searchRequest = new SearchRequest("purchases");
            List<BucketOrder> bucketOrderList = new ArrayList<>();
            bucketOrderList.add(BucketOrder.aggregation("_count", false));
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.aggregation(AggregationBuilders.terms("users").field("client.email.keyword").order(bucketOrderList).size(n));
            searchRequest.source(searchSourceBuilder);

            SearchResponse res1 = client.search(searchRequest, RequestOptions.DEFAULT);
            Terms terms = res1.getAggregations().get("users");

            for(Terms.Bucket term : terms.getBuckets()) {
                String email = term.getKeyAsString();
                User user = this.userRepository.findByEmail(email);
                topUsers.add(user);
            }
        }
        catch (Exception e) {}

        return topUsers;
    }
}
