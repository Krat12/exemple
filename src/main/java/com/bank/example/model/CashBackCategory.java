package com.bank.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class CashBackCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cash_back_category_generator")
    @SequenceGenerator(sequenceName = "cash_back_category_sequence", name = "cash_back_category_generator", allocationSize = 10)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Manager uploader;

    @ManyToOne(fetch = FetchType.LAZY)
    private Manager editor;

    @ManyToMany(mappedBy = "cashBackCategories", fetch = FetchType.LAZY)
    private Set<Account> accounts = new HashSet<>();

    @ManyToMany(mappedBy = "cashBackCategories", fetch = FetchType.LAZY)
    private Set<CashBackCompany> cashBackCompanies = new HashSet<>();

    public void addAccount(Account account) {
        accounts.add(account);
        account.getCashBackCategories().add(this);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
        account.getCashBackCategories().remove(this);
    }

    public void addCashBackCompany(CashBackCompany cashBackCompany) {
        cashBackCompanies.add(cashBackCompany);
        cashBackCompany.getCashBackCategories().add(this);
    }

    public void removeCashBackCompany(CashBackCompany cashBackCompany) {
        cashBackCompanies.remove(cashBackCompany);
        cashBackCompany.getCashBackCategories().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashBackCategory that = (CashBackCategory) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
