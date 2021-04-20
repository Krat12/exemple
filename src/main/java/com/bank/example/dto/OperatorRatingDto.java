package com.bank.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class OperatorRatingDto {

    private Long id;

    private String firstName;

    private String lastName;

    private Double averageRating;

}
