package com.se.sample.springboottetinggradle.controller;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.se.sample.springboottetinggradle.config.SecurityConfig;
import com.se.sample.springboottetinggradle.exception.ErrorResult;
import com.se.sample.springboottetinggradle.model.User;
import com.se.sample.springboottetinggradle.model.UserResource;
import com.se.sample.springboottetinggradle.sesrvice.RegisterUseCase;
import com.se.sample.springboottetinggradle.web.RegisterRestController;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Java6Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(
        value = RegisterRestController.class,
        excludeAutoConfiguration = SecurityConfig.class,
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = WebSecurityConfigurer.class))
@AutoConfigureMockMvc(addFilters = false)

//@WebMvcTest(controllers = RegisterRestController.class)
public class RegisterRestControllerTest {

    // to mock away the business logic
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RegisterUseCase registerUseCase;


    /**
     * Verifying HTTP Request Matching
     * @throws Exception
     */
    @Test
    void whenValidUrlAndMethodAndContentType_thenReturns200() throws Exception {

        UserResource user = new UserResource("Zaphod", "zaphod@galaxy.net");

        mockMvc.perform(post("/forums/42/register")
                        .param("sendWelcomeMail", "true")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType("application/json"))
                .andExpect(status().isOk());

    }


    /**
     * Verifying Input Deserialization
     * @throws Exception
     */
    @Test
    void whenValidInput_thenReturns200() throws Exception {
        UserResource user = new UserResource("Zaphod", "zaphod@galaxy.net");

        mockMvc.perform(post("/forums/{forumId}/register", 42L)
                        .contentType("application/json")
                        .param("sendWelcomeMail", "true")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    //TODO:

    /**
     * 4. We expect the controller to transform the incoming UserResource object into a User and to pass this object into the registerUser() method.
     * @throws Exception
     */
    @Test
    void whenValidInput_thenMapsToBusinessModel() throws Exception {

        UserResource user = new UserResource("Zaphod", "zaphod@galaxy.net");

        mockMvc.perform(post("/forums/{forumId}/register", 42L)
                        .contentType("application/json")
                        .param("sendWelcomeMail", "true")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(registerUseCase, times(1)).registerUser(userCaptor.capture(), eq(true));
        assertThat(userCaptor.getValue().getName()).isEqualTo("Zaphod");
        assertThat(userCaptor.getValue().getEmail()).isEqualTo("zaphod@galaxy.net");

    }


    /**
     * 5. Verifying Output Serialization
     * @throws Exception
     */
    @Test
    void whenValidInput_thenReturnsUserResource() throws Exception {

        UserResource user = new UserResource("Zaphod", "zaphod@galaxy.net");

        MvcResult mvcResult = mockMvc.perform(post("/forums/{forumId}/register", 42L)
                        .contentType("application/json")
                        .param("sendWelcomeMail", "true")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andReturn();

        UserResource expectedResponseBody = user;
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(expectedResponseBody));
    }

    @Test
    void whenNullValue_thenReturns400AndErrorResult() throws Exception {
        UserResource user = new UserResource(null, "zaphod@galaxy.net");

        MvcResult mvcResult = mockMvc.perform(post("/forums/{forumId}/register", 42L)
                        .contentType("application/json")
                        .param("sendWelcomeMail", "true")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andReturn();;

        ErrorResult expectedErrorResponse = new ErrorResult("name", "must not be null");
        String actualResponseBody =
                mvcResult.getResponse().getContentAsString();
        String expectedResponseBody =
                objectMapper.writeValueAsString(expectedErrorResponse);
        assertThat(actualResponseBody)
                .isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    /**
     * Creating Custom ResultMatchers
     */
    @Test
    void whenValidInput_thenReturnsUserResource_withFluentApi() throws Exception {

        UserResource user = new UserResource("Zaphod", "zaphod@galaxy.net");
        UserResource expectedResponseBody = user;

        mockMvc.perform(post("/forums/{forumId}/register", 42L)
                        .contentType("application/json")
                        .param("sendWelcomeMail", "true")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(responseBody().containsObjectAsJson(expectedResponseBody, UserResource.class));
    }

    @Test
    void whenValidInput_thenReturnsUserResource_withTypedAssertion() throws Exception {

        UserResource user = new UserResource("Zaphod", "zaphod@galaxy.net");

        MvcResult mvcResult = mockMvc.perform(post("/forums/{forumId}/register", 42L)
                        .contentType("application/json")
                        .param("sendWelcomeMail", "true")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andReturn();

        UserResource expected = user;
        UserResource actualResponseBody = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UserResource.class);
        assertThat(actualResponseBody.getName()).isEqualTo(expected.getName());
        assertThat(actualResponseBody.getEmail()).isEqualTo(expected.getEmail());

    }

    /**
     * Verifying Input Validation
     * @throws Exception
     */
    @Test
    void whenNullValue_thenReturns400() throws Exception {

        UserResource user = new UserResource(null, "zaphod@galaxy.net");

        mockMvc.perform(post("/forums/{forumId}/register", 42L)
                        .contentType("application/json")
                        .param("sendWelcomeMail", "true")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }




    @Test
    void whenNullValue_thenReturns400AndErrorResult_withFluentApi() throws Exception {

        UserResource user = new UserResource(null, "zaphod@galaxy.net");

        mockMvc.perform(post("/forums/{forumId}/register", 42L)
                        .contentType("application/json")
                        .param("sendWelcomeMail", "true")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(responseBody().containsError("name", "must not be null"));
    }

    static ResponseBodyMatchers responseBody(){
        return new ResponseBodyMatchers();
    }

}
