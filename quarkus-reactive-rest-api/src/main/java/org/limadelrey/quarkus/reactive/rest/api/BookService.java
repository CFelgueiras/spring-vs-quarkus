package org.limadelrey.quarkus.reactive.rest.api;

import io.reactivex.Completable;
import io.reactivex.Single;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class BookService {

    @Inject
    BookRepository bookRepository;

    public Single<List<Book>> readAll() {
        return bookRepository.readAll();
    }

    public Single<Book> readOne(UUID id) {
        return bookRepository.readOne(id);
    }

    public Single<Book> insert(Book book) {
        book.setId(UUID.randomUUID());

        return bookRepository.insert(book)
                .andThen(Single.just(book));
    }

    public Completable update(UUID id, Book book) {
        return bookRepository.update(id, book);
    }

    public Completable delete(UUID id) {
        return bookRepository.delete(id);
    }

}
