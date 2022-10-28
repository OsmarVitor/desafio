package com.challenge.test.domain.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositDTO {

    @NotNull
    @JsonProperty("ag_to_deposit")
    private String agToDeposit;

    @NotNull
    @JsonProperty("acc_to_deposit")
    private String accToDeposit;

    @NotNull
    private BigDecimal value;
}
