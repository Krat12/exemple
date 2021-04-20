package com.bank.example.model.operation;

import com.bank.example.model.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_generator")
    @SequenceGenerator(sequenceName = "transaction_sequence", name = "transaction_generator", allocationSize = 10)
    private Long id;

    private BigDecimal amount;

    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    private TypeTransaction typeTransaction;

    public Transaction(LocalDateTime dateTime, Account account) {
        this.dateTime = dateTime;
        this.account = account;
    }

    public Transaction(TypeTransaction typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public Transaction(BigDecimal amount, LocalDateTime dateTime, Account account) {
        this.amount = amount;
        this.dateTime = dateTime;
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction operation = (Transaction) o;
        return Objects.equals(id, operation.id) &&
                Objects.equals(dateTime, operation.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime);
    }
}
