package com.challenge.test.domain.service.impl;

import com.challenge.test.domain.exception.EntityNotFoundException;
import com.challenge.test.domain.exception.TransferBadRequestException;
import com.challenge.test.domain.model.DTO.DepositDTO;
import com.challenge.test.domain.model.DTO.TransferDTO;
import com.challenge.test.domain.model.Extract;
import com.challenge.test.domain.model.User;
import com.challenge.test.domain.model.enums.TransactionType;
import com.challenge.test.domain.repository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;

@Service
public class WalletServiceImpl {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ExtractRepository extractRepository;

    @Value("${mock-url}")
    private String url;

    @Autowired
    RestTemplate restTemplate;

    public Extract deposit(DepositDTO depositDTO){
        User userReceive = userService.findUserByAdAndAcc(depositDTO.getAgToDeposit(), depositDTO.getAccToDeposit())
                .orElseThrow(() -> new EntityNotFoundException());
        userReceive.setBalance(userReceive.getBalance().add(depositDTO.getValue()));
        return extractRepository.save(createExtract(TransactionType.DEPOSIT, depositDTO.getValue(), userReceive, userReceive));
    }

    public Extract transfer(TransferDTO transferDTO){
        User userDepositor = userService.findUserByCpfCNPJ(transferDTO.getCpfCnpjDepositorUser())
                .orElseThrow(() -> new EntityNotFoundException());
        User userReceive = userService.findUserByAdAndAcc(transferDTO.getAgToDeposit(), transferDTO.getAccToDeposit())
                .orElseThrow(() -> new EntityNotFoundException());

        if(!restTemplate.getForEntity(url, String.class).getStatusCode().is2xxSuccessful())
            throw new TransferBadRequestException();

        userReceive.setBalance(userReceive.getBalance().add(transferDTO.getValue()));
        return extractRepository.save(createExtract(TransactionType.TRANSFER, transferDTO.getValue(), userReceive, userDepositor));
    }

    private Extract createExtract(TransactionType transactionType, BigDecimal value, User userReceive, User userDepositor){
        Extract extract = new Extract();
        extract.setTransactionType(transactionType);
        extract.setValue(value);
        extract.setUserReceiverId(userReceive);
        extract.setUserDepositorId(userDepositor);
        userReceive.setExtractList(Collections.singletonList(extract));
        return extract;
    }
}