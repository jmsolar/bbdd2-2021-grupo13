package com.grupo13.elasticSearch.repositories;

import com.grupo13.elasticSearch.models.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<User, Long> {
}
