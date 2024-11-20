package fiap.tds.resources;

import fiap.tds.model.bo.AparelhosBO;
import fiap.tds.model.vo.Aparelhos;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;

@Path("/aparelhos")
public class AparelhosResource {

    private AparelhosBO aparelhosBO;

    public AparelhosResource() throws SQLException {
        this.aparelhosBO = new AparelhosBO();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirAparelho(Aparelhos aparelho, @Context UriInfo uriInfo) throws Exception {
        Aparelhos criado = aparelhosBO.inserirAparelho(aparelho);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(criado.getId_aparelho()));
        return Response.created(builder.build()).entity(criado).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarAparelho(@PathParam("id") int id, Aparelhos aparelho) throws SQLException {
        aparelho.setId_aparelho(id);
        boolean atualizado = aparelhosBO.atualizarAparelho(aparelho);
        if (atualizado) {
            return Response.ok(aparelho).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirAparelho(@PathParam("id") int id) throws SQLException {
        boolean excluido = aparelhosBO.excluirAparelho(id);
        if (excluido) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarAparelhos() throws SQLException {
        List<Aparelhos> aparelhos = aparelhosBO.listarAparelhos();
        return Response.ok(aparelhos).build();
    }

}
