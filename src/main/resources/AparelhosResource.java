package fiap.tds.resources;


import fiap.tds.model.bo.AparelhosBO;
import fiap.tds.model.vo.Aparelhos;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/aparelhos")
public class AparelhosResource {

    private AparelhosBO aparelhosBO = new AparelhosBO();

    public AparelhosResource() throws SQLException {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirAparelho(Aparelhos aparelho, @Context UriInfo uriInfo) throws Exception {
        Aparelhos aparelhocriado = aparelhosBO.inserirAparelho(aparelho);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(aparelhocriado.getId_aparelho()));
        return Response.created(builder.build()).entity(aparelhocriado).build();
    }

    //atualizar (put)

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarAparelho (Aparelhos aparelhos, @PathParam("id") int id) throws SQLException {
        aparelhosBO.atualizarAparelho(aparelhos);

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletaAparelho(@PathParam("id") int id) throws SQLException {
        aparelhosBO.excluirAparelho(id);
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Aparelhos> selecionaRS() throws SQLException {
        return (ArrayList<Aparelhos>) aparelhosBO.listarAparelhos();
    }


}
