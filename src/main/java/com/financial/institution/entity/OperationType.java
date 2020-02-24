package com.financial.institution.entity;

import lombok.Getter;

@Getter
public enum OperationType {

    MONEY_ISSUE(1),
    MONEY_PURCHASE(2),
    MONEY_TRANSFER(3),
    TRANSFERS_STORY(4),
    TRANSFERS_STORY_BY_DATES(5),
    CLIENT_STORY(6),
    CLIENT_STORY_BY_DATES(7);

    private final Integer operationType;

    OperationType(Integer operationType) {
        this.operationType = operationType;
    }
}
