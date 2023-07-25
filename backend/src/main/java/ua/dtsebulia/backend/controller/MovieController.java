package ua.dtsebulia.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ua.dtsebulia.backend.dto.Prompt;
import ua.dtsebulia.backend.dto.Request;
import ua.dtsebulia.backend.dto.Response;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class MovieController {

    @Value("${model}")
    private String model;

    @Value("${url}")
    private String url;

    private final RestTemplate restTemplate;

    @PostMapping
    public String getMovie(@RequestBody Prompt prompt) {

        String message = String.format(
                """
                        Generate a film recommendation based on user preferences:
                        Rating: %s
                        Release Year: %s
                        Film Length: %s
                        Genre: %s
                        Desired Cast: %s
                                                
                        Recommended Film:
                                                
                        Title:\s
                        IMDB Rating:\s
                        Release Year:
                        Genre:\s
                        Length:\s
                        Actors:\s
                        Director:\s
                                                
                        Synopsis:\s
                        """,
                prompt.getRating(),
                prompt.getReleaseYear(),
                prompt.getFilmLength(),
                prompt.getGenre(),
                prompt.getDesiredCast()

        );

        Request request = new Request(model, message);
        Response response = restTemplate.postForObject(url, request, Response.class);
        return response.getChoices().get(0).getMessage().getContent();
    }

}
