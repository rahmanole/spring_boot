package com.duny.fcr.repo;

import com.duny.fcr.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CreditCardRepo extends JpaRepository<CreditCard,Long> {
    String GET_AMOUNT_BY_PID = "select amount from credit_card where payment_id=?";

    @Query(nativeQuery = true,value = GET_AMOUNT_BY_PID)
    Object getAmount(String pid);
}
