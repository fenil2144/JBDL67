package com.example.minorproject.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.minorproject.enums.AccountStatus;
import com.example.minorproject.models.Student;
import com.example.minorproject.repository.StudentRespositoryInterf;
import com.example.minorproject.requests.StudentCreateRequest;
import com.example.minorproject.service.StudentServiceInterf;

@Service
public class StudentServiceImpl implements StudentServiceInterf {

    @Autowired
    StudentRespositoryInterf studentRespositoryInterf;

    @Override
    public Student save(StudentCreateRequest studentCreateRequest) {
        return saveFromStudent(studentCreateRequest.toStudent());
    }

    public Student saveFromStudent(Student student) {
        return studentRespositoryInterf.save(student);
    }

    @Override
    public Student findById(int studentId) {
        return studentRespositoryInterf.findById(studentId).get();
    }

    @Override
    public Student findByIdAndAccountStatus(int studentId, AccountStatus accountStatus) {
        return studentRespositoryInterf.findByIdAndAccountStatus(studentId,accountStatus);
    }
}
