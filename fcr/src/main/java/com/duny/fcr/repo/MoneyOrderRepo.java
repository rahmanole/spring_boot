package com.duny.fcr.repo;

import com.duny.fcr.entity.CreditCard;
import com.duny.fcr.entity.MoneyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MoneyOrderRepo extends JpaRepository<MoneyOrder,Long> {
    String GET_AMOUNT_BY_ST_ID = "select sum(amount) from money_order where student_id=? and year=?";
    String GET_TF = "select sum(amount) from money_order where student_id=? and year=? and month=?";
    String DELETE_MONEY_ORDER= "delete from money_order where student_id=? and year=?";

    @Query(nativeQuery = true,value = GET_AMOUNT_BY_ST_ID)
    Object getAmount(String pid,String year);

    @Query(nativeQuery = true,value = GET_TF)
    Object getAmount(String pid,String year,String month);
    
    @Modifying
    @Transactional
    @Query(value = DELETE_MONEY_ORDER,nativeQuery = true)
    void deleteMoneyOrderByStudentId(String stId,String year);

}
