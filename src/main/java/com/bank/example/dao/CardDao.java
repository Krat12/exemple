package com.bank.example.dao;

import com.bank.example.model.Card;

import java.util.List;

public interface CardDao extends GenericDao<Long, Card> {

    Card getDefaultCardByAccountId(Long accountId);

    List<Card> getDefaultCardsByAccountIds(List<Long> accountIds);
}
