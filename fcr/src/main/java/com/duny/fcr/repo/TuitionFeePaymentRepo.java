package com.duny.fcr.repo;

import com.duny.fcr.entity.AdmissionPayment;
import com.duny.fcr.entity.TuitionFeePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TuitionFeePaymentRepo extends JpaRepository<TuitionFeePayment,Long> {
    String GET_MAX_ID = "select max(id) from tuition_fee_payment";
    String GET_STUDENT_WITH_DUE = "select st.student_id,st.father_name,st.dob,sum(tp.due) from tuition_fee_payment";

    @Query(value = GET_MAX_ID,nativeQuery = true)
    int getMaxId();
}
