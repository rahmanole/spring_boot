package com.duny.fcr.repo;

import com.duny.fcr.entity.Cheque;
import com.duny.fcr.entity.FromSal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FromSalRepo extends JpaRepository<FromSal,Long> {
    String GET_AMOUNT_BY_ST_ID = "select sum(amount) from from_sal where student_id=? and year=? and payment_id like 'AF%'";
    String GET_TF = "select sum(amount) from from_sal where payment_id=?";
    String DELETE_FROM_SAL= "delete from from_sal where student_id=? and year=?";

    @Query(nativeQuery = true,value = GET_AMOUNT_BY_ST_ID)
    Object getAmount(String pid,String year);

    @Query(nativeQuery = true,value = GET_TF)
    Object getAmount(String pid);


    @Transactional
    void deleteFromSalsByPaymentId(String pid);

    List<FromSal> findAllByPaymentId(String tfPaymentId);

}
