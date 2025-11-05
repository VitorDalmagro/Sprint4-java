package br.com.guiahc;

import br.com.guiahc.api.VerificaSenha;
import br.com.guiahc.beans.Usuario;
import br.com.guiahc.bo.UsuarioBO;
import br.com.guiahc.bo.VerificarSenhaBO;
import br.com.guiahc.services.VerificaSenhaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;


import java.sql.SQLException;
import java.util.ArrayList;


@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {
    private final UsuarioBO usuarioBO = new UsuarioBO();
    private VerificarSenhaBO verificarSenhaBO = new VerificarSenhaBO();

    public UsuarioResource() throws SQLException, ClassNotFoundException {
    }

    // Selecionar todos
    @GET
    public ArrayList<Usuario> selecionarRs() throws ClassNotFoundException, SQLException, SQLException {
        return (ArrayList<Usuario>) usuarioBO.selecionarUsuarioBo();
    }

    // Inserir
    @POST
    public Response inserirRs(Usuario usuario, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        usuarioBO.inserirUsuarioBo(usuario, verificarSenhaBO);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(usuario.getIdUsuario()));
        return Response.created(builder.build()).build();
    }

    // Atualizar
    @PUT
    public Response atualizarRs(Usuario usuario) throws ClassNotFoundException, SQLException {
        usuarioBO.atualizarUsuarioBo(usuario);
        return Response.ok().build();
    }

    // Deletar
    @DELETE
    @Path("/{idUsuario}")
    public Response deletarRs(@PathParam("idUsuario") int idUsuario) throws ClassNotFoundException, SQLException {
        usuarioBO.deletarUsuarioBo(idUsuario);
        return Response.ok().build();
    }

    @GET
    @Path("/verificarSenha/{senha}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verificarSenha(@PathParam("senha") String senha) {
        try {
            VerificaSenhaService service = new VerificaSenhaService();
            VerificaSenha verificaSenha = service.getVerificarSenha(senha);
            VerificarSenhaBO bo = new VerificarSenhaBO();
            bo.salvar(verificaSenha);
            return Response.ok(verificaSenha).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao verificar e salvar senha: " + e.getMessage())
                    .build();
        }
    }
}
