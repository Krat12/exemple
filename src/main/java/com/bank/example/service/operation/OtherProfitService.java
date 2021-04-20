package com.bank.example.service.operation;

import com.bank.example.dto.OtherProfitDto;

import java.util.List;

public interface OtherProfitService {

    List<OtherProfitDto> geOtherProfitsByAccountId(Long accountId);
}
