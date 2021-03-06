package com.bank.example.service.operation;

import com.bank.example.dao.operation.TransactionDao;
import com.bank.example.dto.TransactionDto;
import com.bank.example.model.operation.Transaction;
import com.bank.example.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl extends AbstractService<Long, Transaction> implements TransactionService {

    private final TransactionDao transactionDao;

    public OperationServiceImpl(TransactionDao transactionDao) {
        super(transactionDao);
        this.transactionDao = transactionDao;
    }

    @Override
    public List<TransactionDto> getAllTransactionDtoByAccountId(Long accountId) {
        return transactionDao.getAllTransactionDtoByAccountId(accountId);
    }

    @Override
    public List<TransactionDto> getRefillTransactionDtoByAccountId(Long accountId) {
        return transactionDao.getRefillTransactionDtoByAccountId(accountId);
    }

    @Override
    public List<TransactionDto> getWithdrawTransactionDtoByAccountId(Long accountId) {
        return transactionDao.getWithdrawTransactionDtoByAccountId(accountId);
    }

    @Override
    public List<TransactionDto> getCashBackTransactionDtoByAccountId(Long accountId) {
        return transactionDao.getCashBackTransactionDtoByAccountId(accountId);
    }

    @Override
    public List<TransactionDto> getInterestsTransactionDtoByAccountId(Long accountId) {
        return transactionDao.getInterestsTransactionDtoByAccountId(accountId);
    }
}
