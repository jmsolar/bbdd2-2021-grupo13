package com.grupo13.elasticSearch.services;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.CreditCardPayment;
import com.grupo13.elasticSearch.repositories.CreditCardPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CreditCardPaymentService {
    private CreditCardPaymentRepository creditCardPaymentRepository;

    @Autowired
    public CreditCardPaymentService(CreditCardPaymentRepository creditCardPaymentRepository) {
        this.creditCardPaymentRepository = creditCardPaymentRepository;
    }

    /**
     * @param name nombre del pago con tarjeta
     * @return
     */
    public Optional<CreditCardPayment> findByName(String name) {
        CreditCardPayment creditCardPayment = this.creditCardPaymentRepository.findByName(name);
        Optional<CreditCardPayment> opt = Optional.ofNullable(creditCardPayment);
        return opt;
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
    public CreditCardPayment create(String name, String brand, Long number, Date expiry, Integer cvv, String owner) throws ElasticSearchException {
        ElasticSearchException ex = new ElasticSearchException();

        if (this.findByName(name).isPresent()) ex.constraintViolation();

        CreditCardPayment newCreditCardPayment = new CreditCardPayment(name, brand, number, expiry, cvv,owner);
        this.creditCardPaymentRepository.save(newCreditCardPayment);

        return newCreditCardPayment;
    }
}
