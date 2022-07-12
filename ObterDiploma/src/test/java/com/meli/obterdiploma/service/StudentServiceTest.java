package com.meli.obterdiploma.service;

import com.meli.obterdiploma.model.StudentDTO;
import com.meli.obterdiploma.repository.IStudentDAO;
import com.meli.obterdiploma.util.TestUtilsGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class StudentServiceTest {

    @InjectMocks
    StudentService service;

    @Mock
    IStudentDAO studentDAO;

    @BeforeEach
    public void setup(){
        BDDMockito.when(studentDAO.save(ArgumentMatchers.any(StudentDTO.class)))
                .thenReturn(TestUtilsGenerator.getStudentWithId());

        BDDMockito.when(studentDAO.findById(ArgumentMatchers.anyLong())).
                thenReturn(TestUtilsGenerator.getStudentWithId());

        BDDMockito.doNothing().when(studentDAO).delete(ArgumentMatchers.anyLong());
    }

    @Test
    void create_returnStudent_whenNewStudent() {
        StudentDTO newStudent = TestUtilsGenerator.getNewStudentWithOneSubject();

        StudentDTO savedStudent = service.create(newStudent);

        assertThat(savedStudent.getId()).isPositive();
        assertThat(savedStudent.getStudentName()).isEqualTo(savedStudent.getStudentName());
        verify(studentDAO, atLeastOnce()).save(newStudent);
    }

    @Test
    void read_returnStudante_whenStudentExist() {
        StudentDTO studentDTO = TestUtilsGenerator.getStudentWithId();

        StudentDTO studentFound = service.read(studentDTO.getId());

        assertThat(studentFound.getId()).isEqualTo(studentDTO.getId());
        verify(studentDAO, atLeastOnce()).findById(studentDTO.getId());
    }

    @Test
    void update() {
    }

    @Test
    void delete_deleteStudent_whenStudentExist() {
        StudentDTO studentDTO = TestUtilsGenerator.getStudentWithId();

        assertThatCode(()->{
            service.delete(studentDTO.getId());
        }).doesNotThrowAnyException();

        verify(studentDAO, atLeastOnce()).delete(studentDTO.getId());
    }
}