package ua.dtsebulia.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import ua.dtsebulia.backend.dto.Prompt;
import ua.dtsebulia.backend.dto.Request;
import ua.dtsebulia.backend.dto.Response;

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private MovieController movieController;

    private Prompt prompt;

    @BeforeEach
    public void setUp() {
        prompt = new Prompt("8.5", "2000", "120", "Action", "Tom Hanks");
    }


    @Test
    public void testIfMessageIsCreatedProperly() {

        String message = MovieController.createMessageFromPrompt(prompt);

        String expectedMessage = """
            Generate a film recommendation based on user preferences:
            Rating: 8.5
            Release Year: 2000
            Film Length: 120
            Genre: Action
            Desired Cast: Tom Hanks
                                                
            Recommended Film:
                                                
            Title:\s
            IMDB Rating:\s
            Release Year:
            Genre:\s
            Length:\s
            Actors:\s
            Director:\s
                                                
            Synopsis:\s
            """;

        Assertions.assertEquals(message, expectedMessage);
    }

    @Test
    public void testRequestWithNoBody() throws Exception {

        prompt = new Prompt();
        System.out.println(
        mockMvc.perform(MockMvcRequestBuilders.post("")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(prompt)))
                .andExpect(status().isOk()));
    }

    @Test
    public void testIfWeGetResponseStatusOK() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(prompt)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMovieRecommendation_NoRecommendation() {

        Response response = new Response(Collections.emptyList());

        Mockito.when(restTemplate.postForObject(
                        Mockito.anyString(),
                        Mockito.any(Request.class),
                        Mockito.any(Class.class)))
                .thenReturn(response);

        String result = movieController.getMovieRecommendation(prompt);

        Assertions.assertEquals("Sorry, no movie recommendation available.", result);
    }

    private static String asJsonString(final Prompt obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
