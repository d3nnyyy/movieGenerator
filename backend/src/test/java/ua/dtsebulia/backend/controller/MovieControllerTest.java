package ua.dtsebulia.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.dtsebulia.backend.dto.Prompt;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

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

    private static String asJsonString(final Prompt obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
