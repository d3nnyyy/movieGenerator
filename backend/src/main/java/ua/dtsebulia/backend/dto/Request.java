package ua.dtsebulia.backend.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Request {

    private String model;
    private List<Message> messages;

    public Request(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
    }
}
