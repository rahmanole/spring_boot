package com.duny.fcr.repo;

import com.duny.fcr.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepo extends JpaRepository<CreditCard,Long> {
}
