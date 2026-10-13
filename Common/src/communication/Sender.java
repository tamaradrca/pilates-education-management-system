/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author tamaradrca
 */
public class Sender {
    
        private Socket socket;

    public Sender(Socket socket) {
        this.socket = socket;
    }
    
    public void send(Object obj) throws Exception{
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(obj);
            out.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new Exception("Greska sa slanjem " + ex.getMessage());
        }
    }
    
}
