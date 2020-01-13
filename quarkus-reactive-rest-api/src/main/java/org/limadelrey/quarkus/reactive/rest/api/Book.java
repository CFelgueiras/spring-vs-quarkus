package org.limadelrey.quarkus.reactive.rest.api;

import io.vertx.reactivex.sqlclient.Row;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Book {

    private UUID id;
    private String author;
    private String title;
    private double price;

    public Book(Row row) {
        this.id = row.getUUID("id");
        this.author = row.getString("author");
        this.title = row.getString("title");
        this.price = row.getDouble("price");
    }

}
