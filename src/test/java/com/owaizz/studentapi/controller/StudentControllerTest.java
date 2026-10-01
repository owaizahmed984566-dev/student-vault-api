package com.owaizz.studentapi.controller;

import com.owaizz.studentapi.dto.StudentResponse;
import com.owaizz.studentapi.security.JwtAuthenticationFilter;
import com.owaizz.studentapi.service.StudentService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(
        controllers = StudentController.class,
        excludeFilters = @ComponentScan.Filter(
                type = org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE,
                classes = JwtAuthenticationFilter.class
        )
)
@AutoConfigureMockMvc(addFilters = false)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService service;


    // --------------------------------------------------
    // 1. GET ALL STUDENTS
    // --------------------------------------------------

    @Test
    void getAllStudents_shouldReturn200() throws Exception {

        StudentResponse student = new StudentResponse(
                1,
                "Owaiz",
                "Java",
                90
        );

        when(service.getAllStudents())
                .thenReturn(List.of(student));

        mockMvc.perform(
                        get("/students")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Owaiz"))
                .andExpect(jsonPath("$[0].subject").value("Java"))
                .andExpect(jsonPath("$[0].marks").value(90));
    }


    // --------------------------------------------------
    // 2. GET STUDENT BY ID - SUCCESS
    // --------------------------------------------------

    @Test
    void getStudent_shouldReturn200() throws Exception {

        StudentResponse student = new StudentResponse(
                1,
                "Owaiz",
                "Java",
                90
        );

        when(service.getStudent(1))
                .thenReturn(student);

        mockMvc.perform(
                        get("/students/1")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Owaiz"))
                .andExpect(jsonPath("$.subject").value("Java"))
                .andExpect(jsonPath("$.marks").value(90));
    }


    // --------------------------------------------------
    // 3. POST STUDENT - SUCCESS
    // --------------------------------------------------

    @Test
    void addStudent_shouldReturn201() throws Exception {

        StudentResponse response = new StudentResponse(
                1,
                "Owaiz",
                "Java",
                90
        );

        when(service.studentExistanceById(1))
                .thenReturn(false);

        when(service.addStudent(any()))
                .thenReturn(response);

        mockMvc.perform(
                        post("/students")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "id": 1,
                                            "name": "Owaiz",
                                            "subject": "Java",
                                            "marks": 90
                                        }
                                        """)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Owaiz"))
                .andExpect(jsonPath("$.subject").value("Java"))
                .andExpect(jsonPath("$.marks").value(90));
    }


    // --------------------------------------------------
    // 4. POST STUDENT - ALREADY EXISTS
    // --------------------------------------------------

    @Test
    void addStudent_shouldReturn409_whenStudentAlreadyExists()
            throws Exception {

        when(service.studentExistanceById(1))
                .thenReturn(true);

        mockMvc.perform(
                        post("/students")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "id": 1,
                                            "name": "Owaiz",
                                            "subject": "Java",
                                            "marks": 90
                                        }
                                        """)
                )
                .andExpect(status().isConflict())
                .andExpect(
                        jsonPath("$.message")
                                .value("stundet already exist")
                );
    }


    // --------------------------------------------------
    // 5. POST /students/{id} - BAD REQUEST
    // --------------------------------------------------

    @Test
    void badResponseId_shouldReturn400() throws Exception {

        mockMvc.perform(
                        post("/students/10")
                )
                .andExpect(status().isBadRequest())
                .andExpect(
                        jsonPath("$.message")
                                .value("id : 10 should not be mentioned")
                );
    }


    // --------------------------------------------------
    // 6. PUT STUDENT - SUCCESS
    // --------------------------------------------------

    @Test
    void updateStudent_shouldReturn200() throws Exception {

        StudentResponse response = new StudentResponse(
                1,
                "Owaiz Updated",
                "Spring Boot",
                95
        );

        when(service.studentExistanceById(1))
                .thenReturn(true);

        when(service.updateStudent(any(Integer.class), any()))
                .thenReturn(response);

        mockMvc.perform(
                        put("/students/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "name": "Owaiz Updated",
                                            "subject": "Spring Boot",
                                            "marks": 95
                                        }
                                        """)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Owaiz Updated"))
                .andExpect(jsonPath("$.subject").value("Spring Boot"))
                .andExpect(jsonPath("$.marks").value(95));
    }


    // --------------------------------------------------
    // 7. PUT STUDENT - NOT FOUND
    // --------------------------------------------------

    @Test
    void updateStudent_shouldReturn404_whenStudentNotFound()
            throws Exception {

        when(service.studentExistanceById(99))
                .thenReturn(false);

        mockMvc.perform(
                        put("/students/99")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "name": "Owaiz",
                                            "subject": "Java",
                                            "marks": 90
                                        }
                                        """)
                )
                .andExpect(status().isNotFound())
                .andExpect(
                        jsonPath("$.message")
                                .value("stundet not found")
                );
    }


    // --------------------------------------------------
    // 8. PUT /students - BAD REQUEST
    // --------------------------------------------------

    @Test
    void badResponse_shouldReturn400() throws Exception {

        mockMvc.perform(
                        put("/students")
                )
                .andExpect(status().isBadRequest())
                .andExpect(
                        jsonPath("$.message")
                                .value("student not mentioned")
                );
    }


    // --------------------------------------------------
    // 9. PATCH STUDENT - SUCCESS
    // --------------------------------------------------

    @Test
    void patchStudent_shouldReturn200() throws Exception {

        StudentResponse response = new StudentResponse(
                1,
                "Owaiz",
                "Java",
                95
        );

        when(service.patchStudent(any(Integer.class), any()))
                .thenReturn(response);

        mockMvc.perform(
                        patch("/students/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "name": "Owaiz",
                                            "marks": 95
                                        }
                                        """)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Owaiz"))
                .andExpect(jsonPath("$.subject").value("Java"))
                .andExpect(jsonPath("$.marks").value(95));
    }


    // --------------------------------------------------
    // 10. PATCH /students - BAD REQUEST
    // --------------------------------------------------

    @Test
    void badRequest_shouldReturn400() throws Exception {

        mockMvc.perform(
                        patch("/students")
                )
                .andExpect(status().isBadRequest())
                .andExpect(
                        jsonPath("$.message")
                                .value("student not mentioned")
                );
    }


    // --------------------------------------------------
    // 11. DELETE STUDENT - SUCCESS
    // --------------------------------------------------

    @Test
    void deleteStudent_shouldReturn200() throws Exception {

        when(service.studentExistanceById(1))
                .thenReturn(true);

        when(service.deleteStudent(1))
                .thenReturn(true);

        mockMvc.perform(
                        delete("/students/1")
                )
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.message")
                                .value("deletion successfull")
                );
    }


    // --------------------------------------------------
    // 12. DELETE STUDENT - NOT FOUND
    // --------------------------------------------------

    @Test
    void deleteStudent_shouldReturn404_whenStudentNotFound()
            throws Exception {

        when(service.studentExistanceById(99))
                .thenReturn(false);

        mockMvc.perform(
                        delete("/students/99")
                )
                .andExpect(status().isNotFound())
                .andExpect(
                        jsonPath("$.message")
                                .value("student not found")
                );
    }


    // --------------------------------------------------
    // 13. DELETE STUDENT - DELETE FAILED
    // --------------------------------------------------

    @Test
    void deleteStudent_shouldReturn500_whenDeleteFails()
            throws Exception {

        when(service.studentExistanceById(1))
                .thenReturn(true);

        when(service.deleteStudent(1))
                .thenReturn(false);

        mockMvc.perform(
                        delete("/students/1")
                )
                .andExpect(status().isInternalServerError())
                .andExpect(
                        jsonPath("$.message")
                                .value("deletion failed")
                );
    }


    // --------------------------------------------------
    // 14. DELETE /students - BAD REQUEST
    // --------------------------------------------------

    @Test
    void deleteStudentWithoutId_shouldReturn400()
            throws Exception {

        mockMvc.perform(
                        delete("/students")
                )
                .andExpect(status().isBadRequest())
                .andExpect(
                        jsonPath("$.message")
                                .value("student not mentioned")
                );
    }
}