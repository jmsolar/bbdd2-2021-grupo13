package com.grupo13.elasticSearch.repositories;

import com.grupo13.elasticSearch.models.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface UserRepository extends ElasticsearchRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
