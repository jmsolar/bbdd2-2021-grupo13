package com.grupo13.elasticSearch.repositories;

import com.grupo13.elasticSearch.models.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
