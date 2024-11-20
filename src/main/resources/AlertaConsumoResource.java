package fiap.tds.resources;


import fiap.tds.model.bo.AlertasConsumoBO;
import fiap.tds.model.vo.AlertasConsumo;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;

@Path("/alertasdeconsumo")
public class AlertaConsumoResource {

    private AlertasConsumoBO alertasConsumoBO = new AlertasConsumoBO();

    public AlertaConsumoResource() throws SQLException {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionarAlerta(AlertasConsumo alertasConsumo, @Context UriInfo uriInfo) throws SQLException {
        alertasConsumoBO.inserirAlerta(alertasConsumo);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(alertasConsumo.getId_alerta()));
        return Response.created(builder.build()).entity("Alerta adicionado com sucesso!").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarAlerta(@PathParam("id") int id, AlertasConsumo alertasConsumo) throws SQLException {
        alertasConsumo.setId_alerta(id);
        alertasConsumoBO.atualizarAlerta(alertasConsumo);
        return Response.ok("Alerta atualizado com sucesso!").build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirAlerta(@PathParam("id") int id) throws SQLException {
        alertasConsumoBO.excluirAlerta(id);
        return Response.ok("Alerta excluído com sucesso!").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarAlerta() throws SQLException {
        List<AlertasConsumo> alertasConsumos = alertasConsumoBO.listarAlertas();
        return Response.ok(alertasConsumos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarAlertaPorId(@PathParam("id") int id) throws SQLException {
        AlertasConsumo alertasConsumo = alertasConsumoBO.buscarAlertaPorId(id);
        if (alertasConsumo == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Alerta não encontrado").build();
        }
        return Response.ok(alertasConsumo).build();
    }

    @GET
    @Path("/getByClient/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarAlertasPorIdCliente(@PathParam("id_cliente") int id) throws SQLException {
        AlertasConsumo alertasConsumo = alertasConsumoBO.buscarAlertasPorCliente(id);
        if (alertasConsumo == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Alerta não encontrado").build();
        }
        return Response.ok(alertasConsumo).build();
    }
}
