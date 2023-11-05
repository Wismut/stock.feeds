package com.example.stock.feeds.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Tutorial {
    @Id
    private int id;

    private String title;
    private String description;
    private boolean published;

    public Tutorial(String title, String description, boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;
    }
}
