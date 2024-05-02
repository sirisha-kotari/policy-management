package com.policymanagemet.service;

import com.policymanagemet.dto.PolicyDto;
import com.policymanagemet.model.Policy;
import com.policymanagemet.repository.PolicyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
public class PolicyService {
    @Autowired
    private PolicyRepository policyRepository;
    public Policy createPolicy(PolicyDto policyDto ) {

       Policy policy = Policy.mapToPolicy(policyDto );

        Policy newPolicy = policyRepository.save(policy);


        return newPolicy;
    }

    public Policy updatePolicy(int policyNumber, PolicyDto newPolicyDto){
        log.info("Trying to update PolicyNumber {}" , policyNumber);
        Optional<Policy> oldPolicyData =policyRepository.findById(policyNumber);
        if (oldPolicyData.isPresent()) {
            Policy updatedPolicy= oldPolicyData.get();
            updatedPolicy.setPolicyHolderName(newPolicyDto.getPolicyHolderName());

              Policy newPolicy = policyRepository.save(updatedPolicy);
            log.info("Policy Number {} - updated successfully" , policyNumber);
            return newPolicy ;
        }
        throw new RuntimeException("Policy not found");
    }

}
