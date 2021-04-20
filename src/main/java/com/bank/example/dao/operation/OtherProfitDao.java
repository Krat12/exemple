package com.bank.example.dao.operation;

import com.bank.example.dto.OtherProfitDto;

import java.util.List;

public interface OtherProfitDao {

    List<OtherProfitDto> geOtherProfitsByAccountId(Long accountId);
}
