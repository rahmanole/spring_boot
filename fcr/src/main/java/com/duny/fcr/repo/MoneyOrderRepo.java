package com.duny.fcr.repo;

import com.duny.fcr.entity.CreditCard;
import com.duny.fcr.entity.MoneyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyOrderRepo extends JpaRepository<MoneyOrder,Long> {
}
