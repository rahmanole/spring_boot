package com.duny.fcr.repo;

import com.duny.fcr.entity.Cheque;
import com.duny.fcr.entity.FromSal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FromSalRepo extends JpaRepository<FromSal,Long> {
}
