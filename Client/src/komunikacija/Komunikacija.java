/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import communication.Operation;
import communication.Reciver;
import communication.Request;
import communication.Response;
import communication.Sender;
import domain.Edukacija;
import domain.Grupa;
import domain.Instruktor;
import domain.Klijent;
import domain.PripadanjeGrupi;
import domain.Zaposleni;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tamaradrca
 */
public class Komunikacija {
    
    private static Komunikacija instance;
    private Socket socket;
    private Sender sender;
    private Reciver reciver;
    private Zaposleni ulogovan;

    public Komunikacija() {
        try {
            socket = new Socket("localhost" , 9000);
            sender = new Sender(socket);
            reciver = new Reciver(socket);
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Komunikacija getInstance() {
        if(instance==null){
            instance = new Komunikacija();
        }
        return instance;
    }

    public Zaposleni login(String imejl, String lozinka) throws Exception {
        Zaposleni zaposleni = new Zaposleni(imejl,lozinka);
        Request request = new Request(Operation.LOGIN, zaposleni);
        
        sender.send(request);
        
        Response response = (Response) reciver.recive();
        if(response.getException()==null){
            ulogovan = (Zaposleni) response.getResult();
            return (Zaposleni) response.getResult();
        }else {
            throw response.getException();
        }
        
    }

    public Zaposleni getUlogovani() {
        return ulogovan;
    }

    public List<Instruktor> vratiListuInstruktora() throws Exception {
        Request request = new Request(Operation.VRATILISTUINSTRUKTORA,"");
        
        sender.send(request);
        
        Response response = (Response) reciver.recive();
        if(response.getException()==null){
            return (List<Instruktor>) response.getResult();
        } else {
            throw response.getException();
        }
        
    }

    public String kreirajEdukaciju(Edukacija e) throws Exception {
        Request request = new Request(Operation.KREIRAJ_EDUKACIJU,e);
        sender.send(request);
          Response response = (Response) reciver.recive();
        if(response.getException()==null){
            return (String) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Edukacija> vratiListuEdukacija() throws Exception {
        Request request = new Request(Operation.VRATI_LISTU_EDUKACIJA,"");
        sender.send(request);
        Response response = (Response) reciver.recive();
        if(response.getException()==null){
            return (List<Edukacija>) response.getResult();
        }else {
            throw response.getException();
        }
    }

    public List<Edukacija> pretragaPoImenu(String imeIns) throws Exception {
        Request request = new Request(Operation.PRETRAGA_EDUKACIJA, imeIns);
        sender.send(request);
        Response response = (Response) reciver.recive();
        if(response.getException()==null){
            return (List<Edukacija>) response.getResult();
        }else {
            throw response.getException();
        }
        
    }

    public Edukacija ucitajEdukaciju(Edukacija e) throws Exception {
        Request request = new Request(Operation.UCITAJ_EDUKACIJU, e);
        sender.send(request);
        Response response = (Response) reciver.recive();
        if(response.getException()==null){
            return (Edukacija) response.getResult();
        }else {
            throw response.getException();
        }
    }

    public String promeniEdukacija(Edukacija e) throws Exception {
        Request request = new Request(Operation.PROMENI_EDUKACIJU,e);
        sender.send(request);
        Response response = (Response) reciver.recive();
        if(response.getException()==null){
            return (String) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public String obrisiEdukaciju(Edukacija ta) throws Exception {
        Request request = new Request(Operation.OBRISI_EDUKACIJU,ta);
        sender.send(request);
        Response response = (Response) reciver.recive();
        if(response.getException()==null){
            return (String) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public String kreirajKlijent(Klijent k) throws Exception {
        Request request = new Request(Operation.KREIRAJ_KLIJENTA, k);
        sender.send(request);
        Response response = (Response) reciver.recive();
        if (response.getException() == null) {
            return (String) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Klijent> pretragaKlijent(String ime) throws Exception {
        Request request = new Request(Operation.PRETRAGA_KLIJENT, ime);
        sender.send(request);
        Response response = (Response) reciver.recive();
        if(response.getException()==null){
            return (List<Klijent>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Klijent> vratiListuKlijenata() throws Exception {
        Request request = new Request(Operation.VRATI_LISTU_KLIJENATA, "");
        sender.send(request);
        Response response = (Response) reciver.recive();
        if(response.getException()==null){
            return (List<Klijent>) response.getResult();
        } else {
            throw response.getException();
    }
    }

    public Klijent ucitajKlijenta(Klijent u) throws Exception {
        Request request = new Request(Operation.UCITAJ_KLIJENTA,u);
        sender.send(request);
           Response response = (Response) reciver.recive();
        if(response.getException()==null){
            return (Klijent) response.getResult();
        } else {
            throw response.getException();
    }
    }

    public String promeniKlijent(Klijent k) throws Exception {
        Request request = new Request(Operation.PROMENI_KLIJENTA, k);
        sender.send(request);
        Response response = (Response) reciver.recive();
        if (response.getException() == null) {
            return (String) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public String obrisiKlijenta(Klijent k) throws Exception {
        Request request = new Request(Operation.OBRISI_KLIJENTA, k);
        sender.send(request);
        Response response = (Response) reciver.recive();
        if (response.getException() == null) {
            return (String) response.getResult();
        } else {
            throw response.getException();
        }
        
    }

    public String kreirajGrupu(Object obj) throws Exception {
        Request request = new Request(Operation.KREIRAJ_GRUPU,obj);
        sender.send(request);
        Response response = (Response) reciver.recive();
        if(response.getException()==null){
            return (String) response.getResult();
        }else{
            throw response.getException();
        }
    }

    public List<Grupa> vratiListuGrupa() throws Exception {
           Request request = new Request(Operation.VRATI_LISTU_GRUPA, "");
        sender.send(request);
        Response response = (Response) reciver.recive();
        if (response.getException() == null) {
            return  (List<Grupa>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Grupa> pretragaGrupe(String naziv) throws Exception {
        Request request = new Request(Operation.NADJI_GRUPE, naziv);
        sender.send(request);
        Response response = (Response) reciver.recive();
        if(response.getException()==null){
            return (List<Grupa>) response.getResult();
        }else {
            throw response.getException();
        }
        
    }

    public Grupa ucitajGrupu(Grupa e) throws Exception {
        Request request = new Request(Operation.UCITAJ_GRUPU,e);
        sender.send(request);
           Response response = (Response) reciver.recive();
        if(response.getException()==null){
            return (Grupa) response.getResult();
        } else {
            throw response.getException();
    }
    }

    public List<PripadanjeGrupi> vratiListuPripadanja(Grupa grupa) throws Exception {
        Request request = new Request(Operation.VRATI_LISTU_PRIPADANJA, grupa);
        sender.send(request);
        Response response = (Response) reciver.recive();
        if (response.getException() == null) {
            return  (List<PripadanjeGrupi>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public String promeniGrupu(Object[] zahtev) throws Exception {
        Request request = new Request(Operation.PROMENI_GRUPU,zahtev);
        sender.send(request);
        Response response = (Response) reciver.recive();
        if(response.getException()==null){
            return (String) response.getResult();
        }else{
            throw response.getException();
        }
    }

    public String kreirajIns(Instruktor i) throws Exception {
        Request request = new Request(Operation.KREIRAJ_INSTRUKTOR,i);
        sender.send(request);
        Response response = (Response) reciver.recive();
        if(response.getException()==null){
            return (String) response.getResult();
        }else{
            throw response.getException();
        }
    }

    public String odjaviZap() throws Exception {
        Request request = new Request(Operation.ODJAVA,ulogovan);
        sender.send(request);
        Response response = (Response) reciver.recive();
        if(response.getException()==null){
             return (String) response.getResult();
        }else{
            throw response.getException();
        }
    }

    public List<Instruktor> pretraziIns(String ime) throws Exception {
        Request request = new Request(Operation.PRETRAGA_INSTRUKTOR,ime);
        sender.send(request);
        Response response = (Response) reciver.recive();
        if(response.getException()==null){
            return (List<Instruktor>) response.getResult();
        }else {
            throw response.getException();
        }
    }

    public String promeniInstruktor(Instruktor u) throws Exception {
        Request request = new Request(Operation.PROMENI_INSTRUKTOR,u);
        sender.send(request);
        Response response = (Response) reciver.recive();
        if(response.getException()==null){
            return (String) response.getResult();
        }else{
            throw response.getException();
        }
    }

    public String obrisiInstruktora() throws Exception {
         Request request = new Request(Operation.OBRISI_INSTRUKTOR,"");
        sender.send(request);
        Response response = (Response) reciver.recive();
        if(response.getException()==null){
            return (String) response.getResult();
        }else{
            throw response.getException();
        }
    }

    
    
    
    
}
