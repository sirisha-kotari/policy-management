package com.policymanagemet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CLAIM")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Claim {

    @Id
    @Column(name = "CLAIM_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy_seq_generator")
    @SequenceGenerator(name = "policy_seq_generator", sequenceName = "policy_seq", allocationSize = 1, initialValue = 1000)
    private int claimID;

    @Column(name = "POLICY_NUM")
    private String policyNumber;

    @Column(name = "INSURED_NAME")
    private String insuredName;

    @Column(name = "CLAIM_REASON")
    private String claimReason;

    @Column(name = "CLAIM_AMOUNT")
    private Double claimAmount;

    @Column(name = "CLAIM_STATUS")
    private String status;


}
