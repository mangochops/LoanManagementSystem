package com.credable.lms.controllers;

import com.credable.lms.services.KycService;
import com.credable.lms.services.LoanService;
import com.credable.lms.services.TransactionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/lms")
@Tag(name = "Loan Management Controller", description = "APIs for loan management")
public class LoanManagementController {

    private final RestTemplate restTemplate;
    private final LoanService loanService;
    private final KycService kycService;
    private final TransactionService transactionService;
    private final Map<String, String> loanStatus = new HashMap<>();

    public LoanManagementController(RestTemplate restTemplate, LoanService loanService, KycService kycService, TransactionService transactionService) {
        this.restTemplate = restTemplate;
        this.loanService = loanService;
        this.kycService = kycService;
        this.transactionService = transactionService;
    }

    @PostMapping("/subscribe")
    public String subscribe(@RequestParam String customerNumber) {
        String kycData = kycService.getCustomerKyc(customerNumber);
        loanStatus.put(customerNumber, "Subscribed");
        return "Subscription successful for customer: " + customerNumber + "\nKYC Data: " + kycData;
    }

    @PostMapping("/loan-request")
    public String requestLoan(@RequestParam String customerNumber, @RequestParam double amount) {
        if (loanStatus.containsKey(customerNumber) && loanStatus.get(customerNumber).equals("Subscribed")) {
            boolean eligible = loanService.checkEligibility(customerNumber);
            if (eligible) {
                String transactionData = transactionService.getTransactionData(customerNumber);
                loanStatus.put(customerNumber, "Processing");
                return "Loan request received for customer: " + customerNumber + ", Amount: " + amount + "\nTransaction Data: " + transactionData;
            } else {
                return "Loan request denied due to low credit score.";
            }
        }
        return "Customer not subscribed or already has a pending loan.";
    }

    @GetMapping("/loan-status")
    public String getLoanStatus(@RequestParam String customerNumber) {
        return loanStatus.getOrDefault(customerNumber, "No record found");
    }
}