package com.policymanagemet.repository;

import com.policymanagemet.model.Policy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends CrudRepository<Policy, Integer> {

}
