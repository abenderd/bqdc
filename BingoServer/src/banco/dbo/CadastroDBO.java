package banco.dbo;

import javax.swing.JOptionPane;

import banco.dao.CadastroDAO;

public class CadastroDBO {
    CadastroDAO caddaos=new CadastroDAO();
    
   
 private String senha;
 private String email;

    public CadastroDBO(String email, String senha) {
       
        this.email = email;
        this.senha = senha;
    }
 
 public void cadastroEmail(String email)
 {
     if(this.email==null || " ".equals(email))
     {
         JOptionPane.showMessageDialog(null,"Email invalido ou nulo");
     }
     else{
         this.email=email;
     }
     
 }
 public   void cadastroSenha(String senha)
 {
     if(this.senha==null||"".equals(senha))
     {
         JOptionPane.showMessageDialog(null,"senha invalida");
     }
     else{
         this.senha=senha;
     }
 }
 public void cadastroCsenha(String senha,String csenha) throws Exception
 {
     if(this.senha.equals(csenha))
     {
     }
     else
     {
            JOptionPane.showMessageDialog(null,"Cadastro nao realizado senhas incompativeis");
     }
 }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }

}
