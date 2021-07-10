package ar.edu.unlp.info.bd2.config;

import ar.edu.unlp.info.bd2.repositories.MLRepository;
import ar.edu.unlp.info.bd2.repositories.elasticSearch.ESRepository;
import ar.edu.unlp.info.bd2.services.ESService;
import ar.edu.unlp.info.bd2.services.MLService;
import ar.edu.unlp.info.bd2.services.MLStatisticsService;
import ar.edu.unlp.info.bd2.services.SpringDataMLService;
import ar.edu.unlp.info.bd2.services.impl.ESServiceImpl;
import ar.edu.unlp.info.bd2.services.impl.MLServiceImpl;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean
	public MLService createService() { 
		MLRepository repository = this.createRepository();
		return new MLServiceImpl(repository); 
	}

    @Bean
    public MLRepository createRepository() {
        return new MLRepository();
    }
    
    @Bean
    public ESService createESService() {
    	ESRepository repositoryES = this.createESRepository();
    	return new ESServiceImpl(repositoryES);
    }
    
    @Bean
    public ESRepository createESRepository(){
        RestHighLevelClient client = ElasticSearchConfig.openConnection();
        return new ESRepository(client);
    }
}
