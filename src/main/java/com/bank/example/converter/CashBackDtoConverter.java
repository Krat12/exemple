package com.bank.example.converter;

import com.bank.example.dto.CashBackDto;
import com.bank.example.dto.InterestsDto;
import com.bank.example.model.Account;
import com.bank.example.model.CashBackCompany;
import com.bank.example.model.Deposit;
import com.bank.example.model.operation.CashBack;
import com.bank.example.model.operation.Interests;
import com.bank.example.model.operation.Transaction;
import com.bank.example.model.operation.TypeTransaction;

public class CashBackDtoConverter  {

    public static CashBack convertEntityToDto(CashBackDto cashBackDto, TypeTransaction typeTransaction) {
        CashBack cashBack = new CashBack();
        CashBackCompany company = new CashBackCompany();
        company.setId(cashBackDto.getCashBackCompanyId());
        cashBack.setCashBackCompany(company);

        Account account = new Account();
        account.setId(cashBackDto.getAccountId());

        Transaction transaction = new Transaction(typeTransaction);
        transaction.setDateTime(cashBackDto.getDateTime());
        transaction.setAmount(cashBackDto.getAmount());
        transaction.setAccount(account);

        cashBack.setTransaction(transaction);
        return cashBack;
    }
}
