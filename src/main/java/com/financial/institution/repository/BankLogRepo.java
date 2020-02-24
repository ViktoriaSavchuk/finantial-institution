package com.financial.institution.repository;

import com.financial.institution.entity.BankLog;
import java.sql.Timestamp;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BankLogRepo extends JpaRepository<BankLog, Integer> {


    @Query("SELECT log FROM BankLog log WHERE log.recipient = :accountId OR log.sender = :accountId")
    Page<BankLog> getAllLogsByAccount(@Param("accountId") Integer accountId, Pageable pageable);

    @Query("SELECT log FROM BankLog log WHERE log.dateAndTime BETWEEN :timeFrom AND :timeTo AND "
        + "log.recipient = :accountId "
        + "OR log.sender = :accountId")
    Page<BankLog> getAllLogsByAccount(@Param("accountId") Integer accountId,
                                      @Param("timeFrom") Timestamp timeFrom, @Param("timeTo") Timestamp timeTo,
                                      Pageable pageable);

    @Query("SELECT log FROM BankLog log"
        + " JOIN log.recipient as ca on log.recipient.id = ca.id OR log.sender.id=ca.id"
        + " JOIN ca.clientByOwner c on ca.clientByOwner.id = c.id"
        + " WHERE c.id=:clientId")
    Page<BankLog> getAllLogsByClient(@Param("clientId") Integer clientId, Pageable pageable);

    @Query("SELECT log FROM BankLog log"
        + " JOIN log.recipient ca on log.recipient.id = ca.id OR log.sender.id=ca.id"
        + " JOIN ca.clientByOwner c on ca.clientByOwner.id = c.id"
        + " WHERE c.id=:clientId"
        + "  and log.dateAndTime between :timeFrom and :timeTo")
    Page<BankLog> getAllLogsByClient(@Param("clientId") Integer clientId,
                                     @Param("timeFrom") Timestamp timeFrom, @Param("timeTo") Timestamp timeTo,
                                     Pageable pageable);




}
