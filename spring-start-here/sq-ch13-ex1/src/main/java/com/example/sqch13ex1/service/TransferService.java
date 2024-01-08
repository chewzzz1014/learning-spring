package com.example.sqch13ex1.service;

import com.example.sqch13ex1.model.Account;
import com.example.sqch13ex1.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {

    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transferMoney(long senderId, long receiverId, BigDecimal amount) {
        Account sender = accountRepository.findAccountById(senderId);
        Account receiver = accountRepository.findAccountById(receiverId);

        BigDecimal senderNewAmount = sender.getAmount().subtract(amount);
        BigDecimal receiverNewAmount = receiver.getAmount().add(amount);

        accountRepository.changeAmount(senderId, senderNewAmount);
        accountRepository.changeAmount(receiverId, receiverNewAmount);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAllAccounts();
    }
}
