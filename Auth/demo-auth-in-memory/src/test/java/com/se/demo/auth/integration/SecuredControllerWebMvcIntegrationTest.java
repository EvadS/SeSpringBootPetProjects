package com.se.demo.auth.integration;


import com.se.demo.auth.controller.SecuredController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;



// part 3

/**
 * @WebMvcTest annotation approach with Spring Security, MockMvc is automatically configured with the necessary filter chain
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SecuredController.class)
public class SecuredControllerWebMvcIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void givenRequestOnPrivateService_shouldFailWith401() throws Exception {
        mvc.perform(get("/private/hello")
                        .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isUnauthorized());
    }


    /**
     * we're able to use @WithMockUser
     * @throws Exception
     */
    @WithMockUser(value = "spring")
    @Test
    public void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
        mvc.perform(get("/private/hello")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

}
