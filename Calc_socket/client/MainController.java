/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calc_Socket;

import java.io.*;
import java.lang.System.*;
import java.net.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
/**
 *
 * @author samue
 */
public class MainController {

    private MainApp mainApp;
        
    private char [] a = new char [30];  //array char contenente nuemri e operazione
    private int i=0;  //indice
    
    @FXML
    TextField textfield1;

    public MainController(){
    }

    void setMainApp(MainApp aThis) {
        this.mainApp=mainApp;
    }
    
    @FXML
    private void initialize(){ 
    }
    
    @FXML
    private void click_button1()
    {
        a[i] = '0';
        i++;
        textfield1.setText(String.valueOf(a));
    }
    @FXML
    private void click_button2()
    {
        a[i] = '1';
        i++;
        textfield1.setText(String.valueOf(a));
    }
    @FXML
    private void click_button3()
    {
        a[i] = '2';
        i++;
        textfield1.setText(String.valueOf(a));
    }
    @FXML
    private void click_button4()
    {
        a[i] = '3';
        i++;
        textfield1.setText(String.valueOf(a));
    }
    @FXML
    private void click_button5()
    {
        a[i] = '4';
        i++;
        textfield1.setText(String.valueOf(a));
    }
    @FXML
    private void click_button6()
    {
        a[i] = '5';
        i++;
        textfield1.setText(String.valueOf(a));
    }
    @FXML
    private void click_button7()
    {
        a[i] = '6';
        i++;
        textfield1.setText(String.valueOf(a));
    }
    @FXML
    private void click_button8()
    {
        a[i] = '7';
        i++;
        textfield1.setText(String.valueOf(a));
    }
    @FXML
    private void click_button9()
    {
        a[i] = '8';
        i++;
        textfield1.setText(String.valueOf(a));
    }
    @FXML
    private void click_button10()
    {
        a[i] = '9';
        i++;
        textfield1.setText(String.valueOf(a));
    }
    @FXML
    private void click_button11()
    {
        a[i] = '+';
        i++;
        textfield1.setText(String.valueOf(a));
    }
    @FXML
    private void click_button12()
    {
        a[i] = '-';
        i++;
        textfield1.setText(String.valueOf(a));
    }
    @FXML
    private void click_button13()
    {
        a[i] = '*';
        i++;
        textfield1.setText(String.valueOf(a));
    }
    @FXML
    private void click_button14()
    {
        a[i] = '/';
        i++;
        textfield1.setText(String.valueOf(a));
    }
    @FXML
    private void click_button15()
    {
        String z = "";
        a[i] = '=';
        i++;
        
        try
        {
            // Apre una connessione verso un server in ascolto sulla porta 14751. Si indirizzirà al dispositivo con indirizzo ip 192.168.178.119(pi4)
            System.out.println("Apertura connessione...");
            Socket s1 = new Socket ("192.168.178.119", 14751);

	    //Creazione oggetto per la ricezione dell'output dal server	
            PrintWriter out = new PrintWriter(s1.getOutputStream(),true);
            //Creazione oggetto per mandare al server un output
            BufferedReader inp = new BufferedReader(new InputStreamReader(s1.getInputStream()));

            int input = 0;

            while(input == 0)  //uso del while, perchè senza da problemi, ma esci subito
            {
                out.println(a);  //invio al server l'array di char
                z = inp.readLine();  //ricevo dal server il risultato dell'0perazione
                input++;  //così esce da while
            }

            // Al termine, chiude lo stream di comunicazione e il socket.
            s1.close();
            System.out.println("Chiusura connessione effettuata");
        }
        catch (ConnectException connExc)
        {
            System.err.println("Errore nella connessione ");
            System.err.println(connExc);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
         
         
        textfield1.setText(z);
        i=0; //viene resettato il "puntatore"
        empty_arr(); //funzione per eliminare il contenuto dell'array
    }
    @FXML
    private void click_button16()
    {
        if (i!=0)
        {
            a[i-1] = ' ';
            i--;
        }
        textfield1.setText(String.valueOf(a));
    }
    
    
    //FUNZIONE PER ELIMINARE IL CONTENUTO DELL'ARRAY
    public void empty_arr()
    {
        for (int i=0;i<11;i++)
        {
            a[i] = ' ';  //in ogni cella viene messo uno spazio vuoto
        }
    }
    
}
