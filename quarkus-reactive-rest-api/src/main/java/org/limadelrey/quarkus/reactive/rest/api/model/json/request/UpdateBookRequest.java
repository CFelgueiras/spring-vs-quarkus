package org.limadelrey.quarkus.reactive.rest.api.model.json.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.limadelrey.quarkus.reactive.rest.api.model.entity.Book;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UpdateBookRequest {

    @JsonProperty("author")
    private String author;

    @JsonProperty("title")
    private String title;

    public Book toBook() {
        return Book.builder()
                .author(author)
                .title(title)
                .build();
    }

}
