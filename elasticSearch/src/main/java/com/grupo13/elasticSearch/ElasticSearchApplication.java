package com.grupo13.elasticSearch;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.utils.DBInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElasticSearchApplication {

	public static void main(String[] args) throws Exception, ElasticSearchException {
		SpringApplication.run(ElasticSearchApplication.class, args);
	}

	@Autowired
	private DBInitializer initializer;

	@Autowired
	public void prepareDB() throws Exception, ElasticSearchException {
		this.initializer.prepareDB();
	}

}