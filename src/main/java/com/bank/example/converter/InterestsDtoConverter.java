package com.bank.example.converter;

import com.bank.example.dto.InterestsDto;
import com.bank.example.dto.ManagerDto;
import com.bank.example.model.Account;
import com.bank.example.model.Deposit;
import com.bank.example.model.Manager;
import com.bank.example.model.operation.Interests;
import com.bank.example.model.operation.Transaction;
import com.bank.example.model.operation.TypeTransaction;

public class InterestsDtoConverter {

    public static Interests convertEntityToDto(InterestsDto interestsDto, TypeTransaction typeTransaction) {
        Interests interests = new Interests();
        Deposit deposit = new Deposit();
        deposit.setId(interestsDto.getDepositId());
        interests.setDeposit(deposit);

        Account account = new Account();
        account.setId(interestsDto.getAccountId());

        Transaction transaction = new Transaction(typeTransaction);
        transaction.setDateTime(interestsDto.getDateTime());
        transaction.setAmount(interestsDto.getAmount());
        transaction.setAccount(account);

        interests.setTransaction(transaction);

        return interests;
    }

}
