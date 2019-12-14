package com.duny.fcr.repo;

import com.duny.fcr.entity.AdmissionPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TuitionFeePaymentRepo extends JpaRepository<AdmissionPayment,Long> {
    String GET_MAX_ID = "select max(id) from tuition_fee_payment";

    @Query(value = GET_MAX_ID,nativeQuery = true)
    int getMaxId();
}
