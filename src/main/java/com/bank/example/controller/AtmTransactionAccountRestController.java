package com.bank.example.controller;

import com.bank.example.converter.AtmTransactionDtoConverter;
import com.bank.example.dto.AtmTransactionDto;
import com.bank.example.model.operation.AtmTransaction;
import com.bank.example.model.operation.TypeTransaction;
import com.bank.example.service.AccountService;
import com.bank.example.service.operation.AtmService;
import com.bank.example.service.operation.AtmTransactionService;
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
public class AtmTransactionAccountRestController {

    private final AtmTransactionService atmTransactionService;
    private final AtmService atmService;
    private final AccountService accountService;

    public AtmTransactionAccountRestController(AtmTransactionService atmTransactionService, AtmService atmService, AccountService accountService) {
        this.atmTransactionService = atmTransactionService;
        this.atmService = atmService;
        this.accountService = accountService;
    }

    @GetMapping("/atmTransaction")
    public ResponseEntity<List<AtmTransactionDto>> getAtmTransactionByAccountIdDto(@PathVariable Long accountId) {
        return ResponseEntity.ok(atmTransactionService.getAtmTransactionDtosByAccountId(accountId));
    }

    @GetMapping("/{transactionId}/atmTransaction/{atmTransactionId}")
    public ResponseEntity<AtmTransactionDto> getDocumentScansDto(@PathVariable Long atmTransactionId) {
        AtmTransaction atmTransaction = atmTransactionService.getByKey(atmTransactionId);
        AtmTransactionDto atmTransactionDto = AtmTransactionDtoConverter.convertEntityToDto(atmTransaction);
        return ResponseEntity.ok(atmTransactionDto);
    }

    @PostMapping("/atmTransaction/refill")
    public void addAtmRefill(@RequestBody AtmTransactionDto dto) {
          AtmTransaction atmTransaction = AtmTransactionDtoConverter.convertDtoToEntity(dto,TypeTransaction.REFILL);
          atmTransactionService.persist(atmTransaction);
    }

    @PostMapping("/atmTransaction/withdraw")
    public void addAtmWithdraw(@RequestBody AtmTransactionDto dto) {
        AtmTransaction atmTransaction = AtmTransactionDtoConverter.convertDtoToEntity(dto,TypeTransaction.WITHDRAW);
        atmTransactionService.persist(atmTransaction);
    }
}
