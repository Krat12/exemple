package com.bank.example.service.operation;

import com.bank.example.dto.TransactionDto;
import com.bank.example.model.operation.Transaction;
import com.bank.example.service.GenericService;

import java.util.List;

public interface TransactionService extends GenericService<Long, Transaction> {

    List<TransactionDto> getAllTransactionDtoByAccountId(Long accountId);

    List<TransactionDto> getRefillTransactionDtoByAccountId(Long accountId);

    List<TransactionDto> getWithdrawTransactionDtoByAccountId(Long accountId);

    List<TransactionDto> getCashBackTransactionDtoByAccountId(Long accountId);

    List<TransactionDto> getInterestsTransactionDtoByAccountId(Long accountId);
}
