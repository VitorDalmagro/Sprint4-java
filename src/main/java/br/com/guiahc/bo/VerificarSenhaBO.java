package br.com.guiahc.bo;

import br.com.guiahc.DAO.VerificarSenhaDAO;
import br.com.guiahc.api.VerificaSenha;

import java.sql.SQLException;

public class VerificarSenhaBO {

    private final VerificarSenhaDAO dao = new VerificarSenhaDAO();

    public VerificarSenhaBO() throws SQLException, ClassNotFoundException {
    }

    public void salvar(VerificaSenha verificaSenha) throws SQLException, ClassNotFoundException {
        dao.inserir(verificaSenha);
    }
}
