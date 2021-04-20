package com.bank.example.converter;

import com.bank.example.dto.AtmTransactionDto;
import com.bank.example.model.Account;
import com.bank.example.model.operation.Atm;
import com.bank.example.model.operation.AtmTransaction;
import com.bank.example.model.operation.Transaction;
import com.bank.example.model.operation.TypeTransaction;

public class AtmTransactionDtoConverter {

    public static AtmTransactionDto convertEntityToDto(AtmTransaction entity) {
        AtmTransactionDto atmTransactionDto = new AtmTransactionDto();
        atmTransactionDto.setId(entity.getId());
        atmTransactionDto.setAmount(entity.getTransaction().getAmount());
        atmTransactionDto.setDateTime(entity.getTransaction().getDateTime());
        atmTransactionDto.setAccountId(entity.getTransaction().getAccount().getId());
        return atmTransactionDto;
    }

    public static AtmTransaction convertDtoToEntity (AtmTransactionDto dto, TypeTransaction typeTransaction) {
        AtmTransaction atmTransaction = new AtmTransaction();

        Account account = new Account();
        account.setId(dto.getAccountId());

        Transaction transaction = new Transaction(typeTransaction);
        transaction.setAccount(account);
        transaction.setAmount(dto.getAmount());
        transaction.setDateTime(dto.getDateTime());
        atmTransaction.setTransaction(transaction);

        Atm atm = new Atm();
        atm.setId(dto.getAtmId());

        atmTransaction.setAtm(atm);
        return atmTransaction;
    }
}
