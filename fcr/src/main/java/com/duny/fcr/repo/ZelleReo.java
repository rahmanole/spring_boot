package com.duny.fcr.repo;

import com.duny.fcr.entity.Zelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ZelleReo extends JpaRepository<Zelle,Long> {
    String GET_AMOUNT_BY_PID = "select amount from zelle where payment_id=?";

    @Query(nativeQuery = true,value = GET_AMOUNT_BY_PID)
    Object getAmount(String pid);
}
