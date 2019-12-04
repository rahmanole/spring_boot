package com.duny.fcr.repo;

import com.duny.fcr.entity.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SponsorRepo extends JpaRepository<Sponsor,Long> {
    String GET_SPONSOR_LIST = "select name from sponsor";
    String GET_SPONSOR_BY_ST_ID = "select * from sponsor where st_id = ?";
    String REMOVE_STUDNET_FROM_SPONSOR = "update sponsor set st_id=0 where id=?";

    String INSERT_SP_ID_FIN_TABLE = "update fin_details set sp_id=? where id=?";
    String INSERT_ST_ID_SP_TABLE = "update sponsor set st_id=? where id=?";




    @Modifying
    @Transactional
    @Query(value = INSERT_SP_ID_FIN_TABLE,nativeQuery = true)
    void inserSpID(int sp_id,int findtl_id);

    @Modifying
    @Transactional
    @Query(value = INSERT_ST_ID_SP_TABLE,nativeQuery = true)
    void inserStID(int st_id,int sp_id);

    @Query(nativeQuery = true,value = GET_SPONSOR_LIST)
    List<String> getSponsorNames();

    Sponsor findSponsorByName(String name);

    @Query(value = GET_SPONSOR_BY_ST_ID,nativeQuery = true)
    Sponsor getSponsorOfStudent(int st_id);

    @Modifying
    @Transactional
    @Query(value = REMOVE_STUDNET_FROM_SPONSOR,nativeQuery = true)
    void removeStudentFromSponsor(int sp_id);
}
