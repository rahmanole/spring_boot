package com.duny.fcr.repo;

import com.duny.fcr.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
    String query_for_application_id = "select max(application_id) from Student";
    String query_for_STUDNET_id = "select max(student_id) from Student";
    String GET_ALL_ST_ID = "select student_id from Student";
    String GET_ALL_PENDING_ST = "select * from student where status='applied'";
    String GET_STUDENT_BY_ST_ID = "select * from student where student_id=?";
    String INSERT_ADMITTED = "update student set status='admitted' where id=?";
    String UPDATE_COURSE= "update student set course_name=? where id=?";
    String UPDATE_RESIDENCE= "update student set boarding=? where id=?";

    @Query(nativeQuery = true,value = query_for_application_id)
    int getMaxApplicationId();

    @Query(nativeQuery = true,value = GET_ALL_ST_ID)
    List<String> getStudentIds();

    @Query(nativeQuery = true,value = query_for_STUDNET_id)
    String getMaxStudentId();

    @Query(nativeQuery = true,value = GET_ALL_PENDING_ST)
    List<Student> getPendingStudents();

    @Query(nativeQuery = true,value = GET_STUDENT_BY_ST_ID)
    List<Student> getStudentByStudentId(String st_id);

    @Modifying
    @Transactional
    @Query(value = INSERT_ADMITTED,nativeQuery = true)
    void admitStudent(long id);

    @Modifying
    @Transactional
    @Query(value = UPDATE_COURSE,nativeQuery = true)
    void updateCourse(String course,long id);

    @Modifying
    @Transactional
    @Query(value = UPDATE_RESIDENCE,nativeQuery = true)
    void updateBoarding(String boarding,long id);
}
