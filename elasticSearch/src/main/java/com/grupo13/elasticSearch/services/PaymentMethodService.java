package com.grupo13.elasticSearch.services;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.PaymentMethod;
import com.grupo13.elasticSearch.repositories.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.Optional;

@Service
public class PaymentMethodService {
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    /**
     * @param name nombre del método de pago
     * @param promisedAmount monto con el que va a pagar el cliente
     * @throws ElasticSearchException
     */
    public PaymentMethod create(String name, Float promisedAmount) throws ElasticSearchException {
        ElasticSearchException ex = new ElasticSearchException();

        if (this.findByName(name).isPresent()) ex.constraintViolation();

        PaymentMethod paymentMethod = new PaymentMethod(name, promisedAmount, null, null, null, null, null);
        this.paymentMethodRepository.save(paymentMethod);

        return paymentMethod;
    }

    /**
     * @param name nombre del método de pago
     * @param brand marca de la tarjeta
     * @param number número de la tarjeta
     * @param expiry fecha de expiración de la tarjeta
     * @param cvv código de validación de la tarjeta
     * @param owner nombre del titular de la tarjeta
     * @return el método de pago creado
     * @throws ElasticSearchException
     */
    public PaymentMethod create(String name, String brand, Long number, Date expiry, Integer cvv, String owner) throws ElasticSearchException {
        ElasticSearchException ex = new ElasticSearchException();

        if (this.findByName(name).isPresent()) ex.constraintViolation();

        PaymentMethod paymentMethod = new PaymentMethod(name, null, brand, number, expiry, cvv, owner);
        this.paymentMethodRepository.save(paymentMethod);

        return paymentMethod;
    }

    /**
     * @param name nombre del producto a buscar
     * @return
     */
    @GetMapping("/{name}")
    public Optional<PaymentMethod> findByName(String name) {
        PaymentMethod paymentMethod = this.paymentMethodRepository.findByName(name);
        Optional<PaymentMethod> opt = Optional.ofNullable(paymentMethod);
        return opt;
    }
}
