package com.challenge.test.domain.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferDTO extends DepositDTO {
    @JsonProperty("cpf_cnpj_receiver_user")
    private String cpfCnpjReceiverUser;

    @JsonProperty("cpf_cnpj_depositor_user")
    private String cpfCnpjDepositorUser;
}
