package com.credable.lms.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class LoanService {

    private final RestTemplate restTemplate;

    public LoanService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean checkEligibility(String customerNumber) {
        String url = "https://scoringtest.credable.io/api/v1/scoring/initiateQueryScore/" + customerNumber;
        String token = restTemplate.getForObject(url, String.class);

        int retries = 3;
        while (retries > 0) {
            try {
                Thread.sleep(2000);
                String scoreUrl = "https://scoringtest.credable.io/api/v1/scoring/queryScore/" + token;
                Map<String, Object> response = restTemplate.getForObject(scoreUrl, Map.class);
                int score = (int) response.get("score");
                return score > 500;
            } catch (Exception e) {
                retries--;
            }
        }
        return false;
    }
}