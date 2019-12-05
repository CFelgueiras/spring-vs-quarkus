package org.limadelrey.quarkus.reactive.rest.api.model.json.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.limadelrey.quarkus.reactive.rest.api.model.entity.Book;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ReadBookResponse {

    @JsonProperty("author")
    private String author;

    @JsonProperty("title")
    private String title;

    public ReadBookResponse(Book book) {
        this.author = book.getAuthor();
        this.title = book.getTitle();
    }

}
