/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import banco.dao.*;
import banco.dbo.*;;


public class Server implements Runnable{

    ServerSocket ss;
    ArrayList<SocketConnection> conn = new ArrayList<SocketConnection>();
    private static final int PORT = 9090;
    
    public static void main(String[] args) {
        new Server();
    }
    
    public Server(){
        //CONECTAR AO BD
        
        
        TelaInicio frame = new TelaInicio();
        frame.setVisible(true);
        try{
            ss = new ServerSocket(PORT);
            while(true){
                Socket s = ss.accept();
                SocketConnection sc = new SocketConnection(s, this);
                sc.start();
                conn.add(sc);
                //envia o top 3 rank
                for(int i = 0; i < 3; i++){
                    sleep(100);
                    /*
                    ===============================================
                    AQUI VOCE DEVE
                    ENVIAR OS TOP 3 DO RANK MENSAL
                    ===============================================
                    */
                    sc.sendString("Ranking Vencedores");
                }
                synchronized(frame){
                    //manda pra tela
                frame.conn = this.conn;
              }
                System.out.println("addres added");//CONTROLE DEBUG
            }
        } catch(Exception e){
            
        }
    }
    
    @Override
    public void run(){
        System.out.println("Rodando Server");
    }
}
