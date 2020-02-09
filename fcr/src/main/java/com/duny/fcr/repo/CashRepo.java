package com.duny.fcr.repo;

import com.duny.fcr.entity.Cash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.OptionalDouble;

@Repository
public interface CashRepo extends JpaRepository<Cash,Long> {
    String GET_AMOUNT_BY_ST_ID = "select sum(amount) from cash where student_id=? and year=? and payment_id like 'AF%'";
    String GET_TF= "select sum(amount) from cash where payment_id=?";
    String DELETE_CASH = "delete from cash where student_id=? and year=?";
    String GET_ALL_AF_CASH = "select * from cash where student_id=? and year=? and payment_id like 'AF%'";
    String GET_ALL_TF_CASH = "select * from cash where student_id=? and payment_id =?'";


    @Query(nativeQuery = true,value = GET_AMOUNT_BY_ST_ID)
    Object getAmount(String pid,String year);

    @Query(nativeQuery = true,value = GET_TF)
    Object getAmount(String pid);


    @Transactional
    void deleteCashByPaymentId(String pid);

    @Query(value = GET_ALL_AF_CASH,nativeQuery = true)
    List<Cash> findAllAFCashes(String stId,String year);


    List<Cash> findAllByPaymentId(String tfPaymentId);
}
