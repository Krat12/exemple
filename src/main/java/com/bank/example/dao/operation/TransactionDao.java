package com.bank.example.dao.operation;

import com.bank.example.dao.GenericDao;
import com.bank.example.dto.TransactionDto;
import com.bank.example.model.operation.Transaction;

import java.util.List;

public interface TransactionDao extends GenericDao<Long, Transaction> {

    List<TransactionDto> getAllTransactionDtoByAccountId(Long accountId);

    List<TransactionDto> getRefillTransactionDtoByAccountId(Long accountId);

    List<TransactionDto> getWithdrawTransactionDtoByAccountId(Long accountId);

    List<TransactionDto> getCashBackTransactionDtoByAccountId(Long accountId);

    List<TransactionDto> getInterestsTransactionDtoByAccountId(Long accountId);
}
