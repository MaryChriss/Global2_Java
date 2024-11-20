package fiap.tds.resources;

import fiap.tds.model.bo.LoginBO;
import fiap.tds.model.vo.Login;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/login")
public class LoginResource {

    private LoginBO loginBO;

    public LoginResource() throws SQLException {
        this.loginBO = new LoginBO();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Login> listaLogin() throws SQLException {
        return (ArrayList<Login>) loginBO.listarLogins();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLogin(Login login, @Context UriInfo uriInfo) throws Exception {
        Login loginCriado = loginBO.inserirLogin(login);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(loginCriado.getId_cliente()));
        return Response.created(builder.build()).entity(loginCriado).build();
    }

    @POST
    @Path("/autenticar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response autenticar(Login login) {
        try {
            Login loginAutenticado = loginBO.autenticar(login);
            return Response.status(Response.Status.ACCEPTED).entity(loginAutenticado).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao autenticar login").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLoginPorId(@PathParam("id") int id) {
        try {
            Login login = loginBO.buscarLoginPorId(id);
            if (login != null) {
                return Response.ok(login).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar login por ID").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogin(@PathParam("id") int id, Login login) {
        try {
            login.setId_login(id);
            boolean updated = loginBO.atualizarLogin(login);
            if (updated) {
                return Response.ok(login).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar login").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLogin(@PathParam("id") int id) {
        try {
            boolean deleted = loginBO.excluirLogin(id);
            if (deleted) {
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir login").build();
        }
    }


}
