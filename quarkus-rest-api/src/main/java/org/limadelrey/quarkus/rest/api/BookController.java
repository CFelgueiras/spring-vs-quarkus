package org.limadelrey.quarkus.rest.api;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import io.quarkus.panache.common.Sort;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
@Path("/api/v1")
public class BookController {

    @Inject
    private BookRepository bookRepository;

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
