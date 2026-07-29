package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
class AuthFlowTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void issuedTokenCanBeUsedForAuthentication() throws Exception {
        MvcResult signinResult = mockMvc.perform(post("/auth/signin"))
                .andExpect(status().isOk())
                .andReturn();

        String token = signinResult.getResponse().getContentAsString();

        mockMvc.perform(get("/auth/test")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string("authUser.getId(): 1"));
    }

    @Test
    void requestWithoutTokenIsRejected() throws Exception {
        mockMvc.perform(get("/auth/test"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Authorization 헤더에 JWT가 필요합니다."));
    }

    @Test
    void invalidTokenIsRejected() throws Exception {
        mockMvc.perform(get("/auth/test")
                        .header("Authorization", "Bearer invalid-token"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("유효하지 않거나 만료된 JWT입니다."));
    }
}
