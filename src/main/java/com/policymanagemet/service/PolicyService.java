package com.policymanagemet.service;

import com.policymanagemet.dto.PolicyDto;
import com.policymanagemet.model.Policy;
import com.policymanagemet.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyService {
    @Autowired
    private PolicyRepository policyRepository;
    public Policy createPolicy(PolicyDto policyDto ) {

       Policy policy = Policy.mapToPolicy(policyDto );

        Policy newPolicy = policyRepository.save(policy);


        return newPolicy;
    }

}
