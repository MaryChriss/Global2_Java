package fiap.tds.resources;

import fiap.tds.model.bo.RelatorioBO;
import fiap.tds.model.vo.Relatorio;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/relatorios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RelatorioResource {

    private RelatorioBO relatorioBO;

    public RelatorioResource() {
        try {
            this.relatorioBO = new RelatorioBO();
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao inicializar o BO de Relatório.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    public Response inserirRelatorio(Relatorio relatorio) {
        try {
            Relatorio relatorioCriado = relatorioBO.inserirRelatorio(relatorio);
            return Response.status(Response.Status.CREATED).entity(relatorioCriado).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao inserir o relatório.").build();
        }
    }

    @PUT
    public Response atualizarRelatorio(Relatorio relatorio) {
        try {
            boolean atualizado = relatorioBO.atualizarRelatorio(relatorio);
            if (atualizado) {
                return Response.ok("Relatório atualizado com sucesso.").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Relatório não encontrado.").build();
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar o relatório.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirRelatorio(@PathParam("id") int id) {
        try {
            boolean excluido = relatorioBO.excluirRelatorio(id);
            if (excluido) {
                return Response.ok("Relatório excluído com sucesso.").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Relatório não encontrado.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao excluir o relatório.").build();
        }
    }

    @GET
    public Response listarRelatorios() {
        try {
            List<Relatorio> relatorios = relatorioBO.listarRelatorios();
            return Response.ok(relatorios).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar os relatórios.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarRelatorioPorId(@PathParam("id") int id) {
        try {
            Relatorio relatorio = relatorioBO.buscarRelatorioPorId(id);
            if (relatorio != null) {
                return Response.ok(relatorio).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Relatório não encontrado.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Erro ao buscar o relatório.").build();
        }
    }

    @GET
    @Path("/endereco/{idEndereco}")
    public Response listarRelatoriosPorEndereco(@PathParam("idEndereco") int idEndereco) {
        try {
            List<Relatorio> relatorios = relatorioBO.listarRelatoriosPorEndereco(idEndereco);
            return Response.ok(relatorios).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar relatórios do endereço.").build();
        }
    }

}
