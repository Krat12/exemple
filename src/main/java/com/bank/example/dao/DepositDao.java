package com.bank.example.dao;

import com.bank.example.model.Deposit;
import com.bank.example.model.operation.Interests;

import java.util.List;

public interface DepositDao extends GenericDao<Long, Deposit> {

    List<Interests> getInterestsByDepositIds(List<Long> ids);
}
