/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.edukacija;

import domain.Edukacija;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author tamaradrca
 */
public class ObrisiEdukacijuSO extends ApstraktnaGenerickaOperacija{
    
    private Edukacija m;

    @Override
    protected void preduslovi(Object param) throws Exception {
          if (param == null || !(param instanceof Edukacija)) {
                throw new Exception("Sistem ne moze da obrise edukaciju");

            }
       
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        Edukacija e = (Edukacija) param;
      
        
        broker.delete(e);
        
       
        
    }
    
}
