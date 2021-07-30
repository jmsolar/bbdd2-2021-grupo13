package com.grupo13.elasticSearch.services;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.*;
import com.grupo13.elasticSearch.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PurchaseService {
    private PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    /**
     * @param id el id de la compra
     * @return Provider
     */
    public Optional<Purchase> findById(Long id) {
        return this.purchaseRepository.findById(id);
    }

    /**
     * @param productOnSale producto que se compra
     * @param quantity cantidad de producto que compra
     * @param client usuario que realiza la compra
     * @param deliveryMethod método de delivery
     * @param paymentMethod método de pago
     * @param address dirección en la cual se debe entregar el pedido
     * @param coordX coordenada X de la dirección de entrega
     * @param coordY coordeada Y de la dirección de entrega
     * @param dateOfPurchase fecha de la compra
     * @return la compra creada
     * @throws ElasticSearchException si el método de delivery enviado no se corresponde con el peso de la compra
     */
    public Purchase create(ProductOnSale productOnSale, Integer quantity, User client, DeliveryMethod deliveryMethod,
                                   PaymentMethod paymentMethod, String address, Float coordX, Float coordY, Date dateOfPurchase) throws ElasticSearchException {
        ElasticSearchException ex = new ElasticSearchException();

        Float totalWeight = productOnSale.getProduct().getWeight() * quantity;
        if (totalWeight < deliveryMethod.getStartWeight() || totalWeight > deliveryMethod.getEndWeight()) ex.deliveryMethodInvalid();

        Purchase newPurchase = new Purchase(productOnSale, quantity, client, deliveryMethod, paymentMethod, address, coordX, coordY, dateOfPurchase);
        this.purchaseRepository.save(newPurchase);

        return newPurchase;
    }
}
