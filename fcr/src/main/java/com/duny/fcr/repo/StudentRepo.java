package com.duny.fcr.repo;

import com.duny.fcr.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepo extends JpaRepository<Student,Long> {
    String query_for_application_id = "select max(application_id) from Student";

    @Query(nativeQuery = true,value = query_for_application_id)
    int getMaxApplicationId();
}
