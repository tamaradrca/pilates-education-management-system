/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.HandleClientThread;

/**
 *
 * @author tamaradrca
 */
public class Server extends Thread{
        private ServerSocket serverSocket;
    private Socket socket;
    private List<HandleClientThread> niti = new ArrayList<>();

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(9000);
            System.out.println("Cekam klijenta...");
            while(true){
                socket = serverSocket.accept();
                System.out.println("Klijent je povezan");
                HandleClientThread nit = new HandleClientThread(socket);
                niti.add(nit);
                nit.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void zaustaviServer() {
        try{
        for(HandleClientThread nit : niti){
            nit.interrupt();
        }
        niti.clear();
        if(serverSocket!=null && !serverSocket.isClosed()){
            serverSocket.close();
        }
        this.interrupt();
        System.out.println("Server je uspesno zaustavljen!");
       }
       catch(IOException ex){
           ex.printStackTrace();
       }
    }
}
