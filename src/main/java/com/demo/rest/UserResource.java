package com.demo.rest;

import com.demo.model.User;
import com.demo.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    
    @Inject
    private UserService userService;
    
    @GET
    public Response getAllUsers() {
        List<User> users = userService.getAllUsers();
        return Response.ok(users).build();
    }
    
    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }
    
    @POST
    public Response createUser(User user) {
        try {
            userService.createUser(user);
            return Response.status(Response.Status.CREATED).entity(user).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }
    
    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") Long id, User user) {
        user.setId(id);
        try {
            userService.updateUser(user);
            return Response.ok(user).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        userService.deleteUser(id);
        return Response.noContent().build();
    }
}

// Made with Bob
