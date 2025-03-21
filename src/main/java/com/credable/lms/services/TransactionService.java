package com.credable.lms.services;

import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
public class TransactionService {

    private final WebServiceTemplate webServiceTemplate;

    public TransactionService(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public String getTransactionData(String customerNumber) {
        String wsdlUrl = "https://trxapitest.credable.io/service/transactionWsdl.wsdl";
        return "Fetched Transaction data for customer: " + customerNumber + " from " + wsdlUrl;
    }
}