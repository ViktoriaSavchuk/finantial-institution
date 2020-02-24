package com.financial.institution.controller;

import com.financial.institution.dto.BankAccountReplenish;
import com.financial.institution.dto.MoneyPurchase;
import com.financial.institution.dto.MoneyTransfer;
import com.financial.institution.entity.BankLog;
import com.financial.institution.services.BankAccountService;
import com.financial.institution.services.BankLogService;
import com.financial.institution.services.ClientAccountService;
import java.sql.Timestamp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequiredArgsConstructor
public class FinancialInstitutionRestController {

    private final BankAccountService bankAccountService;

    private final BankLogService bankLogService;

    private final ClientAccountService clientAccountService;

    @PostMapping(value = "/replenishTheBankAccount")
    public void replenishTheBankAccount(@RequestBody BankAccountReplenish bankAccountReplenish) {
        bankAccountService.addMoneyToTheBankAccount(bankAccountReplenish.getMoneyAmount());
    }

    @PostMapping(value = "/buyMoney")
    public void buyMoney(@RequestBody MoneyPurchase moneyPurchase) {
        clientAccountService.buyMoney(moneyPurchase.getAccountNumber(), moneyPurchase.getMoneyAmount());
    }

    @PostMapping(value = "/transferMoney")
    public void transferMoney(@RequestBody MoneyTransfer moneyTransfer) {

        clientAccountService.transferMoney(moneyTransfer.getFromAccount(), moneyTransfer.getToAccount(),
            moneyTransfer.getMoneyAmount());
    }

    @GetMapping(value = "/getAccountLog")
    public Page<BankLog> getAllLogsByAccount(@RequestParam Integer accountId, Integer pageNumber, Integer pageSize) {

        return bankLogService.getAllAccountTransfers(accountId, pageNumber, pageSize);
    }

    @GetMapping(value = "/getAccountLogByTime")
    public Page<BankLog> getLogsByAccountByTime(@RequestParam Integer accountId, Timestamp timeFrom,
                                                Timestamp timeTo, Integer pageNumber, Integer pageSize) {

        return bankLogService.getAllAccountTransfers(accountId, timeFrom, timeTo, pageNumber, pageSize);
    }

    @GetMapping(value = "/getClientLog")
    public Page<BankLog> getLogsByClient(@RequestParam Integer clientId, Integer pageNumber, Integer pageSize) {
        return bankLogService.getAllClientTransfers(clientId, pageNumber, pageSize);
    }

    @GetMapping(value = "/getClientLogByTime")
    public Page<BankLog> getLogsByClientByTime(@RequestParam Integer clientId, Timestamp timeFrom,
                                               Timestamp timeTo, Integer pageNumber, Integer pageSize) {
        return bankLogService.getAllClientTransfers(clientId, timeFrom, timeTo, pageNumber, pageSize);
    }
}
