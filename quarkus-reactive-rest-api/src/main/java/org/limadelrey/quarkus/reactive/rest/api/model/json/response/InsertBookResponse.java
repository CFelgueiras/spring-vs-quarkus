package org.limadelrey.quarkus.reactive.rest.api.model.json.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.limadelrey.quarkus.reactive.rest.api.model.entity.Book;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class InsertBookResponse {

    @JsonProperty("id")
    private UUID id;

    public InsertBookResponse(Book book) {
        this.id = book.getId();
    }

}
