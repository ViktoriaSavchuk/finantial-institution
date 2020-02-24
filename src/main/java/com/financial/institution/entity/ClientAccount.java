package com.financial.institution.entity;

import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CLIENT_ACCOUNT", schema = "PUBLIC", catalog = "FINANCIAL_INSTITUTION")
public class ClientAccount {

    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "AMOUNT", nullable = false)
    private Integer amount;

    @OneToMany(mappedBy = "recipient")
    private Collection<BankLog> bankLogsByRecipientId;

    @OneToMany(mappedBy = "sender")
    private Collection<BankLog> bankLogsBySenderId;

    @ManyToOne
    @JoinColumn(name = "OWNER", referencedColumnName = "ID", nullable = false)
    private Client clientByOwner;

    @Override
    public String toString() {
        return "ClientAccount{" +
            "id=" + id +
            ", amount=" + amount +
            ", clientByOwner=" + clientByOwner.getId() +
            '}';
    }
}
