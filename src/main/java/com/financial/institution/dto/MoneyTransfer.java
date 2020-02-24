package com.financial.institution.dto;

import lombok.Data;

@Data
public class MoneyTransfer {

    private Integer fromAccount;
    private Integer toAccount;
    private Integer moneyAmount;
}
