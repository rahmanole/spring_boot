package com.duny.fcr.repo;

import com.duny.fcr.entity.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FeeRepo extends JpaRepository<Fee,Long> {
    String INSERT_SP_ID_FIN_TABLE = "update fin_details set sp_id=? where id=?";
    String INSERT_ST_ID_SP_TABLE = "update sponsor set st_id=? where id=?";

    String INSERT_DADD_TRUE_FIN_TABLE = "update fin_details set has_dadd=true where id=?";
    String INSERT_ST_ID_DADD_TABLE = "update dadd set st_id=? where id=?";

    @Modifying
    @Transactional
    @Query(value = INSERT_SP_ID_FIN_TABLE,nativeQuery = true)
    void inserSpID(int sp_id,int findtl_id);

    @Modifying
    @Transactional
    @Query(value = INSERT_ST_ID_SP_TABLE,nativeQuery = true)
    void inserStID(int st_id,int sp_id);

    @Modifying
    @Transactional
    @Query(value = INSERT_DADD_TRUE_FIN_TABLE,nativeQuery = true)
    void inserDaddIDInFin(int findtl_id);

    @Modifying
    @Transactional
    @Query(value = INSERT_ST_ID_DADD_TABLE,nativeQuery = true)
    void inserStIDInDadd(int st_id,int dadd_id);
}
