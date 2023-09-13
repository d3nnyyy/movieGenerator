package ua.dtsebulia.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ua.dtsebulia.backend.dto.Prompt;
import ua.dtsebulia.backend.dto.Request;
import ua.dtsebulia.backend.dto.Response;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "localhost:3000")
public class MovieController {

    private final RestTemplate restTemplate;

    @Value("${model}")
    private String model;

    @Value("${url}")
    private String url;

    @GetMapping("/test")
    public String test() {
        return "Hello from backend";
    }

    @PostMapping
    public String getMovieRecommendation(@RequestBody Prompt prompt) {
        String message = createMessageFromPrompt(prompt);
        Request request = new Request(model, message);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Request> requestEntity = new HttpEntity<>(request, headers);

            ResponseEntity<Response> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    Response.class
            );

            Response response = responseEntity.getBody();

            if (response != null && response.getChoices() != null && !response.getChoices().isEmpty()) {
                return response.getChoices().get(0).getMessage().getContent();
            } else {
                return "Sorry, no movie recommendation available.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while processing the movie recommendation, please try later";
        }
    }

    static String createMessageFromPrompt(Prompt prompt) {
        return String.format(
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
    }

}
