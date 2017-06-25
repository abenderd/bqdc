package banco.dao;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import banco.Conexao;
import banco.MeuResultSet;
import banco.dbo.CadastroDBO;


public class CadastroDAO {
    

public void cadastro (CadastroDBO dbos) throws Exception
{
	String dataAtual = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
    if(dbos==null)
        throw new Exception ("Cadastro nulo");

	try {
		String sql = "INSERT INTO tbl_jogador (email, senha, dataCadastro, qtdeVitorias) VALUES (?,?,?,?)";

        Conexao.comando.prepareStatement (sql);
        Conexao.comando.setString(1, dbos.getEmail());
        Conexao.comando.setString(2, dbos.getSenha());
        Conexao.comando.setString(3, dataAtual);
        Conexao.comando.setString(4, "0");
        Conexao.comando.executeUpdate ();
        Conexao.comando.commit        ();
        JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso!");
	}
	catch(SQLException e) {
    JOptionPane.showMessageDialog(null,"Erro ao realizar cadastro." + e);
    }   
}


public CadastroDBO getEmail(String usuario) throws SQLException, Exception
{
	CadastroDBO cadastroDbo = null;
    String sql;

    try {
    	sql = "SELECT * " +
              "FROM tbl_jogador " +
                  "WHERE email = ?";

            Conexao.comando.prepareStatement (sql);

            Conexao.comando.setString (1, usuario);

            MeuResultSet resultado = (MeuResultSet)Conexao.comando.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Usuario nao cadastrado.");

            cadastroDbo = new CadastroDBO (resultado.getString("Email"),
                                    resultado.getString("Senha"));
                             
        }
       catch (Exception erro) {
             erro.printStackTrace();
        }

        return cadastroDbo;

}
}
