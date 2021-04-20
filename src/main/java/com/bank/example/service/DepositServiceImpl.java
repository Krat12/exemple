package com.bank.example.service;

import com.bank.example.dao.DepositDao;
import com.bank.example.model.Account;
import com.bank.example.model.Card;
import com.bank.example.model.Deposit;
import com.bank.example.model.operation.Interests;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class DepositServiceImpl extends AbstractService<Long, Deposit> implements DepositService {

    private final DepositDao depositDao;

    private final AccountService accountService;
    private final CardService cardService;

    public DepositServiceImpl(DepositDao depositDao, AccountService accountService, CardService cardService) {
        super(depositDao);
        this.depositDao = depositDao;
        this.accountService = accountService;
        this.cardService = cardService;
    }

    @Override
    @Transactional
    public void deleteOutDatedDeposit(List<Long> accountIds) {
        List<Account> accountList = accountService.getAccountsByIds(accountIds);
        List<Card> cards = cardService.getDefaultCardsByAccountIds(accountIds);
        for (int i = 0; i < accountList.size() ; i++) {

            List<Deposit> depositList = new ArrayList<>(accountList.get(i).getDeposits());

            for (int j = 0; j < depositList.size() ; j++) {
                LocalDate closeDate = depositList.get(j).getCloseDate();
                if (DAYS.between(closeDate, LocalDate.now()) > 1) {
                    Set<Interests> interests = depositList.get(j).getInterests();
                    cards.get(i).addSum(depositList.get(j).getSum());
                    interests.forEach(k -> k.setDeposit(null));
                    accountList.get(i).getDeposits().remove(depositList.get(j));
                }
            }

            cardService.update(cards.get(i));
        }
    }

    @Override
    @Transactional
    public List<Long> removeAllDepositsByAccountId(Long accountId) {
        Account account = accountService.getByKey(accountId);
        List<Long> depositIds = account.getDeposits().stream().map(Deposit::getId).collect(Collectors.toList());
        account.setDeposits(null);

        return depositIds;
    }
}
