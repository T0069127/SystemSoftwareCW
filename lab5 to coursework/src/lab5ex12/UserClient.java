/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5ex12;

import java.net.*;
import java.io.*;

/**
 *
 * @author n0773624
 */
public class UserClient {

public static void main(String[] args) {
        try{
            InetAddress address = InetAddress.getByName("localhost");
            Socket server = new Socket(address,9090);
            
            System.out.println("Just connected to "+ server.getInetAddress());
            
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream outToServer = new DataOutputStream(server.getOutputStream());
            
            String clientType = "user";
            outToServer.writeUTF(clientType);
            
            DataInputStream inFromServer = new DataInputStream(server.getInputStream());
            String text = inFromServer.readUTF();
            System.out.println(text);
            
            System.out.println("enter a message...");
            
            String sendMessage = inFromUser.readLine();
            outToServer.writeUTF(sendMessage);
        }
        catch(IOException e){
            System.out.println("Error!");
            e.printStackTrace();
        }
    }
    
}
