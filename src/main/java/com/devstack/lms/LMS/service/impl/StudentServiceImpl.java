package com.devstack.lms.LMS.service.impl;

import com.devstack.lms.LMS.dto.request.RequestStudentDto;
import com.devstack.lms.LMS.dto.response.ResponseStudentDto;
import com.devstack.lms.LMS.dto.response.paginate.ResponseStudentPaginateDto;
import com.devstack.lms.LMS.entity.Student;
import com.devstack.lms.LMS.exception.EntryNotFoundException;
import com.devstack.lms.LMS.repo.StudentRepo;
import com.devstack.lms.LMS.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;

    @Override
    public void createStudent(RequestStudentDto dto) {
        studentRepo.save(toStudent(dto));
    }


    @Override
    public void updateStudent(String id, RequestStudentDto dto) {
        Student selectedStudent =
                studentRepo.findById(id).orElseThrow(()->new EntryNotFoundException("Student not found"));
        selectedStudent.setName(dto.getName());
        selectedStudent.setAddress(dto.getAddress());
        selectedStudent.setContact(dto.getContact());
        studentRepo.save(selectedStudent);
    }


    @Override
    public List<ResponseStudentDto> loadAllStudents() {
        /*List<Student> all = studentRepo.findAll();
        List<ResponseStudentDto> studentDtos = new ArrayList<>();

        for (Student student : all) {
            studentDtos.add(toResponseStudentDto(student));
        }

        return studentDtos;*/
        return studentRepo.findAll().stream().map(this::toResponseStudentDto).toList();
    }

    @Override
    public ResponseStudentDto findById(String id) {
        Student selectedStudent =
                studentRepo.findById(id).orElseThrow(()->new EntryNotFoundException("Student not found"));
        return toResponseStudentDto(selectedStudent);
    }

    @Override
    public void deleteById(String id) {
        Student selectedStudent =
                studentRepo.findById(id).orElseThrow(()->new EntryNotFoundException("Student not found"));
        studentRepo.delete(selectedStudent);
    }

    @Override
    public ResponseStudentPaginateDto search(String searchText, int page, int size) {
        return ResponseStudentPaginateDto
                .builder()
                .dataList(
                        studentRepo.findAllByNameContaining(searchText, PageRequest.of(page, size))
                                .stream().map(this::toResponseStudentDto).toList()
                )
                .count(studentRepo.countAllByNameContaining(searchText))
                .build();
    }





    private ResponseStudentDto toResponseStudentDto(Student student) {
        if (student == null) {
            return null;
        }
        return ResponseStudentDto.builder()
                .id(student.getId())
                .name(student.getName())
                .address(student.getAddress())
                .contact(student.getContact())
                .build();
    }

    private Student toStudent(RequestStudentDto dto) {
        return Student.builder()
                .name(dto.getName())
                .contact(dto.getContact())
                .address(dto.getAddress())
                .build();
    }

}
