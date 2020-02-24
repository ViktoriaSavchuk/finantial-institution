package com.financial.institution.services.impl;

import static java.lang.System.currentTimeMillis;
import static org.apache.log4j.LogManager.getLogger;

import com.financial.institution.entity.BankLog;
import com.financial.institution.entity.ClientAccount;
import com.financial.institution.entity.OperationType;
import com.financial.institution.repository.BankLogRepo;
import com.financial.institution.repository.ClientAccountRepo;
import com.financial.institution.services.BankLogService;
import java.sql.Timestamp;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankLogServiceImpl implements BankLogService {

    private static final Logger LOGGER = getLogger(BankAccountServiceImpl.class);

    private final BankLogRepo bankLogRepo;

    private final ClientAccountRepo clientAccountRepo;

    @Override
    public void logData(OperationType operationType) {
        BankLog bankLog = BankLog.builder()
            .dateAndTime(new Timestamp(currentTimeMillis()))
            .operationType(operationType.getOperationType())
            .operationDescription(operationType.toString())
            .build();
        bankLogRepo.save(bankLog);
    }

    @Override
    public void logData(OperationType operationType, ClientAccount recipient) {
        BankLog log = BankLog.builder()
            .dateAndTime(new Timestamp(currentTimeMillis()))
            .operationType(operationType.getOperationType())
            .operationDescription(operationType.toString())
            .recipient(recipient)
            .build();
        bankLogRepo.save(log);
    }

    @Override
    public void logData(OperationType operationType, ClientAccount recipient, ClientAccount sender) {
        BankLog bankLog = BankLog.builder()
            .id(2)
            .dateAndTime(new Timestamp(currentTimeMillis()))
            .operationType(operationType.getOperationType())
            .operationDescription(operationType.toString())
            .recipient(recipient)
            .sender(sender)
            .build();
        bankLogRepo.save(bankLog);
    }

    @Override
    public void logData(OperationType operationType, String description) {
        BankLog bankLog = BankLog.builder()
            .dateAndTime(new Timestamp(currentTimeMillis()))
            .operationType(operationType.getOperationType())
            .operationDescription(String.format("%s: %s", operationType.toString(), description))
            .build();

        bankLogRepo.save(bankLog);
    }

    @Override
    public void logData(OperationType operationType, String description, ClientAccount recipient) {
        BankLog log = BankLog.builder()
            .dateAndTime(new Timestamp(currentTimeMillis()))
            .operationType(operationType.getOperationType())
            .operationDescription(String.format("%s: %s", operationType.toString(), description))
            .build();
        bankLogRepo.save(log);
    }

    @Override
    public Page<BankLog> getAllAccountTransfers(Integer accountId, Integer pageNumber, Integer pageSize) {
        logData(OperationType.TRANSFERS_STORY, clientAccountRepo.getOne(accountId));
        return bankLogRepo.getAllLogsByAccount(accountId, PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Page<BankLog> getAllAccountTransfers(Integer accountId, Timestamp from, Timestamp to,
                                                Integer pageNumber, Integer pageSize) {
        logData(OperationType.TRANSFERS_STORY, String.format("from:%sto: %s", from, to),
            clientAccountRepo.getOne(accountId));
        return bankLogRepo.getAllLogsByAccount(accountId, from, to, PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Page<BankLog> getAllClientTransfers(Integer clientId, Integer pageNumber, Integer pageSize) {
        logData(OperationType.CLIENT_STORY, String.format("Client id:%s", clientId.toString()));
        return bankLogRepo.getAllLogsByClient(clientId, PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Page<BankLog> getAllClientTransfers(Integer clientId, Timestamp from, Timestamp to,
                                               Integer pageNumber, Integer pageSize) {
        logData(OperationType.CLIENT_STORY, String.format("%s from:%sto: %s", clientId, from, to));
        return bankLogRepo.getAllLogsByClient(clientId, from, to, PageRequest.of(pageNumber, pageSize));
    }
}
