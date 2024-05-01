package com.policymanagemet.controller;

import com.policymanagemet.dto.PolicyDto;
import com.policymanagemet.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/policy")
public class PolicyController {
    @Autowired
    private PolicyService policyService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody PolicyDto policyDto) {

        policyService.createPolicy(policyDto);
        return new ResponseEntity<>("Policy created successfully!...", HttpStatus.OK);
    }
}
