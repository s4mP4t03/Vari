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
public class CarModel {
    private String targa;
    private String marca;
    private String modello;
    private int prezzo;
    private String avaible;
    
    public CarModel(List lista)
    {
        this.targa = (String)lista.get(0);
        this.marca = (String)lista.get(1);
        this.modello = (String)lista.get(2);
        this.prezzo = Integer.parseInt((String)lista.get(3));
        this.avaible = (String)lista.get(4);
    }

    public CarModel() {
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

    public int getPrezzo() {
        return prezzo;
    }

    public String getAvaible() {
        return avaible;
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

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    public void setAvaible(String avaible) {
        this.avaible = avaible;
    }
}
