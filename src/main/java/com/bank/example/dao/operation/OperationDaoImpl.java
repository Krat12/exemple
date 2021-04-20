package com.bank.example.dao.operation;

import com.bank.example.dao.AbstractDao;
import com.bank.example.dto.TransactionDto;
import com.bank.example.model.operation.Transaction;
import com.bank.example.model.operation.TypeTransaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OperationDaoImpl extends AbstractDao<Long, Transaction> implements TransactionDao {

    @Override
    public List<TransactionDto> getAllTransactionDtoByAccountId(Long accountId) {
        return entityManager.createQuery(
                "SELECT new com.bank.example.dto.TransactionDto(t.id, t.amount, t.dateTime, t.account.id, t.typeTransaction) " +
                        "FROM Transaction t WHERE t.account.id=:accountId",
                TransactionDto.class
        )
                .setParameter("accountId", accountId)
                .getResultList();
    }

    @Override
    public List<TransactionDto> getRefillTransactionDtoByAccountId(Long accountId) {
        return entityManager.createQuery(
                "SELECT new com.bank.example.dto.TransactionDto(t.id, t.amount, t.dateTime, t.account.id, t.typeTransaction) " +
                        "FROM Transaction t WHERE t.account.id=:accountId AND t.typeTransaction = :type",
                TransactionDto.class
        )
                .setParameter("accountId", accountId)
                .setParameter("type", TypeTransaction.REFILL)
                .getResultList();
    }

    @Override
    public List<TransactionDto> getWithdrawTransactionDtoByAccountId(Long accountId) {
        return entityManager.createQuery(
                "SELECT new com.bank.example.dto.TransactionDto(t.id, t.amount, t.dateTime, t.account.id, t.typeTransaction) " +
                        "FROM Transaction t WHERE t.account.id=:accountId AND t.typeTransaction = :type",
                TransactionDto.class
        )
                .setParameter("accountId", accountId)
                .setParameter("type", TypeTransaction.WITHDRAW)
                .getResultList();
    }

    @Override
    public List<TransactionDto> getCashBackTransactionDtoByAccountId(Long accountId) {
        return entityManager.createQuery(
                "SELECT new com.bank.example.dto.TransactionDto(t.id, t.amount, t.dateTime, t.account.id, t.typeTransaction) " +
                        "FROM Transaction t WHERE t.account.id=:accountId AND t.typeTransaction = :type",
                TransactionDto.class
        )
                .setParameter("accountId", accountId)
                .setParameter("type", TypeTransaction.CASH_BACK)
                .getResultList();
    }

    @Override
    public List<TransactionDto> getInterestsTransactionDtoByAccountId(Long accountId) {
        return entityManager.createQuery(
                "SELECT new com.bank.example.dto.TransactionDto(t.id, t.amount, t.dateTime, t.account.id, t.typeTransaction) " +
                        "FROM Transaction t WHERE t.account.id=:accountId AND t.typeTransaction = :type",
                TransactionDto.class
        )
                .setParameter("accountId", accountId)
                .setParameter("type", TypeTransaction.INTERESTS)
                .getResultList();
    }
}
