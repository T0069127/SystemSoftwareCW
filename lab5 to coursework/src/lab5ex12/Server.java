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
public class Server {
    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(9090);
            while(true){
                Socket client = server.accept();
                System.out.println("Just connected to "+ client.getInetAddress());
                
                DataInputStream clientType = new DataInputStream(client.getInputStream());
               
                String type = clientType.readUTF();
                
                //list of all connected ids
                
                if (type.equals("weather")){
                    String id = clientType.readUTF();
                    // add id to connected list
                    // check this id isn't already connected
                    System.out.println("Welcome, weather client " + id);
                    WeatherHandler handler = new WeatherHandler(client);
                    Thread t = new Thread(handler);
                    t.start();
                    String weatherData = handler.getData();
                    System.out.println("Weather data: " + weatherData);
                    // remove id from connected list after disconnect
                }
                else if (type.equals("user")){
                    System.out.println("Welcome, user client");
                    UserHandler handler = new UserHandler(client);
                    Thread t = new Thread(handler);
                    t.start();
                }
                else{
                    System.out.println("Error; unexpected client type");
                }
            }
        }
        catch(IOException e){
            System.out.println("Error!");
            e.printStackTrace();
        }
    }
}
