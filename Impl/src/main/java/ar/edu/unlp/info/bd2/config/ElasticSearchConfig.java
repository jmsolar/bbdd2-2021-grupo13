package ar.edu.unlp.info.bd2.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.http.HttpHost;
import org.elasticsearch.client.*;
import org.elasticsearch.index.mapper.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ElasticSearchConfig {
	private static RestHighLevelClient client;
	
	private static RestHighLevelClient getRestHighLevelClient() {
		return client;
	}

	private static void setRestHighLevelClient(RestHighLevelClient restHighLevelClient) {
		ElasticSearchConfig.client = restHighLevelClient;
	}
	
	@Bean
	public static synchronized RestHighLevelClient openConnection() {
		if(getRestHighLevelClient() == null) {
	        setRestHighLevelClient(new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http"))));
	    }
	 
	    return getRestHighLevelClient();
	}
	
	@Bean
	public static synchronized void closeConnection() throws IOException {
		getRestHighLevelClient().close();
	    setRestHighLevelClient(null);
	}
}