package com.bank.example.model.operation;

import com.bank.example.model.CashBackCompany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class CashBack  {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private CashBackCompany cashBackCompany;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional=false)
    @MapsId
    private Transaction transaction = new Transaction(TypeTransaction.CASH_BACK);


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashBack cashBack = (CashBack) o;
        return Objects.equals(id, cashBack.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
