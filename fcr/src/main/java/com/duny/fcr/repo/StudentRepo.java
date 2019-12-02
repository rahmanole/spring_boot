package com.duny.fcr.repo;

import com.duny.fcr.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
    String query_for_application_id = "select max(application_id) from Student";
    String query_for_STUDNET_id = "select max(student_id) from Student";
    String GET_ALL_ST_ID = "select student_id from Student";
    String GET_ALL_PENDING_ST = "select * from Student where status='applied'";

    @Query(nativeQuery = true,value = query_for_application_id)
    int getMaxApplicationId();

    @Query(nativeQuery = true,value = GET_ALL_ST_ID)
    List<String> getStudentIds();

    @Query(nativeQuery = true,value = query_for_STUDNET_id)
    String getMaxStudentId();

    @Query(nativeQuery = true,value = GET_ALL_PENDING_ST)
    List<Student> getPendingStudents();
}
