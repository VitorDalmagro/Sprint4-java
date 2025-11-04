package br.com.guiahc;

import br.com.guiahc.beans.Usuario;
import br.com.guiahc.bo.UsuarioBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;


import java.sql.SQLException;
import java.util.ArrayList;


@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {
    private final UsuarioBO usuarioBO = new UsuarioBO();

    // Selecionar todos
    @GET
    public ArrayList<Usuario> selecionarRs() throws ClassNotFoundException, SQLException, SQLException {
        return (ArrayList<Usuario>) usuarioBO.selecionarBo();
    }

    // Inserir
    @POST
    public Response inserirRs(Usuario usuario, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        usuarioBO.inserirBo(usuario);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(usuario.getIdUsuario()));
        return Response.created(builder.build()).build();
    }

    // Atualizar
    @PUT
    public Response atualizarRs(Usuario usuario) throws ClassNotFoundException, SQLException {
        usuarioBO.atualizarBo(usuario);
        return Response.ok().build();
    }

    // Deletar
    @DELETE
    @Path("/{idUsuario}")
    public Response deletarRs(@PathParam("idUsuario") int idUsuario) throws ClassNotFoundException, SQLException {
        usuarioBO.deletarBo(idUsuario);
        return Response.ok().build();
    }
}
