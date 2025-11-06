package br.com.guiahc;

import br.com.guiahc.api.VerificaSenha;
import br.com.guiahc.beans.Usuario;
import br.com.guiahc.bo.UsuarioBO;
import br.com.guiahc.services.VerificaSenhaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.sql.SQLException;
import java.util.ArrayList;


// Usar consumes e produces antes, fica mais limpo, destacando os métodos POST, GET, PUT e DELETE
@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {
    private UsuarioBO usuarioBO = new UsuarioBO();

    public UsuarioResource() throws SQLException, ClassNotFoundException {}


    @GET
    public Response selecionarRs() {
        try {
            return Response.ok(usuarioBO.selecionarUsuarioBo()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar usuários: " + e.getMessage())
                    .build();
        }
    }


    @POST
    public Response inserirRs(Usuario usuario, @Context UriInfo uriInfo) {
        try {
            VerificaSenhaService service = new VerificaSenhaService();
            VerificaSenha verificaSenha = service.getVerificarSenha(usuario.getSenha());

            usuario.setVerificaSenha(verificaSenha);
            usuarioBO.inserirUsuarioBo(usuario, verificaSenha);

            return Response.status(Response.Status.CREATED)
                    .entity("Usuário cadastrado com sucesso!")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao inserir usuário: " + e.getMessage())
                    .build();
        }
    }



    @PUT
    public Response atualizarRs(Usuario usuario) throws ClassNotFoundException, SQLException {
        try {

            VerificaSenhaService service = new VerificaSenhaService();
            VerificaSenha verificaSenha = service.getVerificarSenha(usuario.getSenha());


            usuario.setVerificaSenha(verificaSenha);


            usuarioBO.atualizarUsuarioBo(usuario);

            return Response.ok("Usuário atualizado com sucesso!").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar usuário: " + e.getMessage())
                    .build();
        }
    }


    @DELETE
    @Path("/{idUsuario}")
    public Response deletarRs(@PathParam("idUsuario") int idUsuario) {
        try {
            usuarioBO.deletarUsuarioBo(idUsuario);
            return Response.ok("Usuário deletado com sucesso!").build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar usuário: " + e.getMessage())
                    .build();
        }
    }

}
