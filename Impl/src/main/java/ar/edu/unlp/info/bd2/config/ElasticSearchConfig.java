package ar.edu.unlp.info.bd2.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.http.HttpHost;
import org.elasticsearch.client.*;
import org.elasticsearch.index.mapper.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

public class ElasticSearchConfig {
	private static final String HOST = "localhost";
	private static final int PORT_ONE = 9200;
	private static final int PORT_TWO = 9201;
	private static final String SCHEME = "http";
	private static RestHighLevelClient restHighLevelClient;
	private static ObjectMapper objectMapper;
	
	private static synchronized RestHighLevelClient makeConnection() {
		if(restHighLevelClient == null) {
	        restHighLevelClient = new RestHighLevelClient(
	                RestClient.builder(
	                        new HttpHost(HOST, PORT_ONE, SCHEME),
	                        new HttpHost(HOST, PORT_TWO, SCHEME)));
	    }
	 
	    return restHighLevelClient;
	}
	
	private static synchronized void closeConnection() throws IOException {
	    restHighLevelClient.close();
	    restHighLevelClient = null;
	}
	
	/*
	 * @Bean public LocalSessionFactoryBean sessionFactory() {
	 * LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	 * sessionFactory.setDataSource(dataSource()); sessionFactory.setPackagesToScan(
	 * new String[] {"ar.edu.unlp.info.bd2.models"});
	 * sessionFactory.setHibernateProperties(hibernateProperties());
	 * 
	 * return sessionFactory; }
	 */
}