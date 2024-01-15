package com.example.sqch14ex1.controller;

import com.example.sqch14ex1.dto.TransferRequest;
import com.example.sqch14ex1.model.Account;
import com.example.sqch14ex1.service.TransferService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    private final TransferService transferService;

    public AccountController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public void transferMoney(@RequestBody TransferRequest request) {
        transferService.transferMoney(
                request.getSenderAccountId(),
                request.getReceiverAccountId(),
                request.getAmount()
        );
    }

    @GetMapping("/accounts")
    public Iterable<Account> getAllAccounts(@RequestParam(required = false) String name) {
        if (name == null) {
            return transferService.getAllAcounts();
        } else {
            return transferService.findAccountsByName(name);
        }
    }
}
