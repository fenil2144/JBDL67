package com.example.minorproject.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.minorproject.enums.AccountStatus;
import com.example.minorproject.models.MyUser;
import com.example.minorproject.models.Student;
import com.example.minorproject.repository.StudentRespositoryInterf;
import com.example.minorproject.requests.StudentCreateRequest;
import com.example.minorproject.requests.UserCreateRequest;
import com.example.minorproject.service.StudentServiceInterf;

@Service
public class StudentServiceImpl implements StudentServiceInterf {

    @Autowired
    StudentRespositoryInterf studentRespositoryInterf;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MyUserDetailsService myUserDetailsService;

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
    public List<Student> findAllById(List<Integer> studentIds) {
        return studentRespositoryInterf.findAllById(studentIds);
    }

    @Override
    public Student findByIdAndAccountStatus(int studentId, AccountStatus accountStatus) {
        return studentRespositoryInterf.findByIdAndAccountStatus(studentId,accountStatus);
    }

    public void create(StudentCreateRequest studentCreateRequest){
        UserCreateRequest userCreateRequest = studentCreateRequest.toUser();
        MyUser myUser = myUserDetailsService.createUser(userCreateRequest);

        Student student = studentCreateRequest.toStudent();
        student.setMyUser(myUser);
        studentRespositoryInterf.save(student);

    }
}
