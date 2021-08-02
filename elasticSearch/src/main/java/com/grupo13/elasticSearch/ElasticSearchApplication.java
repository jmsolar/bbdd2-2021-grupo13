package com.grupo13.elasticSearch;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.utils.DBInitializer;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class ElasticSearchApplication {

	public static void main(String[] args) throws Exception, ElasticSearchException {
		SpringApplication.run(ElasticSearchApplication.class, args);
	}

	@Autowired
	public boolean resetIndexes(RestHighLevelClient restHighLevelClient) throws ElasticsearchException {
		/*
		 * Mejorar proceso de eliminacion de indices
		 * */
		boolean status = false;

		try {
			/*
			 * PASO 1: Elimino los indices si existieran
			 * */
			DeleteIndexRequest userIndex = new DeleteIndexRequest("bd2");
			restHighLevelClient.indices().delete(userIndex, RequestOptions.DEFAULT);

			/*
			 * PASO 2: Genero los indices
			 * */
			CreateIndexRequest newUserIndex = new CreateIndexRequest("bd2");

			newUserIndex.settings(
					Settings.builder().put("index.number_of_shards", 1)
							.put("index.number_of_replicas", 0));

			restHighLevelClient.indices().create(newUserIndex, RequestOptions.DEFAULT);
			status = true;
		}
		catch (ElasticsearchException | IOException exception) {
			status = false;
		}

		return status;
	}

	@Autowired
	private DBInitializer initializer;

	@Autowired
	public void prepareDB() throws Exception, ElasticSearchException {
		this.initializer.prepareDB();
	}

}