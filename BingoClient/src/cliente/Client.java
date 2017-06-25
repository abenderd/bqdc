/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import view.TelaLogin;

/**
 *
 * @author Pedro
 */
public class Client {
    
    DataInputStream din;
    DataOutputStream dou;
    int[] numeros = new int[24];

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        new Client();
    }
    
    private Socket socket;
    
    public Client(){
        
        try {
            //conecta
            Socket sok = new Socket("localhost", 9090);//FAZ CONEXAO COM O SERVIDOR (ALTERAR PARA A CONFIG DO LAB)
            socket = sok;
            din = new DataInputStream(sok.getInputStream());
            dou = new DataOutputStream(sok.getOutputStream());
            for(int i = 0; i < 3; i++){
                /*
                ===============================================
                AQUI SAO RECEBIDOS O JOGADORES
                DO RANK TOP 3 DO MES
                ADICIONAR FUNCAO QUE EXIBE O RANK NO FORM
                ===============================================
                */
                System.out.println(din.readUTF());//RECEBE O RANK DO TOP 3   ALTERARR
            }
        } catch (IOException ex) {
            
        }
        //ABRE JANELA 1 (LOGIN)
        TelaLogin jlogin = new TelaLogin(this.socket);
        jlogin.setVisible(true);
    }
}
