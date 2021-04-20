package com.bank.example.dto;

import com.bank.example.model.operation.TypeTransaction;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDto {

    private long id;

    private BigDecimal amount;

    private LocalDateTime dateTime;

    private TypeTransaction transactionType;

    private long accountId;

    public TransactionDto(long id, BigDecimal amount, LocalDateTime dateTime, long accountId, TypeTransaction typeTransaction) {
        this.id = id;
        this.amount = amount;
        this.dateTime = dateTime;
        this.accountId = accountId;
        transactionType = typeTransaction;
    }
}
