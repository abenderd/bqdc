/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.DataOutputStream;
import static java.lang.Thread.sleep;

import java.awt.Color;
import java.awt.Font;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class TelaCadastro extends javax.swing.JFrame {

	private JPanel contentPane;
	private Socket socket;
    
    
    public TelaCadastro(Socket socket) {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 189);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
    	
    	
        initComponents();
        this.socket = socket;
    }
    
    protected String login = "";
    protected String senha = "";
    protected int confirmacaoSenha = 0;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        lblEmail = new javax.swing.JLabel();
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(28, 37, 46, 14);
		contentPane.add(lblEmail);
		
        textFieldEmail = new javax.swing.JTextField();
		textFieldEmail.setBounds(89, 34, 384, 20);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
        
        
        
        lblSenha = new javax.swing.JLabel();
        lblSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSenha.setBounds(28, 66, 51, 14);
		contentPane.add(lblSenha);
		
        passwordField = new JPasswordField();
        passwordField.setBounds(89, 65, 137, 20);
		contentPane.add(passwordField);
		
        btnCadastrar = new javax.swing.JButton();
        btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCadastrar.setBounds(114, 110, 103, 23);
		contentPane.add(btnCadastrar);
		
        btnCancelar = new javax.swing.JButton();
        btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancelar.setBounds(278, 110, 103, 23);
		contentPane.add(btnCancelar);
		
        passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(336, 65, 137, 20);
		contentPane.add(passwordField_1);
        
        lblConfirmaoDeSenha = new javax.swing.JLabel();
		lblConfirmaoDeSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConfirmaoDeSenha.setBounds(236, 66, 99, 14);
		contentPane.add(lblConfirmaoDeSenha);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblEmail.setText("Email");
        
        lblConfirmaoDeSenha.setText("Confirma\u00E7\u00E3o:");

        textFieldEmail.setText("");

        lblSenha.setText("Senha");

        passwordField.setText("");

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        passwordField_1.setText("");
    }

        
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        this.setVisible(false);
    }                                        

    
    //CADASTRO DE CLIENTES
    /*
    ===============================================
    ENVIA OS DADOS DO CADASTRO PARA O SERVIDOR
    ===============================================
    */
    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {
    	String email = textFieldEmail.getText();
		String senha = passwordField.getText();
		String confirmacaoSenha = passwordField_1.getText();
    	
    	if (email.isEmpty()){
			JOptionPane.showMessageDialog(null, "Campo email deve ser preenchido.");
			limparCampos();
		}
		else if (validaEmail(email) == false){
			JOptionPane.showMessageDialog(null, "Email invalido");
			limparCampos();
		}
		else if (senha.isEmpty()){
			JOptionPane.showMessageDialog(null, "Campo senha deve ser preenchido.");
			limparCampos();
		}
		else if (confirmacaoSenha.isEmpty()){
			JOptionPane.showMessageDialog(null, "Campo confirma��o de senha deve ser preenchido.");
			limparCampos();
		}
    	
        if(passwordField.getText().equals(passwordField_1.getText())){
            try{
            //DataInputStream din = new DataInputStream(sok.getInputStream());
            DataOutputStream dou = new DataOutputStream(this.socket.getOutputStream());
            
            dou.writeUTF("5");
            dou.writeUTF(textFieldEmail.getText());
            dou.writeUTF(passwordField.getText());
            this.setVisible(false);
            }
            catch(Exception e){
            
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "As senhas devem ser iguais", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }                                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastro(new Socket()).setVisible(true);
            }
        });
    }
    
    public void limparCampos() {
		textFieldEmail.setText("");
		passwordField.setText("");
		passwordField_1.setText("");
	}
    
    public boolean validaEmail(String email) {
    	{
            boolean isEmailIdValid = false;
            if (email != null && email.length() > 0) {
                String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(email);
                if (matcher.matches()) {
                    isEmailIdValid = true;
                }
            }
            return isEmailIdValid;
        }
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblConfirmaoDeSenha;
    private javax.swing.JTextField textFieldEmail;
    private javax.swing.JTextField passwordField;
    private javax.swing.JTextField passwordField_1;
    // End of variables declaration                   
}