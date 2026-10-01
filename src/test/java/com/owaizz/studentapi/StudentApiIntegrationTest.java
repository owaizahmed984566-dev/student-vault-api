package com.owaizz.studentapi;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class StudentApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    // --------------------------------------------------
    // 1. APPLICATION CONTEXT
    // --------------------------------------------------

    @Test
    void applicationContext_shouldLoad() {

        // If the Spring application context cannot start,
        // this test automatically fails.
    }


    // --------------------------------------------------
    // 2. PROTECTED ENDPOINT WITHOUT JWT
    // --------------------------------------------------

    @Test
    void studentsEndpoint_shouldReturn401_withoutAuthentication()
            throws Exception {

        mockMvc.perform(
                        get("/students")
                )
                .andExpect(status().isUnauthorized());
    }


    // --------------------------------------------------
    // 3. PUBLIC LOGIN ENDPOINT
    // --------------------------------------------------

    @Test
    void loginEndpoint_shouldNotReturn401()
            throws Exception {

        mockMvc.perform(
                        org.springframework.test.web.servlet.request
                                .MockMvcRequestBuilders
                                .post("/auth/login")
                                .contentType(
                                        org.springframework.http.MediaType.APPLICATION_JSON
                                )
                                .content("""
                                        {
                                            "username": "test",
                                            "password": "wrong"
                                        }
                                        """)
                )
                .andExpect(
                        result ->
                                org.junit.jupiter.api.Assertions.assertNotEquals(
                                        401,
                                        result.getResponse().getStatus()
                                )
                );
    }
}
