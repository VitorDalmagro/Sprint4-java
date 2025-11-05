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
    private final UsuarioBO usuarioBO = new UsuarioBO();

    public UsuarioResource() throws SQLException, ClassNotFoundException {}

    // Selecionar todos
    @GET
    public ArrayList<Usuario> selecionarRs() throws ClassNotFoundException, SQLException {
        return (ArrayList<Usuario>) usuarioBO.selecionarUsuarioBo();
    }

    // Inserir
    @POST
    public Response inserirRs(Usuario usuario, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        try {
            // 1️⃣ Verificar a senha usando a API
            VerificaSenhaService service = new VerificaSenhaService();
            VerificaSenha verificaSenha = service.getVerificarSenha(usuario.getSenha());

            // 2️⃣ Atribuir ao usuário
            usuario.setVerificaSenha(verificaSenha);

            // 3️⃣ Inserir no banco
            usuarioBO.inserirUsuarioBo(usuario, verificaSenha);

            // 4️⃣ Construir URI do recurso criado
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Integer.toString(usuario.getIdUsuario()));
            return Response.created(builder.build()).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao inserir usuário e verificar senha: " + e.getMessage())
                    .build();
        }
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
}
