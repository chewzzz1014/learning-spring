package com.example.sqch14ex1;

import com.example.sqch14ex1.exception.AccountNotFoundException;
import com.example.sqch14ex1.model.Account;
import com.example.sqch14ex1.repository.AccountRepository;
import com.example.sqch14ex1.service.TransferService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TransferServiceWithAnnotationsUnitTests {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransferService transferService;

    @Test
    public void moneyTransferHappyFlow() {
        Account sender = new Account();
        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));

        Account destination = new Account();
        sender.setId(2);
        sender.setAmount(new BigDecimal(1000));

        given(accountRepository.findById(sender.getId()))
                .willReturn(Optional.of(sender));
        given(accountRepository.findById(destination.getId()))
                .willReturn(Optional.of(destination));

        transferService.transferMoney(1, 2, new BigDecimal(100));

        verify(accountRepository)
                .changeAmount(1, new BigDecimal(900));
        verify(accountRepository)
                .changeAmount(2, new BigDecimal(1100));
    }

    @Test
    public void moneyTransferDestinationAccountNotFoundFlow() {
        Account sender = new Account();
        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));

        given(accountRepository.findById(1L))
                .willReturn(Optional.of(sender));
        given(accountRepository.findById(2L))
                .willReturn(Optional.empty());

        assertThrows(
                AccountNotFoundException.class,
                () -> transferService.transferMoney(1, 2, new BigDecimal(100))
        );
    }
}
