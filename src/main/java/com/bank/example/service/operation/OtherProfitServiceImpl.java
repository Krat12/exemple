package com.bank.example.service.operation;

import com.bank.example.dao.operation.OtherProfitDao;
import com.bank.example.dto.OtherProfitDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OtherProfitServiceImpl implements OtherProfitService{


    private final OtherProfitDao otherProfitDao;

    @Autowired
    public OtherProfitServiceImpl(OtherProfitDao otherProfitDao) {
        this.otherProfitDao = otherProfitDao;
    }

    @Override
    public List<OtherProfitDto> geOtherProfitsByAccountId(Long accountId) {
        return otherProfitDao.geOtherProfitsByAccountId(accountId);
    }
}
