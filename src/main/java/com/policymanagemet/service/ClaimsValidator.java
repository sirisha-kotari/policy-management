package com.policymanagemet.service;

import com.policymanagemet.model.Claim;
import org.springframework.stereotype.Component;

@Component
public class ClaimsValidator {
    private final static String VALID_REASON = "Injury";

    public boolean validate(Claim claim) {

        if (!claim.getClaimReason().equals(VALID_REASON)) {
            throw new InvalidClaimException("Claim reason is invalid");
        }
      return true;
    }
}
