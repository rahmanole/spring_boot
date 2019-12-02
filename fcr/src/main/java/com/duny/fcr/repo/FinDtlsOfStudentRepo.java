package com.duny.fcr.repo;

import com.duny.fcr.entity.FinDtlsOfStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface FinDtlsOfStudentRepo extends JpaRepository<FinDtlsOfStudent,Long> {
    String REMOVE_SPONSOR = "update fin_details set sp_id = 0 where id=? ";
    String FIN_DETAILS_OF_ST = "select * from fin_details where st_id=?";

    @Query(value = FIN_DETAILS_OF_ST,nativeQuery = true)
    FinDtlsOfStudent getFinDetails(long id);

    @Modifying
    @Transactional
    @Query(value = REMOVE_SPONSOR,nativeQuery = true)
    void removeSponsor(long id);
}
