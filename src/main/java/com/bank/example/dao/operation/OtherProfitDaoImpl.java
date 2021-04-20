package com.bank.example.dao.operation;

import com.bank.example.dto.OtherProfitDto;
import com.bank.example.model.operation.TypeTransaction;
import org.hibernate.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OtherProfitDaoImpl implements OtherProfitDao {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public List<OtherProfitDto> geOtherProfitsByAccountId(Long accountId) {
       return entityManager.createQuery("SELECT t.id,t.typeTransaction,a.id,t.amount,t.dateTime FROM Transaction t " +
               "JOIN Account a ON a.id = t.account.id " +
               "WHERE a.id = :id AND t.typeTransaction<>:type1 AND t.typeTransaction<>:type2")
               .setParameter("id",accountId)
               .setParameter("type1", TypeTransaction.WITHDRAW)
               .setParameter("type2", TypeTransaction.REFILL)
               .unwrap(Query.class)
               .setResultTransformer(new ResultTransformer() {
            @Override
            public Object transformTuple(Object[] objects, String[] strings) {
                OtherProfitDto otherProfitDto = new OtherProfitDto();
                otherProfitDto.setAccountId((Long) objects[2]);
                otherProfitDto.setAmount((BigDecimal) objects[3]);
                otherProfitDto.setId((Long) objects[0]);
                otherProfitDto.setDateTime((LocalDateTime) objects[4]);
                otherProfitDto.setOtherProfitType((TypeTransaction) objects[1]);
                return otherProfitDto;
            }

            @Override
            public List transformList(List list) {
                return list;
            }
        }).getResultList();
    }
}
