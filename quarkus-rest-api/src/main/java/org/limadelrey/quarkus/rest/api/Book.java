package org.limadelrey.quarkus.rest.api;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Book extends PanacheEntity {

    private Long id;
    private String author;
    private String title;

}
