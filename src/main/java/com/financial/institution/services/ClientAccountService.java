package com.financial.institution.services;

public interface ClientAccountService {

    void buyMoney(Integer accountId, Integer moneyAmount);

    void transferMoney(Integer fromAccount, Integer toAccount, Integer moneyAmount);

}
