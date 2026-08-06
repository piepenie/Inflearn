package com.docker.test.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.docker.test.dto.MySqlTestResponse;
import com.docker.test.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MySqlTestServiceIntegrationTest {

    @Autowired
    private MySqlTestService mySqlTestService;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void mysqlCanSaveAndReadStudent() {
        MySqlTestResponse response = mySqlTestService.save("mysql-student");

        assertThat(studentRepository.findById(response.getStudentId())).isPresent();
    }
}
