package org.limadelrey.quarkus.mongodb.model;

import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {

    private String id;
    private String firstName;
    private String lastName;

}
