package com.bank.example.dao;

import com.bank.example.model.Card;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardDaoImpl extends AbstractDao<Long, Card> implements CardDao {
    @Override
    public Card getDefaultCardByAccountId(Long accountId) {
        return entityManager.createQuery(
                "SELECT c FROM Card c WHERE c.account.id = :accountId AND c.isDefaultCard = true",
                Card.class
        )
                .setParameter("accountId", accountId)
                .getSingleResult();
    }

    @Override
    public List<Card> getDefaultCardsByAccountIds(List<Long> accountIds) {
        return entityManager.createQuery(
                "SELECT c FROM Card c WHERE c.account.id IN :accountIds AND c.isDefaultCard = true"
        )
                .setParameter("accountIds", accountIds)
                .getResultList();
    }
}
