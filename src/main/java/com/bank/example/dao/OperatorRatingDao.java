package com.bank.example.dao;

import com.bank.example.dto.OperatorRatingDto;
import com.bank.example.model.OperatorRating;

import java.util.List;

public interface OperatorRatingDao extends GenericDao<Long, OperatorRating> {

    List<OperatorRatingDto> getOperatingRaitingDto();
}
