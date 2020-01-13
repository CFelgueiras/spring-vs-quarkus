package org.limadelrey.quarkus.mongodb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.bson.Document;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {

    @JsonProperty("id")
    private String id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("age")
    private Integer age;

    public static User of(Document document) {
        return User.builder()
                .id(document.getString("_id"))
                .firstName(document.getString("first_name"))
                .lastName(document.getString("last_name"))
                .age(document.getInteger("age"))
                .build();
    }

    public Document toDocument(String id) {
        return new Document()
                .append("_id", id)
                .append("first_name", firstName)
                .append("last_name", lastName)
                .append("age", age);
    }

}
