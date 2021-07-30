package com.grupo13.elasticSearch.services;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.OnDeliveryPayment;
import com.grupo13.elasticSearch.repositories.OnDeliveryPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OnDeliveryPaymentService {
    private OnDeliveryPaymentRepository onDeliveryPaymentRepository;

    @Autowired
    public OnDeliveryPaymentService(OnDeliveryPaymentRepository onDeliveryPaymentRepository) {
        this.onDeliveryPaymentRepository = onDeliveryPaymentRepository;
    }

    /**
     * @param name	Nombre del Pago en Delivery a buscar
     * @return
     */
    public Optional<OnDeliveryPayment> findByName(String name) {
        return this.onDeliveryPaymentRepository.findByName(name);
    }

    /**
	 * @param name nombre del método de pago
	 * @param promisedAmount monto con el que va a pagar el cliente
	 * @return el método de pago creado
	 * @throws ElasticSearchException
	 */
    public OnDeliveryPayment create(String name, Float promisedAmount) throws ElasticSearchException {
        ElasticSearchException ex = new ElasticSearchException();

        if (this.findByName(name).isPresent()) ex.constraintViolation();

        OnDeliveryPayment newOnDeliveryPayment = new OnDeliveryPayment(name, promisedAmount);
        this.onDeliveryPaymentRepository.save(newOnDeliveryPayment);

        return newOnDeliveryPayment;
    }
}
