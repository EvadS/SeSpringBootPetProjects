package com.example.webmvctestdemo.controller;

import com.example.webmvctestdemo.domain.RegisterUseCase;
import com.example.webmvctestdemo.domain.User;
import com.example.webmvctestdemo.web.ErrorResult;
import com.example.webmvctestdemo.web.ResponseBodyMatchers;
import com.example.webmvctestdemo.web.UserResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RegisterRestController.class)
class RegisterRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RegisterUseCase registerUseCase;

    /**
     * Verifying Input Deserialization
     *
     * @throws Exception
     */
    @Test
    void whenValidInput_thenReturns200() throws Exception {
        UserResource user = new UserResource("evad", "zaphod@mail.net");

        mockMvc.perform(post("/forums/{forumId}/register", 42L)
                        .contentType("application/json")
                        .param("sendWelcomeMail", "true")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }


    @Test
    void whenValidUrlAndMethodAndContentType_thenReturns200() throws Exception {

        UserResource user = new UserResource("Zaphod", "zaphod@galaxy.net");

        mockMvc.perform(post("/forums/42/register")
                        .param("sendWelcomeMail", "true")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

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


    /**
     * Verifying Input Validation
     *
     * @throws Exception
     */
    @Test
    void whenNullValue_thenReturns400() throws Exception {
        UserResource user = new UserResource(null, "zaphod@galaxy.net");

        mockMvc.perform(post("/forums/{forumId}/register", 42L)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
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

    @Test
    void whenNullValue_thenReturns400AndErrorResult() throws Exception {

        UserResource user = new UserResource(null, "zaphod@galaxy.net");

        MvcResult mvcResult = mockMvc.perform(post("/forums/{forumId}/register", 42L)
                        .contentType("application/json")
                        .param("sendWelcomeMail", "true")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResult expectedErrorResponse = new ErrorResult("name", "must not be null");
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        String expectedResponseBody = objectMapper.writeValueAsString(expectedErrorResponse);
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
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

    static ResponseBodyMatchers responseBody() {
        return new ResponseBodyMatchers();
    }
}