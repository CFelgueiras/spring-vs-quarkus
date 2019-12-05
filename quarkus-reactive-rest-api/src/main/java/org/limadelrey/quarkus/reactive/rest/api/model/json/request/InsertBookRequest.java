package org.limadelrey.quarkus.reactive.rest.api.model.json.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.limadelrey.quarkus.reactive.rest.api.model.entity.Book;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class InsertBookRequest {

    @JsonProperty("author")
    private String author;

    @JsonProperty("title")
    private String title;

    public Book toBook(UUID id) {
        return Book.builder()
                .id(id)
                .author(author)
                .title(title)
                .build();
    }

}
