package com.credable.lms.services;

import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
public class KycService {

    private final WebServiceTemplate webServiceTemplate;

    public KycService(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public String getCustomerKyc(String customerNumber) {
        String wsdlUrl = "https://kycapitest.credable.io/service/customerWsdl.wsdl";
        return "Fetched KYC data for customer: " + customerNumber + " from " + wsdlUrl;
    }
}