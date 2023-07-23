package ua.dtsebulia.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prompt {

    private String rating;
    private String releaseYear;
    private String filmLength;
    private String genre;
    private String desiredCast;

}
