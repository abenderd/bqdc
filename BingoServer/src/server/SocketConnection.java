package server;
/**Objeto para gerenciar um ou multiplos Sockets
 * @author 
 * @version 1.04
 * @since Release 01
 */

import banco.Conexao;
import banco.dao.CadastroDAO;
import banco.dbo.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class SocketConnection extends Thread {
    
    Socket socket;
    Server server;
    DataInputStream din;
    DataOutputStream dout;
    
    int[] sorteio = new int[74];
    int[] sorteados = new int[74];
    int qtsort = 0;
    boolean running = false;
    String email = "";
    
    CadastroDAO daos;
    CadastroDBO dbos;
    CadastroDBO dbos2;
    
    public SocketConnection(Socket socket, Server server){
        super("ServerConnectionThread");
        this.socket = socket;
        this.server = server;
    }
    
    
    /** Metodo para enviar string
     *   @param String*/
    public void sendString(String text){
        try{
            dout.writeUTF(text);
            dout.flush();
            if(running){
                qtsort++;
                try{
                    sorteados[qtsort] = Integer.parseInt(text);
                }
                catch(Exception e){
                    
                }
            }
            
        } catch(Exception e){
            
        }
    }
    
    /** Metodo para enviar texto a todos os clientes
     *   @param String*/
    public void sendAll(String text){
        for(int index = 0; index < server.conn.size(); index++){
            SocketConnection sc = server.conn.get(index);
            sc.sendString(text);
        }
    }
    
    public void run(){
        try{
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());
            
            while(true){//INFINITO
                while(din.available() == 0){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        
                    }
                }
                String textIn = din.readUTF();//ENTRADA DO CLIENTE
                
                //TENTATIVA DE LOGIN DO CLIENTE
                /*
                ===============================================
                RECEBE OS DADOS DO LOGIN DE JOGADOR
                RETORNA 1 SE O JOGADOR  FOR AUTORIZADO
                RETORNA 0 SE JOGADOR NAO FOR AUTORIZADO
                ===============================================
                */
                if(Integer.parseInt(textIn.substring(0,1)) == 1){
                    String email1 = din.readUTF();
                    this.email = email1;
                    String senha1 = din.readUTF();
                    
                    try
                    {
                        //CHECA SE USUARIO EXISTE
                        dbos2 = Conexao.caddaos.getEmail(email1);
                        
                        //CHECA SE SENHA DE USUARIO CONDIZ
                        if(dbos2.getSenha().equals(senha1)){
                            System.out.println("Usuario logado!");
                            dout.writeUTF("1");
                            sleep(100);

                            
                            
                            //CRIA ARRAY DE NUMEROS PARA ENVIAR 
                            for(int i = 0; i< 74; i++){
                                sorteio[i] = i+1;
                            }
                            //SHUFFLE
                            shuffleArray(sorteio);
                            //ENVIA NUMEROS PARA O CLIENTE
                            try{
                                for(int i = 0; i < 24; i++){
                                    sleep(100);
                                    dout.writeUTF(""+sorteio[i]);
                                }
                                running = true;
                            } 
                            catch(Exception e){}
                        }
                        else{
                            dout.writeUTF("0");
                        }
                    }
                    catch(Exception e){
                        dout.writeUTF("0");
                    }
                }
                /*
                ===============================================
                RECEBE OS DADOS DE NOVO JOGADOR PARA CADASTRO
                CADASTRAR NO BANCO DE DADOS
                ===============================================
                */
                if(Integer.parseInt(textIn.substring(0,1)) == 5){
                    System.out.println("CADASTRO NOVO "+textIn);
                    textIn = "0";
                    
                    String email = din.readUTF();
                    String senha = din.readUTF();
                    dbos=new CadastroDBO(email,senha);
                    
                    try{
                        Conexao.caddaos.cadastro(dbos);
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
                /*
                ===============================================
                BINGO!!
                ===============================================
                */
                if(Integer.parseInt(textIn.substring(0,1)) == 9){
                    
                    
                    int ok =0;
                    if(qtsort >= 23 && running){
                        for(int c = 0; c < 24; c++){
                            for(int e = 0; e <= (qtsort-1); e++){
                                if(sorteio[c] == sorteados[e]){
                                    ok++;
                                }
                            }
                        }
                    }
                    if(ok >= 23){
                        System.out.println("BINGO!");
                        sendAll(this.email);
                        qtsort = 0;
                    }
                    else{
                        System.out.println("Alarme Falso");
                        //sendAll("NOBINGO");
                    }
                    
                }
                //sendString(textIn);
            }
        } catch(IOException e){
            
        }
    }
    
    /** Metodo para embaralhar array
     *   @param int[]*/
    static void shuffleArray(int[] ar)
    {
      Random rnd = ThreadLocalRandom.current();
      for (int i = ar.length - 1; i > 0; i--)
      {
        int index = rnd.nextInt(i + 1);
        int a = ar[index];
        ar[index] = ar[i];
        ar[i] = a;
      }
    }
}
