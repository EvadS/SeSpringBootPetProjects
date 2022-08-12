package com.se.sample;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class SpeakerControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    /**
     * Generating the Documentation
     * @throws Exception
     */
    @Test
    public void getPersonByIdShouldReturnOk() throws Exception {

//
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/speakers/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON))//("application/json;charset=UTF-8"))
                .andDo(print());
//                (document("persons/get-by-id",
//                        pathParameters(parameterWithName("id")
//                                .description("Identifier of the person to be obtained.")),
//                        responseFields(
//                                fieldWithPath("name")
//                                        .description("Unique identifier of the person."),
//                                fieldWithPath("company")
//                                        .description("First Name of the person."),
//
//                                fieldWithPath("status").description(""),
//                                fieldWithPath("_links").description("")
//                                //fieldWithPath("self").description("")
//
//                     )
//                ));
    }

    @Test
    public void testAllSpeakers() throws Exception {
        //Given-When
        ResultActions actions =  this.mockMvc.perform(get("/speakers/{id}", 1L))
                .andDo(print());
        actions.andExpect(jsonPath("$.name", CoreMatchers.is("Josh Long")))
                .andExpect(jsonPath("$.company", CoreMatchers.is("Pivotal")))
                .andExpect(jsonPath("$.status", CoreMatchers.is("I like Spring & Rest Docs.")));

        //Then
        actions.andDo(document("{class-name}/{method-name}",
                responseFields(
                        fieldWithPath("name").description("Speakers name"),
                        fieldWithPath("company").description("Age"),
                        fieldWithPath("status").description("The company that speaker is working on."),
                subsectionWithPath("_links").ignored()

                ))
        );
    }
}
