package com.policymanagemet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.policymanagemet.model.Claim;
import com.policymanagemet.repository.ClaimsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class ClaimsListener {

    private final ClaimsValidator claimsValidator;

    private final ClaimsRepository claimsRepository;

    ObjectMapper mapper = new ObjectMapper();

    private final String SUCCESS = "SUCCESS";
    private final String FAIL = "FAIL";

    @KafkaListener(topics = {"WHOLE_LIFE_CLAIMS"}, groupId = "whole_life-consumer-group")
    public void consume(String value) throws JsonProcessingException {
        log.info("Claim payload: " + value);
        Claim claim = mapper.readValue(value, Claim.class);
        String status = SUCCESS;
        try {
            claimsValidator.validate(claim);
        } catch (InvalidClaimException ex) {
            log.error("invalid claims exception occurred {}", ex.getMessage());
            status = FAIL;
        }
        claim.setStatus(status);
        claimsRepository.save(claim);
        log.info("Claim saved in db : " + claim);
    }

    @KafkaListener(topics = {"WHOLE_LIFE_CLAIMS"}, groupId = "whole_life-consumer-group")
    public void consumerGroup1(String value) {
        log.info("consumerGroup1: " + value);
    }
}
