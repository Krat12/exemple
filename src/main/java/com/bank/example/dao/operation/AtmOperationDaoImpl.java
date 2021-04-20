package com.bank.example.dao.operation;

import com.bank.example.dao.AbstractDao;
import com.bank.example.dto.AtmTransactionDto;
import com.bank.example.dto.TransactionDto;
import com.bank.example.model.operation.Atm;
import com.bank.example.model.operation.AtmTransaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AtmOperationDaoImpl extends AbstractDao<Long, AtmTransaction> implements AtmTransactionDao {

    @Override
    public List<AtmTransactionDto> getAtmTransactionDtosByAccountId(Long accountId) {
        return entityManager.createQuery(
                "SELECT new com.bank.example.dto.AtmTransactionDto(at.id, t.amount, t.dateTime, t.account.id, at.atm.id,t.typeTransaction) " +
                        "FROM AtmTransaction at JOIN Transaction t ON t.id = at.id WHERE t.account.id=:accountId",
                AtmTransactionDto.class
        )
                .setParameter("accountId", accountId)
                .getResultList();
    }
}
