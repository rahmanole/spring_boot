package com.duny.fcr.repo;

import com.duny.fcr.dto.ITuitionFeeDueReport;
import com.duny.fcr.entity.TuitionFeePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TuitionFeePaymentRepo extends JpaRepository<TuitionFeePayment,Long> {
    String GET_MAX_ID = "select max(id) from tuition_fee_payment";
    String GET_STUDENT_WITH_DUE = "select s.name as name,s.student_id as studentId,s.dob as dob,tf.total_dues as due from student s join\n" +
            " (select student_id,sum(tuition_fee_due) 'total_dues' from tuition_fee_payment where tuition_fee_due >=0 group by student_id) tf on\n" +
            "s.student_id = tf.student_id;";
    String DELETE_TUITION_FEE = "delete from  tuition_fee_payment where student_id=";

    @Query(value = GET_MAX_ID,nativeQuery = true)
    int getMaxId();

    @Query(value = GET_STUDENT_WITH_DUE,nativeQuery = true)
    List<ITuitionFeeDueReport> getTuitionFeeDueReport();

    TuitionFeePayment findTuitionFeePaymentByStudentIdAndMonthAndYear(String stId,String month,String year);

    @Transactional
    void deleteTuitionFeePaymentByTfPaymentId(String pid);

    TuitionFeePayment findTuitionFeePaymentByTfPaymentId(String pid);
}
