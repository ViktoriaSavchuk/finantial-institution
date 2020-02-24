package com.financial.institution.services.impl;

import static org.apache.log4j.LogManager.getLogger;

import com.financial.institution.entity.BankAccount;
import com.financial.institution.entity.ClientAccount;
import com.financial.institution.entity.OperationType;
import com.financial.institution.repository.BankAccountRepo;
import com.financial.institution.repository.ClientAccountRepo;
import com.financial.institution.services.BankLogService;
import com.financial.institution.services.ClientAccountService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientAccountServiceImpl implements ClientAccountService {

    private static final Logger LOGGER = getLogger(BankAccountServiceImpl.class);

    private final ClientAccountRepo clientAccountRepo;

    private final BankAccountRepo bankAccountRepo;

    private final BankLogService bankLogService;

    @Override
    public void buyMoney(Integer accountId, Integer moneyAmount) {

        ClientAccount clientAccount = clientAccountRepo.getOne(accountId);
        clientAccount.setAmount(clientAccount.getAmount() + moneyAmount);
        clientAccountRepo.save(clientAccount);

        BankAccount bankAccount = bankAccountRepo.getOne(1);
        bankAccount.setAmount(bankAccount.getAmount() - moneyAmount);
        bankAccountRepo.save(bankAccount);

        bankLogService.logData(OperationType.MONEY_PURCHASE, clientAccount);
        LOGGER.info("Account has bought money from bank");
    }

    @Override
    public void transferMoney(Integer fromAccount, Integer toAccount, Integer moneyAmount) {

        ClientAccount sender = clientAccountRepo.getOne(fromAccount);
        sender.setAmount(sender.getAmount() - moneyAmount);
        clientAccountRepo.save(sender);

        ClientAccount recipient = clientAccountRepo.getOne(toAccount);
        recipient.setAmount(recipient.getAmount() + moneyAmount);
        clientAccountRepo.save(recipient);

        bankLogService.logData(OperationType.MONEY_TRANSFER, recipient, sender);
        LOGGER.info(String.format("Successful transfer from %s to %s", sender, recipient));
    }
}
