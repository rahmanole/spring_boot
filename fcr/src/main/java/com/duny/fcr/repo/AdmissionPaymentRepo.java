package com.duny.fcr.repo;

import com.duny.fcr.entity.AdmissionPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdmissionPaymentRepo extends JpaRepository<AdmissionPayment,Long> {
    String GET_MAX_ID = "select max(id) from admission_payment";
    String INSERT_ACADEMIC_FEE = "update fin_details set academic_fee='NA' where id=?";
    String INSERT_BOOK_FEE = "update fin_details set book_fee='NA' where id=?";
    String INSERT_MEAL_FEE = "update fin_details set meal_fee='NA' where id=?";
    String GET_PAYMENT_ID = "select * from admission_payment where student_id=? and year=?";
    String GET_ALL_PAYMENTS = "select * from admission_payment";
    String DELETE_PAYMENTS = "delete from admission_payment where student_id=?";

    @Query(value = GET_MAX_ID,nativeQuery = true)
    int getMaxId();

    @Modifying
    @Transactional
    @Query(value = INSERT_ACADEMIC_FEE,nativeQuery = true)
    void updatingAcademicee(long id);

    @Modifying
    @Transactional
    @Query(value = INSERT_MEAL_FEE,nativeQuery = true)
    void updatingMealFee(long id);

//    @Modifying
//    @Transactional
//    @Query(value = INSERT_BOOK_FEE,nativeQuery = true)
//    void updatingBookFee(long id);
//
//    @Query(value = GET_PAYMENT_ID,nativeQuery = true)
//    String getAFPaymentID(String studentId);

    AdmissionPayment getAdmissionPaymentByStudentIdAndYear(String stId,String year);

    @Modifying
    @Transactional
    @Query(value = DELETE_PAYMENTS,nativeQuery = true)
    void deleteAFPaymentByStudentId(String stId);

}
