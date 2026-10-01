package com.owaizz.studentapi.security;

import com.owaizz.studentapi.controller.StudentController;
import com.owaizz.studentapi.service.StudentService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService service;


    // =========================================================
    // 1. NO AUTHENTICATION
    // Expected: 401 Unauthorized
    // =========================================================

    @Test
    void deleteStudent_shouldReturn401_whenUserIsNotAuthenticated()
            throws Exception {

        mockMvc.perform(
                        delete("/students/1")
                )
                .andExpect(status().isUnauthorized());
    }


    // =========================================================
    // 2. NORMAL USER
    // Expected: 403 Forbidden
    // =========================================================

    @Test
    void deleteStudent_shouldReturn403_forNormalUser()
            throws Exception {

        mockMvc.perform(
                        delete("/students/1")
                                .with(
                                        user("owaiz")
                                                .roles("USER")
                                )
                )
                .andExpect(status().isForbidden());
    }


    // =========================================================
    // 3. ADMIN
    // Expected: 404 because student 1 does not exist
    // IMPORTANT:
    // 404 here means ADMIN successfully passed security.
    // =========================================================

    @Test
    void deleteStudent_shouldAllowAdmin()
            throws Exception {

        when(service.studentExistanceById(1))
                .thenReturn(false);

        mockMvc.perform(
                        delete("/students/1")
                                .with(
                                        user("admin")
                                                .roles("ADMIN")
                                )
                )
                .andExpect(status().isNotFound());
    }


    // =========================================================
    // 4. AUTHENTICATED USER
    // Expected: 200 OK
    // =========================================================

    @Test
    void authenticatedUser_shouldBeAllowedToAccessStudentApi()
            throws Exception {

        when(service.getAllStudents())
                .thenReturn(Collections.emptyList());

        mockMvc.perform(
                        get("/students")
                                .with(
                                        user("owaiz")
                                                .roles("USER")
                                )
                )
                .andExpect(status().isOk());
    }
}