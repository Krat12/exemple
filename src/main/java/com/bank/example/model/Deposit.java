package com.bank.example.model;

import com.bank.example.model.operation.Interests;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deposit_generator")
    @SequenceGenerator(sequenceName = "deposit_sequence", name = "deposit_generator", allocationSize = 10)
    private Long id;

    private LocalDate openDate;

    private LocalDate closeDate;

    private Float rate;

    private Double sum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "deposit", cascade = CascadeType.REMOVE, fetch =  FetchType.LAZY)
    private Set<Interests> interests;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deposit deposit = (Deposit) o;
        return Objects.equals(id, deposit.id) &&
                Objects.equals(openDate, deposit.openDate) &&
                Objects.equals(closeDate, deposit.closeDate) &&
                Objects.equals(rate, deposit.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, openDate, closeDate, rate);
    }
}
