package com.bank.example.dao;

import com.bank.example.model.Deposit;
import com.bank.example.model.operation.Interests;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepositDaoImpl extends AbstractDao<Long, Deposit> implements DepositDao {

    @Override
    public List<Interests> getInterestsByDepositIds(List<Long> ids) {
        return entityManager.createQuery("SELECT i FROM Interests i WHERE i.deposit.id in :ids")
                .setParameter("ids",ids).getResultList();
    }
}
