package fiap.tds.resources;

import fiap.tds.model.bo.SugestoesEconomiaBO;
import fiap.tds.model.vo.SugestoesEconomia;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/sugestoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SugestoesEconomiaResource {

    private SugestoesEconomiaBO sugestoesEconomiaBO;

    public SugestoesEconomiaResource() {
        try {
            this.sugestoesEconomiaBO = new SugestoesEconomiaBO();
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao inicializar o BO de Sugestões de Economia.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    public Response inserirSugestao(SugestoesEconomia sugestao) {
        try {
            SugestoesEconomia sugestaoCriada = sugestoesEconomiaBO.inserirSugestao(sugestao);
            return Response.status(Response.Status.CREATED).entity(sugestaoCriada).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao inserir a sugestão.").build();
        }
    }

    @PUT
    public Response atualizarSugestao(SugestoesEconomia sugestao) {
        try {
            boolean atualizado = sugestoesEconomiaBO.atualizarSugestao(sugestao);
            if (atualizado) {
                return Response.ok("Sugestão atualizada com sucesso.").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Sugestão não encontrada.").build();
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar a sugestão.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirSugestao(@PathParam("id") int id) {
        try {
            boolean excluido = sugestoesEconomiaBO.excluirSugestao(id);
            if (excluido) {
                return Response.ok("Sugestão excluída com sucesso.").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Sugestão não encontrada.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao excluir a sugestão.").build();
        }
    }

    @GET
    public Response listarSugestoes() {
        try {
            List<SugestoesEconomia> sugestoes = sugestoesEconomiaBO.listarSugestoes();
            return Response.ok(sugestoes).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar as sugestões.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarSugestaoPorId(@PathParam("id") int id) {
        try {
            SugestoesEconomia sugestao = sugestoesEconomiaBO.buscarSugestaoPorId(id);
            if (sugestao != null) {
                return Response.ok(sugestao).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Sugestão não encontrada.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar a sugestão.").build();
        }
    }

    @GET
    @Path("/cliente/{idCliente}")
    public Response buscarSugestaoPorIdCliente(@PathParam("idCliente") int idCliente) {
        try {
            SugestoesEconomia sugestao = sugestoesEconomiaBO.buscarSugestaoPorIdCliente(idCliente);
            if (sugestao != null) {
                return Response.ok(sugestao).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Sugestão para o cliente não encontrada.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar a sugestão por cliente.").build();
        }
    }
}
