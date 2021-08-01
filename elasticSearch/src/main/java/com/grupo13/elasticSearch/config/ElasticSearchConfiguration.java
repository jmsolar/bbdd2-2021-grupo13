package com.grupo13.elasticSearch.config;

import java.time.Duration;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.grupo13.elasticSearch.repositories")
@ComponentScan(basePackages = { "com.grupo13.elasticSearch" })
public class ElasticSearchConfiguration extends AbstractElasticsearchConfiguration {
	
	@Value("${elasticSearch.url}")
	public String elasticSearchUrl;
	
	@Value("${elasticSearch.connectionTimeout}")
	public Duration connectionTimeOut;
	
	@Value("${elasticSearch.socketTimeout}")
	public Duration socketTimeOut;

	@Override
	@Bean
	public RestHighLevelClient elasticsearchClient() {
		final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
	        .connectedTo(this.elasticSearchUrl)
	        .withConnectTimeout(this.connectionTimeOut)
	        .withSocketTimeout(this.socketTimeOut)
	        .build();

		return RestClients.create(clientConfiguration).rest();
	}
}