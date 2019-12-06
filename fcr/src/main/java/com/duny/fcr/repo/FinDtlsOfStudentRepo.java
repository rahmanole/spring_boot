package com.duny.fcr.repo;

import com.duny.fcr.entity.FinDtlsOfStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface FinDtlsOfStudentRepo extends JpaRepository<FinDtlsOfStudent,Long> {
    String REMOVE_SPONSOR = "update fin_details set sp_id = 0 where id=? ";
    String FIN_DETAILS_OF_ST = "select * from fin_details where st_id=?";
    String INSERT_COLLECTION = "update fin_details set collection = ? where id=?";
    String INSERT_ZAKAT = "update fin_details set zakat = ? where id=?";
    String INSERT_STAFF = "update fin_details set is_staff_child = true where id=?";
    String REMOVE_STAFF = "update fin_details set is_staff_child = false where id=?";
    String IS_SELF_FUNDED = "update fin_details set is_self_funded = true where id=?";
    String REMOVE_SELF_FUNDED = "update fin_details set is_self_funded = false where id=?";
    String INSERT_SIBLING = "update fin_details set sibling_ids=?,sibling_num=? where id=?";
    String INSERT_MAND_FEES = "update fin_details set mand_fees=? where id=?";

    @Query(value = FIN_DETAILS_OF_ST,nativeQuery = true)
    FinDtlsOfStudent getFinDetails(long id);

    @Modifying
    @Transactional
    @Query(value = REMOVE_SPONSOR,nativeQuery = true)
    void removeSponsor(long id);

    @Modifying
    @Transactional
    @Query(value = INSERT_COLLECTION,nativeQuery = true)
    void insertColletion(double collection,long id);

    @Modifying
    @Transactional
    @Query(value = INSERT_ZAKAT,nativeQuery = true)
    void insertZakat(double zakat,long id);

    @Modifying
    @Transactional
    @Query(value = INSERT_STAFF,nativeQuery = true)
    void insertStaff(long id);

    @Modifying
    @Transactional
    @Query(value = REMOVE_STAFF,nativeQuery = true)
    void removeStaff(long id);

    @Modifying
    @Transactional
    @Query(value = IS_SELF_FUNDED,nativeQuery = true)
    void insertSelfFunded(long id);

    @Modifying
    @Transactional
    @Query(value = REMOVE_SELF_FUNDED,nativeQuery = true)
    void removeSelfFunded(long id);

    @Modifying
    @Transactional
    @Query(value = INSERT_SIBLING,nativeQuery = true)
    void insertSibling(String siblingIds,int siblingNum,long id);

    @Modifying
    @Transactional
    @Query(value = INSERT_MAND_FEES,nativeQuery = true)
    void insertSibling(double mandFees,long id);



}
