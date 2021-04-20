package com.bank.example.controller;

import com.bank.example.converter.CashBackDtoConverter;
import com.bank.example.converter.InterestsDtoConverter;
import com.bank.example.dto.CashBackDto;
import com.bank.example.dto.InterestsDto;
import com.bank.example.dto.OtherProfitDto;
import com.bank.example.model.operation.Interests;
import com.bank.example.model.operation.TypeTransaction;
import com.bank.example.service.AccountService;
import com.bank.example.service.CashBackCompanyService;
import com.bank.example.service.DepositService;
import com.bank.example.service.operation.CashBackService;
import com.bank.example.service.operation.InterestsService;
import com.bank.example.service.operation.OtherProfitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/account/{accountId}/transaction")
public class OtherProfitAccountRestController {

    private final CashBackService cashBackService;
    private final InterestsService interestsService;
    private final AccountService accountService;
    private final DepositService depositService;
    private final CashBackCompanyService cashBackCompanyService;
    private final OtherProfitService otherProfitService;

    public OtherProfitAccountRestController(CashBackService cashBackService, InterestsService interestsService, AccountService accountService, DepositService depositService, CashBackCompanyService cashBackCompanyService, OtherProfitService otherProfitService) {
        this.cashBackService = cashBackService;
        this.interestsService = interestsService;
        this.accountService = accountService;
        this.depositService = depositService;
        this.cashBackCompanyService = cashBackCompanyService;
        this.otherProfitService = otherProfitService;
    }

    @GetMapping("/otherProfit")
    public ResponseEntity<List<OtherProfitDto>> getAtmTransactionByAccountIdDto(@PathVariable Long accountId) {
        return ResponseEntity.ok(otherProfitService.geOtherProfitsByAccountId(accountId));
    }

    @GetMapping("/{transactionId}/otherProfit/{profitId}")
    public ResponseEntity<OtherProfitDto> getDocumentScansDto(@PathVariable Long profitId) {
//        OtherProfit otherProfit = otherProfitService.getByKey(profitId);
//        OtherProfitDto otherProfitDto = OtherProfitDtoConverter.convertEntityToDto(otherProfit);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/otherProfit/interests")
    public void addInterests(@RequestBody InterestsDto dto) {
        interestsService.persist(InterestsDtoConverter.convertEntityToDto(dto, TypeTransaction.INTERESTS));
    }

    @PostMapping("/otherProfit/cashBack")
    public void addCashBack(@RequestBody CashBackDto dto) {
        cashBackService.persist(CashBackDtoConverter.convertEntityToDto(dto, TypeTransaction.CASH_BACK));
    }
}
