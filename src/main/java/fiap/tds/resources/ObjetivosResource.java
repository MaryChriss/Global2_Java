package fiap.tds.resources;

import fiap.tds.model.bo.ObjetivosBO;
import fiap.tds.model.vo.Cliente;
import fiap.tds.model.vo.Objetivos;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;

@Path("/objetivos")
public class ObjetivosResource {

    private final ObjetivosBO objetivosBO;

    public ObjetivosResource() throws SQLException {
        this.objetivosBO = new ObjetivosBO();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response adicionarObjetivo(Objetivos objetivo, @Context UriInfo uriInfo) throws Exception {

            Objetivos novoObjetivo = objetivosBO.inserirObjetivo(objetivo);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Integer.toString(novoObjetivo.getId_objetivo()));
            return Response.created(builder.build()).entity("Objetivo adicionado com sucesso!").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response atualizarObjetivo(@PathParam("id") int id, Objetivos objetivo) throws SQLException {
        try {
            objetivo.setId_objetivo(id);
            boolean atualizado = objetivosBO.atualizarObjetivo(objetivo);
            if (atualizado) {
                return Response.ok("Objetivo atualizado com sucesso!").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Objetivo não encontrado.").build();
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro: " + e.getMessage()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao atualizar objetivo.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response excluirObjetivo(@PathParam("id") int id) {
        try {
            boolean excluido = objetivosBO.excluirObjetivo(id);
            if (excluido) {
                return Response.ok("Objetivo excluído com sucesso!").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Objetivo não encontrado.").build();
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro: " + e.getMessage()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao excluir objetivo.").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarObjetivos() {
        try {
            List<Objetivos> objetivos = objetivosBO.listarObjetivos();
            return Response.ok(objetivos).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao listar objetivos.").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarObjetivoPorId(@PathParam("id") int id) {
        try {
            Objetivos objetivo = objetivosBO.buscarObjetivoPorId(id);
            if (objetivo != null) {
                return Response.ok(objetivo).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Objetivo não encontrado.").build();
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro: " + e.getMessage()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao buscar objetivo.").build();
        }
    }

    @GET
    @Path("/cliente/{idCliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarObjetivoPorIdCliente(@PathParam("idCliente") int idCliente) {
        try {
            List<Objetivos> objetivos = objetivosBO.buscarObjetivoPorIdCliente(idCliente);
            if (!objetivos.isEmpty()) {
                return Response.ok(objetivos).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Nenhum objetivo encontrado para o cliente especificado.").build();
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro: " + e.getMessage()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno ao buscar objetivos do cliente.").build();
        }
    }

}
