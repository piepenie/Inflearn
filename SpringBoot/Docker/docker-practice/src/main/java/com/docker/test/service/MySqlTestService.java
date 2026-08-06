package com.docker.test.service;

import com.docker.test.dto.MySqlTestResponse;
import com.docker.test.entity.Student;
import com.docker.test.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MySqlTestService {

    private final StudentRepository studentRepository;

    @Transactional
    public MySqlTestResponse save(String name) {
        Student student = studentRepository.save(new Student(name));
        return new MySqlTestResponse(student.getId(), student.getName());
    }

    @Transactional(readOnly = true)
    public MySqlTestResponse get(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Student not found: " + studentId));
        return new MySqlTestResponse(student.getId(), student.getName());
    }
}
