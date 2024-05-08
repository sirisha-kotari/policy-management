package com.policymanagemet.service;

public class InvalidClaimException extends RuntimeException {
    public InvalidClaimException(String msg) {
        super(msg);
    }
}
