package fiap.tds.resources;

import fiap.tds.model.bo.FeedbackBO;
import fiap.tds.model.vo.Endereco;
import fiap.tds.model.vo.Feedback;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;

@Path("/feedback")
public class FeedbackResource {

    private FeedbackBO feedbackBO;

    public FeedbackResource() throws SQLException {
        this.feedbackBO = new FeedbackBO();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response adicionarFeedback(Feedback feedback, @Context UriInfo uriInfo) throws SQLException {
        feedbackBO.inserirFeedback(feedback);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(feedback.getId_feedback()));
        return Response.created(builder.build()).entity("Feedback enviado com sucesso!").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response atualizarFeedback(@PathParam("id") int id, Feedback feedback) throws SQLException {
        feedback.setId_feedback(id);
        feedbackBO.atualizarFeedback(feedback);
        return Response.ok("Feedback atualizado com sucesso!").build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response excluirFeedback(@PathParam("id") int id) throws SQLException {
        feedbackBO.excluirFeedback(id);
        return Response.ok("Feedback excluído com sucesso!").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarFeedback() throws SQLException {
        List<Feedback> feedbacks = feedbackBO.listarFeedbacks();
        return Response.ok(feedbacks).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarFeedbackPorId(@PathParam("id") int id) {
        try {
            Feedback feedback = feedbackBO.buscarFeedbackPorId(id);
            return Response.ok(feedback).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Feedback não encontrado.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno do servidor.").build();
        }
    }


    @GET
    @Path("/getByClient/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarFeedbackPorIdCliente(@PathParam("id") int id) throws SQLException {
        Feedback feedback = feedbackBO.buscarFeedbackPorIdCliente(id);
        return Response.ok(feedback).build();
    }
}
