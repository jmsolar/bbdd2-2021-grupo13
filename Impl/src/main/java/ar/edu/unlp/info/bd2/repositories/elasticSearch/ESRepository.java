package ar.edu.unlp.info.bd2.repositories.elasticSearch;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;

public class ESRepository {
	private RestHighLevelClient client;

	public RestHighLevelClient getClient() {
		return client;
	}

	public void setClient(RestHighLevelClient client) {
		this.client = client;
	}
	
	@Autowired
	public ESRepository(RestHighLevelClient client) {
		setClient(client);
	}
}