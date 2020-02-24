package com.financial.institution.repository;

import com.financial.institution.entity.ClientAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientAccountRepo extends JpaRepository<ClientAccount, Integer> {
}
