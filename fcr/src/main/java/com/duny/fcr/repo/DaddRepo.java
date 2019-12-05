package com.duny.fcr.repo;

import com.duny.fcr.entity.Dadd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DaddRepo extends JpaRepository<Dadd,Long> {
    String GET_DADD_NAMES = "select name from dadd";
    String COUNT_ALL_DADD_FOR_ST = "select count(*) from dadd where st_id=?";
    String GET_ALL_DADD_FOR_ST = "select * from dadd where st_id=?";


    String INSERT_DADD_TRUE_FIN_TABLE = "update fin_details set has_dadd=true where id=?";
    String INSERT_ST_ID_DADD_TABLE = "update dadd set st_id=? where id=?";

    //For removing dadd
    String INSERT_DADD_FALSE_FIN_TABLE = "update fin_details set has_dadd=false where id=?";
    String REMOVE_DADD_FROM_DADD_TABLE = "update dadd set st_id=0 where id=?";


    @Query(value = GET_DADD_NAMES,nativeQuery = true)
    List<String> getDaddNames();

    Dadd findDaddByName(String name);

    @Query(nativeQuery = true,value = COUNT_ALL_DADD_FOR_ST)
    int getTotalDaddsOfSt(int id);

    @Query(nativeQuery = true,value = GET_ALL_DADD_FOR_ST)
    List<Dadd> findAllBySt_id(int st_id);

    @Modifying
    @Transactional
    @Query(value = INSERT_DADD_TRUE_FIN_TABLE,nativeQuery = true)
    void inserDaddIDInFin(int findtl_id);

    @Modifying
    @Transactional
    @Query(value = INSERT_ST_ID_DADD_TABLE,nativeQuery = true)
    void inserStIDInDadd(int st_id,int dadd_id);

    @Modifying
    @Transactional
    @Query(value = REMOVE_DADD_FROM_DADD_TABLE,nativeQuery = true)
    void removeStudentFromDadd(int dadd_id);

    @Modifying
    @Transactional
    @Query(value = INSERT_DADD_FALSE_FIN_TABLE,nativeQuery = true)
    void removeDaddFromFinDtl(int fin_id);




}
