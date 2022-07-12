package com.meli.obterdiploma.repository;

import com.meli.obterdiploma.model.StudentDTO;
import com.meli.obterdiploma.util.TestUtilsGenerator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudentDAOTest {

    private IStudentDAO studentDAO;

    @BeforeEach
    void setup() {
        studentDAO = new StudentDAO();
        TestUtilsGenerator.emptyUsersFile();
    }

    @Test
    void save_saveStudent_whenNewStudent() {
        StudentDTO newStudent = TestUtilsGenerator.getNewStudentWithOneSubject();

        StudentDTO savedStudent = studentDAO.save(newStudent);

        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getId().doubleValue()).isPositive();
        assertThat(savedStudent.getStudentName()).isEqualTo(newStudent.getStudentName());
    }

    @Test
    void save_updateStudent_whenStudentWithId() {
        StudentDTO newStudent = TestUtilsGenerator.getNewStudentWithOneSubject();

        StudentDTO savedStudent = studentDAO.save(newStudent);

        savedStudent.setStudentName("Novo nome");
        savedStudent.getSubjects().get(0).setName("Nova disciplina");

        StudentDTO updatedStudent = studentDAO.save(savedStudent);

        assertThat(updatedStudent).isNotNull();
        assertThat(updatedStudent.getId()).isEqualTo(savedStudent.getId());
        assertThat(updatedStudent.getStudentName()).isEqualTo(savedStudent.getStudentName());
        assertThat(updatedStudent.getSubjects().get(0).getName())
                .isEqualTo(savedStudent.getSubjects().get(0).getName());
    }

    @Test
    void delete() {
    }

    @Test
    void exists() {
    }

    @Test
    void findById() {
    }
}