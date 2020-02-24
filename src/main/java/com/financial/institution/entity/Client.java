package com.financial.institution.entity;

import static java.util.stream.Collectors.joining;

import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CLIENT", schema = "PUBLIC", catalog = "FINANCIAL_INSTITUTION")
public class Client {

    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "NAME", nullable = false, length = 64)
    private String name;

    @Basic
    @Column(name = "PHONE", length = 13)
    private String phone;

    @OneToMany(mappedBy = "clientByOwner")
    private Collection<ClientAccount> clientAccountsById;

    @Override
    public String toString() {
        return "Client{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", phone='" + phone + '\'' +
            ", clientAccountsById=" + clientAccountsById.stream()
            .map(clientAccount -> clientAccount.getId().toString())
            .collect(joining(",")) +
            '}';
    }
}
