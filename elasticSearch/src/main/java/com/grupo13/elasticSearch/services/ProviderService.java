package com.grupo13.elasticSearch.services;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.Provider;
import com.grupo13.elasticSearch.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProviderService {
    private ProviderRepository providerRepository;

    @Autowired
    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    /**
     *
     * @param cuit cuit del proveedor
     * @return
     */
    public Optional<Provider> findByCuit(Long cuit) {
        Provider provider = this.providerRepository.findByCuit(cuit);
        Optional<Provider> opt = Optional.ofNullable(provider);
        return opt;
    }

    /**
     * @param name nombre del proveedor
     * @param cuit cuil del proveedor
     * @return el proveedor creado
     * @throws ElasticSearchException
     */
    public Provider create(String name, Long cuit) throws ElasticSearchException {
        ElasticSearchException ex = new ElasticSearchException();

        if (this.findByCuit(cuit).isPresent()) ex.constraintViolation();

        Provider newProvider = new Provider(name, cuit);
        this.providerRepository.save(newProvider);

        return newProvider;
    }
}
