package com.policymanagemet.repository;

import com.policymanagemet.model.Claim;
import com.policymanagemet.model.Policy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimsRepository extends CrudRepository<Claim, Integer> {

    List<Claim> findByPolicyNumber(String policyNumber);
}
