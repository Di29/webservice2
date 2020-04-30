package controllers;

import domain.User;;
import org.glassfish.jersey.media.multipart.FormDataParam;
import repositories.entities.UserRepository;
import repositories.interfaces.IEntityRepository;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.LinkedList;

@Path("users")
public class UserController {

    @GET
    public String hello() {
        return "Hello world!";
    }

    @GET
    @Path("/{id}")
    public Response getUserByID(@PathParam("id") long id) {
        IEntityRepository userrep = new UserRepository();
        LinkedList<User> users = (LinkedList<User>) userrep.query(
                "SELECT * FROM users WHERE id = " + id);
        if (users.isEmpty())
            return  Response.status(Response.Status.NOT_FOUND)
                    .entity("There is no user with such id")
                    .build();
        else
            return Response.status(Response.Status.OK)
                    .entity(users.get(0))
                    .build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/add")
    public Response addUser(@FormDataParam("name") String name,
                            @FormDataParam("surname") String surname,
                            @FormDataParam("username") String username,
                            @FormDataParam("password") String password,
                            @FormDataParam("birthday") Date birthday) {
        IEntityRepository userrep = new UserRepository();
        User user = new User(name, surname, username, password, birthday);
        userrep.add(user);
        return Response.status(200).entity("User created").build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/remove")
    public Response removeUser(@FormDataParam("username") String username) {
        IEntityRepository userrep = new UserRepository();
        User user = new User(username);
        userrep.remove(user);
        return Response.status(200).entity("User deleted").build();
    }

}
