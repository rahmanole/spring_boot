package com.duny.fcr.repo;

import com.duny.fcr.entity.CreditCard;
import com.duny.fcr.entity.MoneyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MoneyOrderRepo extends JpaRepository<MoneyOrder,Long> {
    String GET_AMOUNT_BY_ST_ID = "select sum(amount) from money_order where student_id=?";

    @Query(nativeQuery = true,value = GET_AMOUNT_BY_ST_ID)
    Object getAmount(String pid);
}
