package com.policymanagemet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyDto {

    private String policyHolderName;
    private String policyType;
      private String email;
}
