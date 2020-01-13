package org.limadelrey.quarkus.mongodb;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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

    @GET
    @Path("/users/{id}")
    public User readOne(@PathParam("id") String id) {
        return userRepository.readOne(id);
    }

    @POST
    @Path("/users")
    public User create(User user) {
       return userRepository.insert(user);
    }

    @PUT
    @Path("/users/{id}")
    public void update(User userAfterUpdate, @PathParam("id") String id) {
        userRepository.update(userAfterUpdate, id);
    }

    @DELETE
    @Path("/users/{id}")
    public void delete(@PathParam("id") String id) {
        userRepository.delete(id);
    }

}
