package com.meli.obterdiploma.repository;

import com.meli.obterdiploma.exception.StudentNotFoundException;
import com.meli.obterdiploma.model.StudentDTO;
import com.meli.obterdiploma.util.TestUtilsGenerator;
import org.junit.jupiter.api.*;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class StudentDAOTest {

    private IStudentDAO studentDAO;

//    @AfterAll
//    public static void tearDown() {
//        TestUtilsGenerator.emptyUsersFile();
//    }

    @BeforeEach @AfterEach
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
    void save_throwException_whenStudentWithIdAndNotExist() {
        StudentDTO student = TestUtilsGenerator.getStudentWithId();

        StudentNotFoundException exception = Assertions.assertThrows(StudentNotFoundException.class, () -> {
            StudentDTO savedStudent = studentDAO.save(student);
        });

        assertThat(exception.getError().getDescription()).contains(student.getId().toString());
        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void delete() {
    }

    @Test
    void exists_returnTrue_whenStudentExist() {
        StudentDTO newStudent = TestUtilsGenerator.getNewStudentWithOneSubject();
        StudentDTO savedStudent = studentDAO.save(newStudent);

        boolean found = studentDAO.exists(savedStudent);

        assertThat(found).isTrue();
    }

    @Test
    void exists_returnFalse_whenStudentNotExist() {
        StudentDTO student = TestUtilsGenerator.getStudentWithId();

        boolean found = studentDAO.exists(student);

        assertThat(found).isFalse();
    }

    @Test
    void findById_returnStudent_whenStudentExist() {
        StudentDTO newStudent = TestUtilsGenerator.getNewStudentWithOneSubject();
        StudentDTO savedStudent = studentDAO.save(newStudent);

        StudentDTO foundStudent = studentDAO.findById(savedStudent.getId());

        assertThat(foundStudent).isNotNull();
        assertThat(foundStudent.getId()).isEqualTo(savedStudent.getId());
        assertThat(foundStudent.getStudentName()).isEqualTo(savedStudent.getStudentName());
    }

    @Test
    void findById_throwException_whenStudentNotExist() {
        StudentDTO student = TestUtilsGenerator.getStudentWithId();

            StudentNotFoundException exception = Assertions.assertThrows(StudentNotFoundException.class, () -> {
                StudentDTO foundStudent = studentDAO.findById(student.getId());
            });

            assertThat(exception.getError().getDescription()).contains(student.getId().toString());
            assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}