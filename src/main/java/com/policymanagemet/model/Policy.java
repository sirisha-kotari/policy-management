package com.policymanagemet.model;

import com.policymanagemet.dto.PolicyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "policy")
public class Policy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy_seq_generator")
    @SequenceGenerator(name = "policy_seq_generator", sequenceName = "policy_seq", allocationSize = 1, initialValue = 1000)
    private int policyNumber;
    @Column(name="Policy_Holder_Name")
    private String policyHolderName;
    @Column(name="Policy_Type")
    private String policyType;
    @Column(name="Email")
    private String email;
    public static Policy mapToPolicy(PolicyDto policyDto){
        Policy policy = new Policy();
        policy.setPolicyHolderName(policyDto.getPolicyHolderName());
        policy.setPolicyType(policyDto.getPolicyType());
        policy.setEmail(policyDto.getEmail());

        return policy;
    }
}


