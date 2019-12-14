package com.duny.fcr.repo;

import com.duny.fcr.entity.Cash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashRepo extends JpaRepository<Cash,Long> {

}
