package org.limadelrey.quarkus.reactive.rest.api;

import io.reactivex.Single;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Tag(name = "Books API", description = "Endpoints regarding book interaction")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Path("/api/v1")
@ApplicationScoped
public class BookController {

    @Inject
    BookService bookService;

    // Open API
    @Operation(summary = "Read all books.")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Book.class, type = SchemaType.ARRAY)))
    // Open Metrics
    @Counted(name = "readAllCount", description = "How many readAll() calls have been performed.")
    @Timed(name = "readAllTime", description = "A measure of how long it takes to perform a readAll() call.", unit = MetricUnits.MILLISECONDS)
    // JAX-RS
    @GET
    @Path("/books")
    public Single<Response> readAll() {
        return bookService.readAll()
                .map(result -> Response.ok(result).build());
    }

    // Open API
    @Operation(summary = "Read a book given its ID.")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Book.class)))
    // Open Metrics
    @Counted(name = "readOneCount", description = "How many readOne() calls have been performed.")
    @Timed(name = "readOneTime", description = "A measure of how long it takes to perform a readOne() call.", unit = MetricUnits.MILLISECONDS)
    // JAX-RS
    @GET
    @Path("/books/{id}")
    public Single<Response> readOne(@PathParam("id") UUID id) {
        return bookService.readOne(id)
                .map(result -> Response.ok(result).build());
    }

    // Open API
    @Operation(summary = "Create a book.")
    @APIResponse(responseCode = "201", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Book.class)))
    // Open Metrics
    @Counted(name = "createCount", description = "How many create() calls have been performed.")
    @Timed(name = "createTime", description = "A measure of how long it takes to perform a create() call.", unit = MetricUnits.MILLISECONDS)
    // JAX-RS
    @POST
    @Path("/books")
    public Single<Response> create(@Valid Book book) {
        return bookService.insert(book)
                .map(result -> Response.status(201).entity(result).build());
    }

    // Open API
    @Operation(summary = "Update a book given its ID.")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Book.class)))
    // Open Metrics
    @Counted(name = "updateCount", description = "How many update() calls have been performed.")
    @Timed(name = "updateTime", description = "A measure of how long it takes to perform an update() call.", unit = MetricUnits.MILLISECONDS)
    // JAX-RS
    @PUT
    @Path("/books/{id}")
    public Single<Response> update(@PathParam("id") UUID id, @Valid Book book) {
        return bookService.update(id, book)
                .andThen(Single.just(Response.noContent().build()));
    }

    // Open API
    @Operation(summary = "Delete a book given its ID.")
    @APIResponse(responseCode = "204")
    // Open Metrics
    @Counted(name = "deleteCount", description = "How many delete() calls have been performed.")
    @Timed(name = "deleteTime", description = "A measure of how long it takes to perform a delete() call.", unit = MetricUnits.MILLISECONDS)
    // JAX-RS
    @DELETE
    @Path("/books/{id}")
    public Single<Response> delete(@PathParam("id") UUID id) {
        return bookService.delete(id)
                .andThen(Single.just(Response.noContent().build()));
    }

}
