package com.challenge.test.api.controller;

import com.challenge.test.domain.model.DTO.DepositDTO;
import com.challenge.test.domain.model.DTO.TransferDTO;
import com.challenge.test.domain.model.Extract;
import com.challenge.test.domain.service.impl.WalletServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/wallet")
public class WalletUserController {

    @Autowired
    private WalletServiceImpl walletService;

    @PostMapping("/deposit")
    public ResponseEntity<Extract> deposit(@RequestBody DepositDTO depositDTO) {
        Extract extract = walletService.deposit(depositDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(extract);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Extract> transfer(@RequestBody TransferDTO transferDTO) {
        Extract extract = walletService.transfer(transferDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(extract);
    }
}
