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
public class WeatherClient {

public static void main(String[] args) {
    
    String clientType = "weather";
    
        try{
            InetAddress address = InetAddress.getByName("localhost");
            Socket server = new Socket(address,9090);
            
            System.out.println("Just connected to "+ server.getInetAddress());
            
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream outToServer = new DataOutputStream(server.getOutputStream());
            
            outToServer.writeUTF(clientType);
            
            System.out.println("Please enter weather station ID: ");
            String id = inFromUser.readLine();
            outToServer.writeUTF(id);
            
            DataInputStream inFromServer = new DataInputStream(server.getInputStream());
            String text = inFromServer.readUTF();
            System.out.println(text);
            
            String exit = "";
            
            while(!exit.equals("exit")){
                System.out.println("enter weather data: ");
                String sendMessage = inFromUser.readLine();
                exit = sendMessage; //if the user types exit, stop the loop
                outToServer.writeUTF(sendMessage);
            }
            
            
        }
        catch(IOException e){
            System.out.println("Error!");
            e.printStackTrace();
        }
    }
    
}
