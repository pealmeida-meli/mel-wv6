package com.meli.obterdiploma.controller;

import com.meli.obterdiploma.model.StudentDTO;
import com.meli.obterdiploma.service.IStudentService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class StudentControllerTest {

    @InjectMocks
    private StudentController controller;

    @Mock
    IStudentService studentService;

    @BeforeEach
    void setup() {
        BDDMockito.when(studentService.create(ArgumentMatchers.any(StudentDTO.class)))
                .thenReturn(TestUtilsGenerator.getStudentWithId());

        BDDMockito.when(studentService.read(ArgumentMatchers.anyLong()))
                .thenReturn(TestUtilsGenerator.getStudentWithId());

        BDDMockito.doNothing().when(studentService).delete(ArgumentMatchers.anyLong());
    }

    @Test
    void registerStudent() {
        StudentDTO newStudent = TestUtilsGenerator.getNewStudentWithOneSubject();

        ResponseEntity<StudentDTO> response = controller.registerStudent(newStudent);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId())
                .isNotNull()
                .isPositive();
        verify(studentService, atLeastOnce()).create(newStudent);
    }

    @Test
    void getStudent() {
        StudentDTO studentDTO = TestUtilsGenerator.getStudentWithId();

        ResponseEntity<StudentDTO> response = controller.getStudent(studentDTO.getId());

        verify(studentService, atLeastOnce()).read(studentDTO.getId());
        assertThat(response.getBody().getId()).isEqualTo(studentDTO.getId());
    }

    @Test
    void removeStudent() {
        StudentDTO studentDTO = TestUtilsGenerator.getStudentWithId();

        ResponseEntity<Void> response = controller.removeStudent(studentDTO.getId());

        verify(studentService, atLeastOnce()).delete(studentDTO.getId());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}