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

	private boolean deleteIndexes(RestHighLevelClient restHighLevelClient) {
		try {
			DeleteIndexRequest categoryIndex = new DeleteIndexRequest("categories");
			DeleteIndexRequest creditCardPaymentIndex = new DeleteIndexRequest("credit_card_payments");
			DeleteIndexRequest deliveryMethodIndex = new DeleteIndexRequest("delivery_methods");
			DeleteIndexRequest onDeliveryPaymentIndex = new DeleteIndexRequest("on_delivery_payments");
			DeleteIndexRequest productOnSaleIndex = new DeleteIndexRequest("products_on_sale");
			DeleteIndexRequest productIndex = new DeleteIndexRequest("products");
			DeleteIndexRequest providerIndex = new DeleteIndexRequest("providers");
			DeleteIndexRequest purchaseIndex = new DeleteIndexRequest("purchases");
			DeleteIndexRequest userIndex = new DeleteIndexRequest("users");

			restHighLevelClient.indices().delete(categoryIndex, RequestOptions.DEFAULT);
			restHighLevelClient.indices().delete(creditCardPaymentIndex, RequestOptions.DEFAULT);
			restHighLevelClient.indices().delete(deliveryMethodIndex, RequestOptions.DEFAULT);
			restHighLevelClient.indices().delete(onDeliveryPaymentIndex, RequestOptions.DEFAULT);
			restHighLevelClient.indices().delete(productOnSaleIndex, RequestOptions.DEFAULT);
			restHighLevelClient.indices().delete(productIndex, RequestOptions.DEFAULT);
			restHighLevelClient.indices().delete(providerIndex, RequestOptions.DEFAULT);
			restHighLevelClient.indices().delete(purchaseIndex, RequestOptions.DEFAULT);
			restHighLevelClient.indices().delete(userIndex, RequestOptions.DEFAULT);
			return true;
		}
		catch (ElasticsearchException | IOException exception) {
			return false;
		}
	}

	private boolean createIndexes(RestHighLevelClient restHighLevelClient) {
		try {
			CreateIndexRequest newCategoryIndex = new CreateIndexRequest("categories");
			CreateIndexRequest newCreditCardPaymentIndex = new CreateIndexRequest("credit_card_payments");
			CreateIndexRequest newDeliveryMethodIndex = new CreateIndexRequest("delivery_methods");
			CreateIndexRequest newOnDeliveryPaymentIndex = new CreateIndexRequest("on_delivery_payments");
			CreateIndexRequest newProductOnSaleIndex = new CreateIndexRequest("products_on_sale");
			CreateIndexRequest newProductIndex = new CreateIndexRequest("products");
			CreateIndexRequest newProviderIndex = new CreateIndexRequest("providers");
			CreateIndexRequest newPurchaseIndex = new CreateIndexRequest("purchases");
			CreateIndexRequest newUserIndex = new CreateIndexRequest("users");
			newCategoryIndex.settings(
					Settings.builder().put("index.number_of_shards", 1)
							.put("index.number_of_replicas", 0));

			restHighLevelClient.indices().create(newCategoryIndex, RequestOptions.DEFAULT);
			newCreditCardPaymentIndex.settings(
					Settings.builder().put("index.number_of_shards", 1)
							.put("index.number_of_replicas", 0));

			restHighLevelClient.indices().create(newCreditCardPaymentIndex, RequestOptions.DEFAULT);
			newDeliveryMethodIndex.settings(
					Settings.builder().put("index.number_of_shards", 1)
							.put("index.number_of_replicas", 0));

			restHighLevelClient.indices().create(newDeliveryMethodIndex, RequestOptions.DEFAULT);
			newOnDeliveryPaymentIndex.settings(
					Settings.builder().put("index.number_of_shards", 1)
							.put("index.number_of_replicas", 0));

			restHighLevelClient.indices().create(newOnDeliveryPaymentIndex, RequestOptions.DEFAULT);
			newProductOnSaleIndex.settings(
					Settings.builder().put("index.number_of_shards", 1)
							.put("index.number_of_replicas", 0));

			restHighLevelClient.indices().create(newProductOnSaleIndex, RequestOptions.DEFAULT);
			newProductIndex.settings(
					Settings.builder().put("index.number_of_shards", 1)
							.put("index.number_of_replicas", 0));

			restHighLevelClient.indices().create(newProductIndex, RequestOptions.DEFAULT);
			newProviderIndex.settings(
					Settings.builder().put("index.number_of_shards", 1)
							.put("index.number_of_replicas", 0));

			restHighLevelClient.indices().create(newProviderIndex, RequestOptions.DEFAULT);
			newPurchaseIndex.settings(
					Settings.builder().put("index.number_of_shards", 1)
							.put("index.number_of_replicas", 0));

			restHighLevelClient.indices().create(newPurchaseIndex, RequestOptions.DEFAULT);
			newUserIndex.settings(
					Settings.builder().put("index.number_of_shards", 1)
							.put("index.number_of_replicas", 0));

			restHighLevelClient.indices().create(newUserIndex, RequestOptions.DEFAULT);

			return true;
		}
		catch (ElasticsearchException | IOException exception) {
			return false;
		}
	}

	@Autowired
	public boolean resetIndexes(RestHighLevelClient restHighLevelClient) throws ElasticsearchException {
		/*
		 * Mejorar proceso de eliminacion de indices
		 * */
		boolean status = false;
		/*
		* PASO 1: Elimino los indices si existieran
		* */
		status = this.deleteIndexes(restHighLevelClient);

		/*
		* PASO 2: Genero los indices
		* */
		status = this.createIndexes(restHighLevelClient);

		return status;
	}

	@Autowired
	private DBInitializer initializer;

	@Autowired
	public void prepareDB() throws Exception, ElasticSearchException {
		this.initializer.prepareDB();
	}

}