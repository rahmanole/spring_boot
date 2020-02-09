package com.duny.fcr.repo;

import com.duny.fcr.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CreditCardRepo extends JpaRepository<CreditCard,Long> {
    String GET_AMOUNT_BY_ST_ID = "select sum(amount) from credit_card where student_id=? and year=? and payment_id like 'AF%'";
    String GET_TF= "select sum(amount) from credit_card where payment_id=?";
    String GET_ALL_AF_CC = "select * from credit_card where student_id=? and payment_id like 'AF%'";
    String GET_ALL_TF_CC = "select * from credit_card where student_id=? and payment_id like 'TF%'";

    @Query(nativeQuery = true,value = GET_AMOUNT_BY_ST_ID)
    Object getAmount(String pid,String year);

    @Query(nativeQuery = true,value = GET_TF)
    Object getAmount(String pid);


    @Transactional
    void deleteCreditCardByPaymentId(String pid);

    List<CreditCard> findAllByPaymentId(String pid);

}
