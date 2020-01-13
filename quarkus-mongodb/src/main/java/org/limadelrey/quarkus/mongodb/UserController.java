package org.limadelrey.quarkus.mongodb;

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
public class UserController {

    @Inject
    UserRepository userRepository;

    @GET
    @Path("/users")
    public List<User> readAll() {
        return userRepository.readAll();
    }

//    @GET
//    @Path("/books/{id}")
//    public User readOne(@PathParam("id") Long id) {
//        final User user = bookRepository.findById(id);
//
//        if (user == null) {
//            throw new RuntimeException("User with id " + id + " not found.");
//        }
//
//        return user;
//    }

    @POST
    @Path("/books")
    @Transactional
    public void create(User user) {
        userRepository.insert(user);
    }

//    @PUT
//    @Path("/books/{id}")
//    @Transactional
//    public User update(User newBook, @PathParam("id") Long id) {
//        final User user = bookRepository.findById(id);
//
//        if (user == null) {
//            throw new RuntimeException("User with id " + id + " not found.");
//        }
//
//        user.setAuthor(newBook.getAuthor());
//        user.setTitle(newBook.getTitle());
//
//        return user;
//    }
//
//    @DELETE
//    @Path("/books/{id}")
//    @Transactional
//    public void delete(@PathParam("id") Long id) {
//        final User user = bookRepository.findById(id);
//
//        if (user == null) {
//            throw new RuntimeException("User with id " + id + " not found.");
//        }
//
//        bookRepository.delete(user);
//    }

}
