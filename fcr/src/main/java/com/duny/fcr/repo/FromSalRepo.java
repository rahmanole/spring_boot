package com.duny.fcr.repo;

import com.duny.fcr.entity.Cheque;
import com.duny.fcr.entity.FromSal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FromSalRepo extends JpaRepository<FromSal,Long> {
    String GET_AMOUNT_BY_PID = "select amount from from_sal where payment_id=?";

    @Query(nativeQuery = true,value = GET_AMOUNT_BY_PID)
    Object getAmount(String pid);
}
