package com.financial.institution.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class MoneyPurchase {

    private Integer accountNumber;
    private Integer moneyAmount;
}
