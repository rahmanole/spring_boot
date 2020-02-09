package com.duny.fcr.repo;

import com.duny.fcr.entity.Cash;
import com.duny.fcr.entity.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ChequeRepo extends JpaRepository<Cheque,Long> {
    String GET_AMOUNT_BY_ST_ID = "select sum(amount) from cheque where student_id=? and year=? and payment_id like 'AF%'";
    String GET_TF = "select sum(amount) from cheque where payment_id=?";
    String DELETE_CHEQUE = "delete from cheque where student_id=? and year=?";
    String GET_ALL_AF_CHEQUE = "select * from cheque where student_id=? and payment_id like 'AF%'";
    String GET_ALL_TF_CHEQUE = "select * from cheque where student_id=? and payment_id=?";

    @Query(nativeQuery = true,value = GET_AMOUNT_BY_ST_ID)
    Object getAmount(String pid,String year);

    @Query(nativeQuery = true,value = GET_TF)
    Object getAmount(String pid);


    @Transactional
    void deleteChequeByPaymentId(String paymentId);

    @Query(value = GET_ALL_AF_CHEQUE,nativeQuery = true)
    List<Cheque> findAllAFCheques(String stId,String year);


    List<Cheque> findAllByPaymentId(String tfPaymentId);

}
