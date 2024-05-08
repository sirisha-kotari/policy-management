package com.policymanagemet.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.policymanagemet.model.Claim;
import com.policymanagemet.service.ClaimsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ClaimsController {

    @Autowired
    private ClaimsService claimsService;

    @Value("${claims.topic}")
    private String claimsTopic;
    ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/claim/submit")
    public ResponseEntity<String> claimsSubmit(@RequestBody Claim claim) throws JsonProcessingException {
        String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(claim);
        log.info("Claim request {}", prettyJson);
        claimsService.sendMessage(claimsTopic, prettyJson);
        return ResponseEntity.status(HttpStatus.OK).body("Claim submitted successfully");
    }

    @GetMapping("/claim/status/{policyNumber}")
    public ResponseEntity<List<Claim>> claimStatus(@PathVariable String policyNumber) throws JsonProcessingException {
        log.info("Checking claim status using policyNumber {}", policyNumber);
        List<Claim> claims = claimsService.findAllClaimsByPolicyNumber(policyNumber);
        return ResponseEntity.status(HttpStatus.OK).body(claims);
    }

}
