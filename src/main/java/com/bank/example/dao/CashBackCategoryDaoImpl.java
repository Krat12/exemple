package com.bank.example.dao;

import com.bank.example.model.Account;
import com.bank.example.model.CashBackCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CashBackCategoryDaoImpl extends AbstractDao<Long, CashBackCategory> implements CashBackCategoryDao {


    @Override
    public CashBackCategory getByKey(Long key) {
        return entityManager.createQuery("SELECT ch FROM CashBackCategory ch JOIN FETCH ch.accounts JOIN FETCH ch.cashBackCompanies WHERE ch.id = :id",CashBackCategory.class)
                .setParameter("id",key)
                .getSingleResult();
    }

    @Override
    public List<Account> getAccountByCategoryId(Long key) {
        return null;
    }

    @Override
    public List<CashBackCategory> getAll() {
        return entityManager.createQuery("SELECT distinct ch FROM CashBackCategory ch JOIN FETCH ch.cashBackCompanies JOIN FETCH ch.uploader u JOIN FETCH u.department ").getResultList();
    }
}
