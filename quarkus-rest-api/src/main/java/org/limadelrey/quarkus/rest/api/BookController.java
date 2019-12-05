package org.limadelrey.quarkus.rest.api;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@ApplicationScoped
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Path("/api/v1")
public class BookController {

    @Inject
    BookRepository bookRepository;

    @GET
    @Path("/books")
    public List<Book> readAll() {
        return bookRepository.listAll();
    }

    @GET
    @Path("/books/{id}")
    public Book readOne(@PathParam("id") Long id) {
        final Book book = bookRepository.findById(id);

        if (book == null) {
            throw new RuntimeException("Book with id " + id + " not found.");
        }

        return book;
    }

    @POST
    @Path("/books")
    @Transactional
    public void create(Book book) {
        bookRepository.persist(book);
    }

    @PUT
    @Path("/books/{id}")
    @Transactional
    public Book update(Book newBook, @PathParam("id") Long id) {
        final Book book = bookRepository.findById(id);

        if (book == null) {
            throw new RuntimeException("Book with id " + id + " not found.");
        }

        book.setAuthor(newBook.getAuthor());
        book.setTitle(newBook.getTitle());

        return book;
    }

    @DELETE
    @Path("/books/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        final Book book = bookRepository.findById(id);

        if (book == null) {
            throw new RuntimeException("Book with id " + id + " not found.");
        }

        bookRepository.delete(book);
    }

}
