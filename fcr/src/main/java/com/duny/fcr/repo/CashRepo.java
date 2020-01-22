package com.duny.fcr.repo;

import com.duny.fcr.entity.Cash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.OptionalDouble;

@Repository
public interface CashRepo extends JpaRepository<Cash,Long> {
    String GET_AMOUNT_BY_PID = "select sum(amount) from cash where payment_id=?";

    @Query(nativeQuery = true,value = GET_AMOUNT_BY_PID)
    Object getAmount(String pid);
}
