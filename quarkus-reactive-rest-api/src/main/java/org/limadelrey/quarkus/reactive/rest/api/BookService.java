package org.limadelrey.quarkus.reactive.rest.api;

import io.reactivex.Completable;
import io.reactivex.Single;
import org.limadelrey.quarkus.reactive.rest.api.model.entity.Book;
import org.limadelrey.quarkus.reactive.rest.api.model.json.request.InsertBookRequest;
import org.limadelrey.quarkus.reactive.rest.api.model.json.response.InsertBookResponse;
import org.limadelrey.quarkus.reactive.rest.api.model.json.response.ReadBookResponse;
import org.limadelrey.quarkus.reactive.rest.api.model.json.request.UpdateBookRequest;
import org.limadelrey.quarkus.reactive.rest.api.model.json.response.UpdateBookResponse;
import org.limadelrey.quarkus.reactive.rest.api.BookRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookService {

    @Inject
    BookRepository bookRepository;

    public Single<List<ReadBookResponse>> readAll() {
        return bookRepository.readAll()
                .map(result -> result.stream()
                        .map(book -> new ReadBookResponse(book))
                        .collect(Collectors.toList()));
    }

    public Single<ReadBookResponse> readOne(UUID id) {
        return bookRepository.readOne(id)
                .map(book -> new ReadBookResponse(book));
    }

    public Single<InsertBookResponse> insert(InsertBookRequest request) {
        final Book book = request.toBook(UUID.randomUUID());

        return bookRepository.insert(book)
                .andThen(Single.just(new InsertBookResponse(book)));
    }

    public Single<UpdateBookResponse> update(UUID id, UpdateBookRequest request) {
        final Book book = request.toBook();

        return bookRepository.update(id, book)
                .andThen(Single.just(new UpdateBookResponse(book)));
    }

    public Completable delete(UUID id) {
        return bookRepository.delete(id);
    }

}
