package com.duny.fcr.repo;

import com.duny.fcr.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CreditCardRepo extends JpaRepository<CreditCard,Long> {
    String GET_AMOUNT_BY_ST_ID = "select sum(amount) from credit_card where student_id=?";
    String DELETE_CC = "delete from credit_card where student_id=?";

    @Query(nativeQuery = true,value = GET_AMOUNT_BY_ST_ID)
    Object getAmount(String pid);

    @Modifying
    @Transactional
    @Query(value = DELETE_CC,nativeQuery = true)
    void deleteCreditCardByStudentId(String stId);

}
