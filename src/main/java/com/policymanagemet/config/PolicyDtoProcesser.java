package com.policymanagemet.config;


import com.policymanagemet.dto.PolicyDto;
import com.policymanagemet.model.Policy;
import org.springframework.batch.item.ItemProcessor;



public class PolicyDtoProcesser implements ItemProcessor<PolicyDto, Policy> {

    @Override
    public Policy process(PolicyDto  policyDto) throws Exception {
        return mapToPolicy(policyDto);
    }
    public static Policy mapToPolicy(PolicyDto policyDto){
        Policy policy = new Policy();
        policy.setPolicyHolderName(policyDto.getPolicyHolderName());
        policy.setPolicyType(policyDto.getPolicyType());
        policy.setEmail(policyDto.getEmail());

        return policy;
    }

}