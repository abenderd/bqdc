package banco;

import javax.swing.JOptionPane;

import banco.dao.CadastroDAO;

public class Conexao {
   public static final MeuPreparedStatement comando;
   public static final CadastroDAO caddaos; 

    static
    {
    	MeuPreparedStatement _comando = null;
     	CadastroDAO     _caddaos  = null;

    	try 
        {
            //Class.forName("com.mysql.jdbc.Driver");
            _comando =
            new MeuPreparedStatement ("com.mysql.jdbc.Driver", 
					"jdbc:mysql://localhost/bd_aula", 
					"root", "" );

            _caddaos = new CadastroDAO ();
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null,"Erro de conexao com banco de dados\n"+erro);
            System.exit(0); 
        }
        
        comando = _comando;
        caddaos  = _caddaos; 
    }

}
