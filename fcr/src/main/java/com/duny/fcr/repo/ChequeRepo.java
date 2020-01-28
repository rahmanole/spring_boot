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
    String GET_AMOUNT_BY_ST_ID = "select sum(amount) from cheque where student_id=?";
    String DELETE_CHEQUE = "delete from cheque where student_id=?";
    String GET_ALL_CHEQUE = "select * from cheque where student_id=?";

    @Query(nativeQuery = true,value = GET_AMOUNT_BY_ST_ID)
    Object getAmount(String pid);

    @Modifying
    @Transactional
    @Query(value = DELETE_CHEQUE,nativeQuery = true)
    void deleteChequeByStudentId(String stId);

    @Query(value = GET_ALL_CHEQUE,nativeQuery = true)
    List<Cheque> findChequeByStudentId(String stId);

}
