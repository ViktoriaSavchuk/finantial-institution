package com.financial.institution.services;

import com.financial.institution.entity.BankLog;
import com.financial.institution.entity.ClientAccount;
import com.financial.institution.entity.OperationType;
import java.sql.Timestamp;
import org.springframework.data.domain.Page;

public interface BankLogService {

    void logData(OperationType operationType);

    void logData(OperationType operationType, ClientAccount recipient);

    void logData(OperationType operationType, ClientAccount recipient, ClientAccount sender);

    void logData(OperationType operationType, String description);

    void logData(OperationType operationType, String description, ClientAccount recipient);

    Page<BankLog> getAllAccountTransfers(Integer accountId, Integer pageNumber, Integer pageSize);

    Page<BankLog> getAllAccountTransfers(Integer accountId, Timestamp from, Timestamp to,
                                         Integer pageNumber, Integer pageSize);

    Page<BankLog> getAllClientTransfers(Integer clientId, Integer pageNumber, Integer pageSize);

    Page<BankLog> getAllClientTransfers(Integer clientId, Timestamp from, Timestamp to,
                                        Integer pageNumber, Integer pageSize);
}
