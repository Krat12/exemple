package com.bank.example.service;

import com.bank.example.model.Card;

import java.util.List;

public interface CardService extends GenericService<Long, Card> {

    Card getDefaultCardByAccountId(Long accountId);

    List<Card> getDefaultCardsByAccountIds(List<Long> accountIds);
}
