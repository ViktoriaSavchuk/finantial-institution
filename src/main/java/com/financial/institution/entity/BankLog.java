package com.financial.institution.entity;

import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "ID"),name = "BANK_LOG", schema = "PUBLIC", catalog =
    "FINANCIAL_INSTITUTION")
public class BankLog {

    @Id
    @GeneratedValue
    @Column(name = "ID", updatable = false, nullable = false)
    private Integer id;

    @Basic
    @Column(name = "OPERATION_TYPE", nullable = false)
    private Integer operationType;

    @Basic
    @Column(name = "OPERATION_DESCRIPTION", nullable = false, length = -1)
    private String operationDescription;

    @Basic
    @Column(name = "DATE_AND_TIME", nullable = false)
    private Timestamp dateAndTime;

    @ManyToOne
    @JoinColumn(name = "RECIPIENT", referencedColumnName = "ID")
    private ClientAccount recipient;

    @ManyToOne
    @JoinColumn(name = "SENDER", referencedColumnName = "ID")
    private ClientAccount sender;
}
