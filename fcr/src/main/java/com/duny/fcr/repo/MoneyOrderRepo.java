package com.duny.fcr.repo;

import com.duny.fcr.entity.CreditCard;
import com.duny.fcr.entity.MoneyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MoneyOrderRepo extends JpaRepository<MoneyOrder,Long> {
    String GET_AMOUNT_BY_ST_ID = "select sum(amount) from money_order where student_id=? and year=? and payment_id like 'AF%'";
    String GET_TF = "select sum(amount) from money_order where payment_id=?";
    String DELETE_MONEY_ORDER= "delete from money_order where student_id=? and year=?";

    @Query(nativeQuery = true,value = GET_AMOUNT_BY_ST_ID)
    Object getAmount(String pid,String year);

    @Query(nativeQuery = true,value = GET_TF)
    Object getAmount(String pid);
    

    @Transactional
    void deleteMoneyOrderByPaymentId(String pid);

    List<MoneyOrder> findAllByPaymentId(String tfPaymentId);

}
