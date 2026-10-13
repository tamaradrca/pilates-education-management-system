/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import communication.Reciver;
import communication.Request;
import communication.Response;
import communication.Sender;
import controller.Controller;
import domain.Edukacija;
import domain.Grupa;
import domain.Instruktor;
import domain.Klijent;
import domain.PripadanjeGrupi;
import domain.Zaposleni;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tamaradrca
 */
public class HandleClientThread extends Thread{
      private Socket socket;
    private Request request;
    private Response response;
    private Sender sender;
    private Reciver reciver;
    private boolean kraj = false;

    public HandleClientThread(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
        reciver = new Reciver(socket);
    }

    @Override
    public void run() {
        while (!kraj) {
            try {
                Object obj = reciver.recive();
                if (obj == null) {
                    System.out.println("Klijent je zatvorio vezu,nit se prekida.");
                    prekini();
                    break;
                }
                request = (Request) obj;
                response = new Response();
                
                switch (request.getOperation()) {
                    case LOGIN:
                        Zaposleni z = (Zaposleni) request.getArgument();
                        try {
                            Zaposleni ulogovan = Controller.getInstance().login(z);
                            response.setResult(ulogovan);
                        } catch (Exception ex) {
                            response.setException(new Exception(ex.getMessage()));
                        }
                        break;
                    case VRATILISTUINSTRUKTORA:
                        List<Instruktor> l = Controller.getInstance().vratiListuInstruktora();
                        response.setResult(l);
                        break;
                    case KREIRAJ_EDUKACIJU:
                        Edukacija e = (Edukacija) request.getArgument();
                        try{
                           Controller.getInstance().kreirajEdukaciju(e);
                           response.setResult("Uspesno je kreirana edukacija");
                        }catch(Exception ex){
                            response.setException(ex);
                        }
                        break;
                    case VRATI_LISTU_EDUKACIJA:
                        List<Edukacija> n = Controller.getInstance().vratiListuEdukacija();
                        response.setResult(n);
                        break;
                    case PRETRAGA_EDUKACIJA:
                        String ime = (String) request.getArgument();
                        try{
                            List<Edukacija> o = Controller.getInstance().pretragaEdukacija(ime);
                            response.setResult(o);
                        }catch(Exception ex){
                            response.setException(ex);
                        }
                        break;
                    case UCITAJ_EDUKACIJU:
                        Edukacija i = (Edukacija) request.getArgument();
                        try {
                            Edukacija ucitana = Controller.getInstance().ucitajEdukaciju(i);
                            response.setResult(ucitana);
                        } catch (Exception ex) {
                            response.setException(ex);
                        }
                        break;
                    case PROMENI_EDUKACIJU:
                        Edukacija v = (Edukacija) request.getArgument();
                        try{
                            Controller.getInstance().promeniEdukaciju(v);
                            response.setResult("Sistem je promenio podatke o edukaciji");
                        }catch(Exception ex){
                            response.setException(ex);
                        }
                        break;
                    case OBRISI_EDUKACIJU:
                        Edukacija h = (Edukacija) request.getArgument();
                        try{
                            Controller.getInstance().obrisiEdukaciju(h);
                            response.setResult("Sistem je obrisao edukaciju");

                        }catch(Exception ex){
                            response.setException(ex);
                        }
                        break;
                    case KREIRAJ_KLIJENTA:
                        Klijent k = (Klijent) request.getArgument();
                        try{
                            Controller.getInstance().kreirajKlijenta(k);
                            response.setResult("Sistem je uspesno kreirao klijenta");
                        }catch(Exception ex){
                            response.setException(ex);
                        }
                        break;
                    case PRETRAGA_KLIJENT:
                        String imeKlijent = (String) request.getArgument();
                        try {
                           List<Klijent> listaKlijent = Controller.getInstance().pretragaKlijent(imeKlijent);
                           response.setResult(listaKlijent);
                        }catch(Exception ex){
                            response.setException(ex);
                        }
                        break;
                    case VRATI_LISTU_KLIJENATA:
                        try {
                            List<Klijent> sviKlijenti = Controller.getInstance().vratiListuKlijenata();
                            response.setResult(sviKlijenti);
                        }catch(Exception ex){
                            response.setException(ex);
                        }
                        break;
                    case UCITAJ_KLIJENTA:
                        Klijent zaUcit = (Klijent) request.getArgument();
                        
                        try{
                            Klijent ucitan = Controller.getInstance().ucitajKlijenta(zaUcit);
                            response.setResult(ucitan);
                        }catch(Exception ex){
                            response.setException(ex);
                        }
                        break;
                    case PROMENI_KLIJENTA:
                        Klijent zaIzmenu = (Klijent) request.getArgument();
                        try{
                            Controller.getInstance().promeniKlijenta(zaIzmenu);
                            response.setResult("Sistem je izmenio podatke o klijentu");
                        }catch(Exception ex){
                            response.setException(ex);
                        }
                        break;
                    case OBRISI_KLIJENTA:
                        Klijent zaBrisanje = (Klijent) request.getArgument();
                        try {
                            Controller.getInstance().obrisiKlijenta(zaBrisanje);
                            response.setResult("Sistem je obrisao klijenta");
                        }catch(Exception ex){
                            response.setException(ex);
                        }
                        break;
                    case KREIRAJ_GRUPU:
                        Object[] niz1 = (Object[]) request.getArgument();
                        try {
                            Controller.getInstance().kreirajGrupu(niz1);
                            response.setResult("Sistem je kreirao grupi za pracenje edukacije");
                        } catch (Exception ex) {
                            response.setException(ex);
                        }
                        break;
                    case VRATI_LISTU_GRUPA:
                        try{
                            List<Grupa> listaGrupa = Controller.getInstance().vratiListuGrupa();
                            response.setResult(listaGrupa);
                        }catch(Exception ex){
                            response.setException(ex);
                        }
                        break;
                    case NADJI_GRUPE: 
                        String nazivEd = (String) request.getArgument();
                        try {
                            List<Grupa> pretGrupe = Controller.getInstance().pretragaGrupe(nazivEd);
                            response.setResult(pretGrupe);
                            
                        }catch(Exception ex){
                            response.setException(ex);
                        }
                        break;
                    case UCITAJ_GRUPU:
                        Grupa ucitaj = (Grupa) request.getArgument();
                        try{
                            Grupa ucitana = Controller.getInstance().ucitajGrupu(ucitaj);
                            response.setResult(ucitana);
                        }catch(Exception ex){
                            response.setException(ex);
                        }
                        break;
                    case VRATI_LISTU_PRIPADANJA:
                        Grupa j = (Grupa) request.getArgument();
                        try{
                            List<PripadanjeGrupi> lpg = Controller.getInstance().vratiListuPripadanja(j);
                            response.setResult(lpg);
                        }catch(Exception ex){
                            response.setException(ex);
                        }
                        break;
                    case PROMENI_GRUPU:
                        Object[] niz = (Object[]) request.getArgument();
                        try {
                            Controller.getInstance().promeniGrupu(niz);
                            response.setResult("Sistem je izmenio podatke o grupi za pracenje edukacije");
                        } catch (Exception ex) {
                            response.setException(ex);
                        }
                        break;
                    case KREIRAJ_INSTRUKTOR:
                        Instruktor d = (Instruktor) request.getArgument();
                        try{
                            Controller.getInstance().kreirajIns(d);
                            response.setResult("Instruktor je kreiran");
                        }catch(Exception ex){
                            response.setException(ex);
                        }
                        break;
                    case PRETRAGA_INSTRUKTOR:
                        
                        String imeIns = (String) request.getArgument();
                        List<Instruktor> kj = Controller.getInstance().pretragaIns(imeIns);
                        response.setResult(kj);
                        break;
                    case PROMENI_INSTRUKTOR:
                        Instruktor q = (Instruktor) request.getArgument();
                        Controller.getInstance().promeniIns(q);
                        response.setResult("Uspesna promena");
                        break;
                    case OBRISI_INSTRUKTOR:
                        Instruktor w = (Instruktor) request.getArgument();
                        try{
                        Controller.getInstance().obrisiIns(w);
                        }catch(Exception ex){
                            response.setException(ex);
                        }
                        break;
                    case ODJAVA:
                        Zaposleni y = (Zaposleni) request.getArgument();
                        Controller.getInstance().odjavi(y);
                        response.setResult("Uspesna odjava");
                        break;
                }
                sender.send(response);
            } catch (Exception ex) {
                ex.printStackTrace();
                prekini();
                break;
            }
        }
    }
    
    private void prekini() {
        kraj = true;
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(HandleClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        interrupt();

    }
    
}

    
