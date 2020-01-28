package com.duny.fcr.repo;

import com.duny.fcr.entity.Zelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ZelleReo extends JpaRepository<Zelle,Long> {
    String GET_AMOUNT_BY_ST_ID = "select sum(amount) from zelle where student_id=?";
    String DELETE_ZELLE = "delete from zelle where student_id=?";

    @Query(nativeQuery = true,value = GET_AMOUNT_BY_ST_ID)
    Object getAmount(String pid);

    @Modifying
    @Transactional
    @Query(value = DELETE_ZELLE,nativeQuery = true)
    void deleteZelleByStudentId(String stId);
}
