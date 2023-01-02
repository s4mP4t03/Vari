/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Autonoleggio;

import java.util.List;

/**
 *
 * @author samue
 */
public class CarRented {
    private String targa;
    private String marca;
    private String modello;
    private String noleggiatore;
    
    public CarRented (List lista)
    {
        this.targa = (String)lista.get(0);
        this.marca = (String)lista.get(1);
        this.modello = (String)lista.get(2);
        this.noleggiatore = (String)lista.get(5);
    }

    public CarRented() {
    }
    

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getNoleggiatore() {
        return noleggiatore;
    }


    public String getTarga() {
        return targa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModello() {
        return modello;
    }

    public void setNoleggiatore(String noleggiatore) {
        this.noleggiatore = noleggiatore;
    }


    
}
