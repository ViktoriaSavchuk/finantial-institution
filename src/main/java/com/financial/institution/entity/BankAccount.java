package com.financial.institution.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BANK_ACCOUNT", schema = "PUBLIC", catalog = "FINANCIAL_INSTITUTION")
public class BankAccount {

    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "AMOUNT", nullable = false)
    private Integer amount;
}
