package com.devstack.lms.LMS.repo;

import com.devstack.lms.LMS.entity.Student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface StudentRepo   extends JpaRepository<Student, String> {

 public Page<Student> findAllByNameContaining(String searchText, Pageable pageable);
 public long countAllByNameContaining(String searchText);


}
