package com.bank.example.model.operation;

import com.bank.example.model.Deposit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Interests {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Deposit deposit;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional=false)
    @MapsId
    private Transaction transaction = new Transaction(TypeTransaction.INTERESTS);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interests interests = (Interests) o;
        return Objects.equals(id, interests.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
