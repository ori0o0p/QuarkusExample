package com.example.user.resource;

import com.example.user.dto.UpdateUser;
import com.example.user.model.User;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.net.URI;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReactiveUserResource {

    @POST
    public Uni<Response> createUser(User user) {
        return user.<User>persist().map(u ->
                Response.created(URI.create("/users/" + u.id.toString()))
                .entity(user).build());
    }

    @GET
    public Multi<User> userList() {
        return User.findAllUser();
    }

    @DELETE
    public Uni<Void> deleteById(@QueryParam("userId") String id) {
        return User.deleteUserById(id);
    }

    @PATCH
    @Path("/{userId}")
    public Uni<Void> update(@PathParam("userId") String id, @RequestBody UpdateUser updateUser) {
        return User.updateUser(id, updateUser);
    }

}
