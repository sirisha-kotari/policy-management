package com.policymanagemet.controller;

import com.policymanagemet.dto.PolicyDto;
import com.policymanagemet.service.PolicyService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/policy")
public class PolicyController {
    @Autowired
    private PolicyService policyService;


    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @GetMapping("/import")
    public void loadDataToDB() throws Exception{

        JobParameters jobParams = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();

        jobLauncher.run(job, jobParams);
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody PolicyDto policyDto) {

        policyService.createPolicy(policyDto);
        return new ResponseEntity<>("Policy created successfully!...", HttpStatus.OK);
    }

    @PatchMapping("/update/{policyNumber}")
    private ResponseEntity<String> update(@PathVariable("policyNumber") int policyNumber, @RequestBody PolicyDto newPolicyDto) {
        try {
            policyService.updatePolicy(policyNumber, newPolicyDto);
            ResponseEntity<String> responseEntity = new ResponseEntity<>("Policy Updated successfully", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            ResponseEntity<String> responseEntity = new ResponseEntity<>("Policy failed to Update ", HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
    }
}