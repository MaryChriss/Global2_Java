package fiap.tds.resources;

import fiap.tds.model.bo.EnderecoBO;
import fiap.tds.model.vo.Endereco;
import fiap.tds.model.vo.Objetivos;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;

@Path("/endereco")
public class EnderecoResource {

    private EnderecoBO enderecoBO = new EnderecoBO();

    public EnderecoResource() throws SQLException {}

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionarEndereco(Endereco endereco, @Context UriInfo uriInfo) throws SQLException {
        enderecoBO.inserirEndereco(endereco);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(endereco.getId_endereco()));
        return Response.created(builder.build()).entity("Endereço adicionado com sucesso!").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarEndereco(@PathParam("id") int id, Endereco endereco) throws SQLException {
        endereco.setId_endereco(id);
        enderecoBO.atualizarEndereco(endereco);
        return Response.ok("Endereço atualizado com sucesso!").build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirEndereco(@PathParam("id") int id) throws SQLException {
        enderecoBO.excluirEndereco(id);
        return Response.ok("Endereço excluído com sucesso!").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarEndereco() throws SQLException {
        List<Endereco> enderecos = enderecoBO.listarEnderecos();
        return Response.ok(enderecos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarEnderecoPorId(@PathParam("id") int id) throws SQLException {
        Endereco endereco = enderecoBO.buscarEnderecoPorId(id);
        if (endereco == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Endereço não encontrado.").build();
        }
        return Response.ok(endereco).build();
    }

    @GET
    @Path("/getByClient/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarEnderecoPorIdCliente(@PathParam("id") int idCliente) throws SQLException {
        Endereco endereco = enderecoBO.buscarEnderecoPorIdCliente(idCliente);
        if (endereco == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Endereço não encontrado para o cliente informado.").build();
        }
        return Response.ok(endereco).build();
    }
}
