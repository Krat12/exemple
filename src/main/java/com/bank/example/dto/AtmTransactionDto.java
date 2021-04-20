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
public class AtmTransactionDto {

    private long id;

    private BigDecimal amount;

    private TypeTransaction atmTransactionType;

    private LocalDateTime dateTime;

    private long accountId;

    private long atmId;

    public AtmTransactionDto(long id, BigDecimal amount, LocalDateTime dateTime, long accountId, long atmId, TypeTransaction typeTransaction) {
        this.id = id;
        this.amount = amount;
        this.dateTime = dateTime;
        this.accountId = accountId;
        this.atmId = atmId;
        this.atmTransactionType = typeTransaction;
    }
}
