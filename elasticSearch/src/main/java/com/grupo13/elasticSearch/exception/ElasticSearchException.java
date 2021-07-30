package com.grupo13.elasticSearch.exception;

public class ElasticSearchException extends Throwable{
    public String message;

    public ElasticSearchException() {}

    public ElasticSearchException(String string) {
        this.message = string;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void categoryNotFound() throws ElasticSearchException {
        throw new ElasticSearchException("Category not found");
    }

    public void productNotFound() throws ElasticSearchException {
        throw new ElasticSearchException("Product doesn't exists");
    }

    public void userNotFound() throws ElasticSearchException {
        throw new ElasticSearchException("User doesn't exists");
    }

    public void providerNoyFound() throws ElasticSearchException {
        throw new ElasticSearchException("Provider doesn't exists");
    }

    public void deliveryMethodNotFound() throws ElasticSearchException {
        throw new ElasticSearchException("Delivery Method doesn't exists");
    }

    public void creditCardNotFound() throws ElasticSearchException {
        throw new ElasticSearchException("Credit Card Payment doesn't exists");
    }

    public void onDeliveryPaymentNotFound() throws ElasticSearchException {
        throw new ElasticSearchException("On Delivery Payment doesn't exists");
    }

    public void priceValidity() throws ElasticSearchException {
        throw new ElasticSearchException("Ya existe un precio para el producto con fecha de inicio de vigencia posterior a la fecha de inicio dada");
    }

    public void deliveryMethodInvalid() throws ElasticSearchException {
        throw new ElasticSearchException("método de delivery no válido");
    }

    public void constraintViolation() throws ElasticSearchException {
        throw new ElasticSearchException("Constraint Violation");
    }
}
