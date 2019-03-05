/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5ex12;

import java.net.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author n0773624
 */
public class UserHandler implements Runnable {
    Socket client;
    DataOutputStream outToClient;
    DataInputStream inFromClient;
    
    public UserHandler(Socket aClient) throws IOException {
        client = aClient;
        outToClient = new DataOutputStream(client.getOutputStream());
        inFromClient = new DataInputStream(client.getInputStream());
    }
    
    public void run() {
        try{
            outToClient.writeUTF("this is the second type of client");
            String text = inFromClient.readUTF();
            System.out.println(text);
        }
        catch(IOException e){
            //
        }
    }
}
