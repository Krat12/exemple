package com.bank.example.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
public class OperatorRating {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operator_rating_generator")
    @SequenceGenerator(sequenceName = "operator_rating_sequence", name = "operator_rating_generator", allocationSize = 10)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Operator operator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    private Integer rating;

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperatorRating that = (OperatorRating) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(rating, that.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rating);
    }
}
