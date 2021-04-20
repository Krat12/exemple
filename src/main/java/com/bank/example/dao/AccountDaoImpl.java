package com.bank.example.dao;

import com.bank.example.dto.AccountInfoDto;
import com.bank.example.dto.AccountSumDto;
import com.bank.example.model.Account;
import org.hibernate.annotations.QueryHints;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class AccountDaoImpl extends AbstractDao<Long, Account> implements AccountDao {

    @Override
    public List<Account> getAccountsByIds(List<Long> accountIds) {
        return entityManager.createQuery(
                "SELECT distinct a FROM Account a JOIN FETCH a.settings JOIN FETCH a.closeRequest" +
                        " JOIN FETCH a.documentScans JOIN FETCH a.deposits d JOIN FETCH d.interests WHERE a.id IN :accountIds",
                Account.class
        )
                .setParameter("accountIds", accountIds)
                .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                .getResultList();
    }

    @Override
    public List<AccountSumDto> getTopAccountListBySumOnDeposit() {
        return entityManager.createQuery(
                "SELECT a.id, a.firstName, a.lastName, SUM(d.sum) FROM Account a JOIN Deposit d on d.account.id = a.id GROUP BY a.id"
        )
                .unwrap( org.hibernate.query.Query.class )
                .setResultTransformer( new AccountSumDtoTransformer() )
                .getResultList();
    }


    private class AccountSumDtoTransformer implements ResultTransformer {

        @Override
        public Object transformTuple(Object[] objects, String[] strings) {
            AccountSumDto accountSumDto = new AccountSumDto();
            accountSumDto.setId((Long) objects[0]);
            accountSumDto.setFirstName((String) objects[1]);
            accountSumDto.setLastName((String) objects[2]);
            accountSumDto.setSum((Double) objects[3]);
            return accountSumDto;
        }

        @Override
        public List transformList(List list) {
            return list;
        }

    }
//
//    @Override
//    public Account getByKey(Long key) {
//        return entityManager.createQuery("SELECT a FROM Account a JOIN FETCH a.settings s JOIN FETCH a.documentScans WHERE a.id = :id", Account.class)
//                .setParameter("id",key)
//                .getSingleResult();
//    }
}
