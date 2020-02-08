package com.duny.fcr.repo;

import com.duny.fcr.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CreditCardRepo extends JpaRepository<CreditCard,Long> {
    String GET_AMOUNT_BY_ST_ID = "select sum(amount) from credit_card where student_id=? and year=?";
    String GET_TF= "select sum(amount) from credit_card where student_id=? and year=? and month=?";
    String DELETE_CC = "delete from credit_card where student_id=? and year=?";

    @Query(nativeQuery = true,value = GET_AMOUNT_BY_ST_ID)
    Object getAmount(String pid,String year);

    @Query(nativeQuery = true,value = GET_TF)
    Object getAmount(String pid,String year,String month);

    @Modifying
    @Transactional
    @Query(value = DELETE_CC,nativeQuery = true)
    void deleteCreditCardByStudentId(String stId,String year);

}
