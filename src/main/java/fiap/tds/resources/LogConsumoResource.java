package fiap.tds.resources;

import fiap.tds.model.bo.LogConsumoBO;
import fiap.tds.model.vo.LogConsumo;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;

@Path("/logsconsumo")
public class LogConsumoResource {

    private final LogConsumoBO logConsumoBO;

    public LogConsumoResource() throws SQLException {
        this.logConsumoBO = new LogConsumoBO();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response adicionarLogConsumo(LogConsumo logConsumo, @Context UriInfo uriInfo) {
        try {
            logConsumoBO.inserirLogConsumo(logConsumo);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Integer.toString(logConsumo.getId_log()));
            return Response.created(builder.build())
                    .entity("Log de consumo adicionado com sucesso!")
                    .build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao adicionar log de consumo. " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response atualizarLogConsumo(@PathParam("id") int id, LogConsumo logConsumo) {
        try {
            logConsumo.setId_log(id);
            logConsumoBO.atualizarLogConsumo(logConsumo);
            return Response.ok("Log de consumo atualizado com sucesso!").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar log de consumo. " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response excluirLogConsumo(@PathParam("id") int id) {
        try {
            logConsumoBO.excluirLogConsumo(id);
            return Response.ok("Log de consumo excluído com sucesso!").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir log de consumo. " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarLogsConsumo() {
        try {
            List<LogConsumo> logsConsumo = logConsumoBO.listarLogConsumo();
            return Response.ok(logsConsumo).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar logs de consumo. " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarLogConsumoPorId(@PathParam("id") int id) {
        try {
            LogConsumo logConsumo = logConsumoBO.buscarLogConsumoPorId(id);
            return Response.ok(logConsumo).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Log de consumo não encontrado. " + e.getMessage())
                    .build();
        }
    }
}
