package com.devstack.lms.LMS.service;

import com.devstack.lms.LMS.dto.request.RequestStudentDto;
import com.devstack.lms.LMS.dto.response.ResponseStudentDto;
import com.devstack.lms.LMS.dto.response.paginate.ResponseStudentPaginateDto;

import java.util.List;

public interface StudentService {
     void createStudent(RequestStudentDto dto);
     void updateStudent(String id,RequestStudentDto dto);
     List<ResponseStudentDto> loadAllStudents();
     ResponseStudentDto findById(String id);
     void deleteById(String id);
     ResponseStudentPaginateDto search(String searchText, int page, int size);
}
