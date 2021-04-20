package com.bank.example.controller;

import com.bank.example.converter.CashBackCategoryDetailedConverter;
import com.bank.example.dto.CashBackCategoryDetailedDto;
import com.bank.example.model.Account;
import com.bank.example.model.CashBackCategory;
import com.bank.example.model.CashBackCompany;
import com.bank.example.service.CashBackCategoryService;
import com.bank.example.sqltracker.AssertSqlCount;
import com.bank.example.sqltracker.QueryCountInfoHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/manager/cashback/category")
public class CashBackCategoryManagerController {

    private final CashBackCategoryService cashBackCategoryService;

    public CashBackCategoryManagerController(CashBackCategoryService cashBackCategoryService) {
        this.cashBackCategoryService = cashBackCategoryService;
    }

    @GetMapping("/detailed")
    public ResponseEntity<List<CashBackCategoryDetailedDto>> getCashBackCategory() {
        List<CashBackCategory> cashBackCategories = cashBackCategoryService.getAll();
        return ResponseEntity.ok(CashBackCategoryDetailedConverter.convertEntityToDtoList(cashBackCategories));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteDeposit(@PathVariable Long categoryId) {
        CashBackCategory category = cashBackCategoryService.getByKey(categoryId);

        while (!category.getAccounts().isEmpty()) {
            Iterator<Account> accountIterator = category.getAccounts().iterator();
            Account account = accountIterator.next();
            category.removeAccount(account);
        }

        while (!category.getCashBackCompanies().isEmpty()) {
            Iterator<CashBackCompany> cashBackCompany = category.getCashBackCompanies().iterator();
            CashBackCompany company = cashBackCompany.next();
            category.removeCashBackCompany(company);
        }
        cashBackCategoryService.update(category);
        cashBackCategoryService.remove(category);
        return ResponseEntity.ok().build();
    }
}
