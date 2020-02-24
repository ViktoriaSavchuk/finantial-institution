package com.financial.institution.services.impl;

import static org.apache.log4j.LogManager.getLogger;

import com.financial.institution.entity.BankAccount;
import com.financial.institution.entity.OperationType;
import com.financial.institution.repository.BankAccountRepo;
import com.financial.institution.services.BankAccountService;
import com.financial.institution.services.BankLogService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private static final Logger LOGGER = getLogger(BankAccountServiceImpl.class);

    private final BankAccountRepo bankAccountRepo;

    private final BankLogService bankLogService;

    @Override
    public void addMoneyToTheBankAccount(Integer moneyValue) {
        BankAccount bankAccount = bankAccountRepo.getOne(1);
        bankAccount.setAmount(bankAccount.getAmount() + moneyValue);
        bankAccountRepo.save(bankAccount);
        bankLogService.logData(OperationType.MONEY_ISSUE);
        LOGGER.info("Money to the bank account was added");
    }
}
