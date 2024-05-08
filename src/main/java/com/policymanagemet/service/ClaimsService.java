package com.policymanagemet.service;

import com.policymanagemet.model.Claim;
import com.policymanagemet.repository.ClaimsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClaimsService {

    private final ClaimsRepository claimsRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topicName, String message) {
          this.kafkaTemplate.send(topicName, message);
    }

    public List<Claim> findAllClaimsByPolicyNumber(String policyNumber) {
        return claimsRepository.findByPolicyNumber(policyNumber);
    }
}
