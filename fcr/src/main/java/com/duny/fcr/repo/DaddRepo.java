package com.duny.fcr.repo;

import com.duny.fcr.entity.Dadd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaddRepo extends JpaRepository<Dadd,Long> {
    String GET_DADD_NAMES = "select name from dadd";

    @Query(value = GET_DADD_NAMES,nativeQuery = true)
    List<String> getDaddNames();

    Dadd findDaddByName(String name);
}
