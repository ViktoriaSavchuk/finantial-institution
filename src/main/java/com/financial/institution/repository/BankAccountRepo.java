package com.financial.institution.repository;

import com.financial.institution.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepo extends JpaRepository<BankAccount, Integer> {
}
