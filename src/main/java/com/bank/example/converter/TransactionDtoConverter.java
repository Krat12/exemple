package com.bank.example.converter;

import com.bank.example.dto.TransactionDto;
import com.bank.example.model.operation.Transaction;

public class TransactionDtoConverter {

    public static TransactionDto convertEntityToDto(Transaction entity) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(entity.getId());
        transactionDto.setAmount(entity.getAmount());
        transactionDto.setDateTime(entity.getDateTime());
        transactionDto.setAccountId(entity.getAccount().getId());
        return transactionDto;
    }
}
