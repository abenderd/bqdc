/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author Pedro
 */
public class SSConnection {
    
    //URSS
    DataInputStream din;
    DataOutputStream dou;
    
    public void send(Socket sok, String msg){
        try{
            //DataInputStream din = new DataInputStream(sok.getInputStream());
            DataOutputStream dou = new DataOutputStream(sok.getOutputStream());
            
            dou.writeUTF(msg);
        }
        catch(Exception e){
            
        }
    }
}