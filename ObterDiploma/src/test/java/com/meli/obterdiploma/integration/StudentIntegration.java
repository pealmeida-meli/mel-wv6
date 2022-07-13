package com.meli.obterdiploma.integration;

import com.meli.obterdiploma.model.StudentDTO;
import com.meli.obterdiploma.repository.StudentDAO;
import com.meli.obterdiploma.util.TestUtilsGenerator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentIntegration {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @BeforeEach
    public void setup(){
        TestUtilsGenerator.emptyUsersFile();
    }

    @Test
    public void registerStudent_saveStudent_whenNewStudent() {
        StudentDTO newStudent = TestUtilsGenerator.getNewStudentWithOneSubject();
        String baseUrl = "http://localhost:" + port + "/student";
        HttpEntity<StudentDTO> httpEntity = new HttpEntity<>(newStudent);

        //exchage(URL, método Http, body, tipo de objeto)
        ResponseEntity<StudentDTO> retorno = testRestTemplate.exchange(baseUrl+"/registerStudent",
                HttpMethod.POST, httpEntity, StudentDTO.class);

        StudentDTO studentReturned = retorno.getBody();

        assertThat(retorno.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(studentReturned).isNotNull();
        assertThat(studentReturned.getId()).isPositive();
        assertThat(studentReturned.getStudentName()).isEqualTo(newStudent.getStudentName());
    }

    @Test
    public void registerStudent_returnBadRequest_whenStudentHasId() {
        StudentDTO newStudent = TestUtilsGenerator.getStudentWithId();
        String baseUrl = "http://localhost:" + port + "/student";
        HttpEntity<StudentDTO> httpEntity = new HttpEntity<>(newStudent);

        //exchage(URL, método Http, body, tipo de objeto)
        ResponseEntity<StudentDTO> retorno = testRestTemplate.exchange(baseUrl+"/registerStudent",
                HttpMethod.POST, httpEntity, StudentDTO.class);

        assertThat(retorno.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void getStudent_returnStatusNotFound_whenStudentNotExist() {
        StudentDTO student = TestUtilsGenerator.getStudentWithId();
        String baseUrl = "http://localhost:" + port + "/student";

        //exchage(URL, método Http, body, tipo de objeto)
        ResponseEntity<StudentDTO> retorno = testRestTemplate.exchange(baseUrl+"/getStudent/"+student.getId(),
                HttpMethod.GET, null, StudentDTO.class);

        assertThat(retorno.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

   @Test
    public void getStudent_returnStudent_whenStudentExist() {
        StudentDTO newStudent = TestUtilsGenerator.getNewStudentWithOneSubject();
        String baseUrl = "http://localhost:" + port + "/student/getStudent/";
        StudentDAO dao = new StudentDAO();
        StudentDTO studentSaved = dao.save(newStudent);

        String url = String.format(baseUrl+"%d", studentSaved.getId());

        ResponseEntity<StudentDTO> retorno = testRestTemplate.exchange(url,
                HttpMethod.GET, null, StudentDTO.class);

        assertThat(retorno.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(retorno.getBody().getId()).isEqualTo(studentSaved.getId());
        assertThat(retorno.getBody().getStudentName()).isEqualTo(studentSaved.getStudentName());
    }

}
