/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Autonoleggio;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author samue
 */
public class MainController {
    //apertura file, attraversi la classe readcssv, inizializzo una lista con una lista globale della classe readcsv. Dichiaro un array di liste con dimensione uguale a quella della lista contenente il fiel
    readcsv obj = new readcsv("src/Autonoleggio/macchine.csv");
    List list = obj.list1;
    int size_list = list.size();
    List [] lista = new List [size_list];

    private MainApp mainApp;
    //dichiarazione variabile per interfaccia con scene builder della prima pagina
    @FXML
     TableView tabella;
    @FXML
     TableColumn targa;
    @FXML
     TableColumn marca;
    @FXML
     TableColumn modello;
    @FXML
     TableColumn prezzo;
    @FXML
     TableColumn avaible;
    @FXML
     TextField ricerca;
    @FXML
     TextField targa_noleggio;
    @FXML
     TextField nomecognome;
    @FXML
     TextArea areatesto;
    //dichiarazione variabile per interfaccia con scene builder della seconda pagina
    @FXML
     TableView tabella1;
    @FXML
     TableColumn targa1;
    @FXML
     TableColumn marca1;
    @FXML
     TableColumn modello1;
    @FXML
     TableColumn noleggiatore;

    void setMainApp(MainApp aThis) {
        this.mainApp=mainApp;

    }
    
    @FXML
    private void initialize(){        
        //assegnamento nomi colonne prima pagina
        targa.setCellValueFactory(new PropertyValueFactory <>("targa"));
        marca.setCellValueFactory(new PropertyValueFactory <>("marca"));
        modello.setCellValueFactory(new PropertyValueFactory <>("modello"));
        prezzo.setCellValueFactory(new PropertyValueFactory <>("prezzo"));
        avaible.setCellValueFactory(new PropertyValueFactory <>("avaible"));
        //assegnamento nomi colonne seconda pagina
        targa1.setCellValueFactory(new PropertyValueFactory <>("targa"));
        marca1.setCellValueFactory(new PropertyValueFactory <>("marca"));
        modello1.setCellValueFactory(new PropertyValueFactory <>("modello"));
        noleggiatore.setCellValueFactory(new PropertyValueFactory <>("noleggiatore"));
        
        //eliminazione di tutte le righe
        tabella.getItems().clear();   
        
        tabella.getItems().add(new CarModel());
        tabella1.getItems().add(new CarModel());
        
        //richiamo funzione per creazione di sotto liste
        lista = sub_list(list, lista);

    }
    
    
    //mostra il contenuto del file macchine.csv, senza alcun tipo di filtri
    @FXML
    private void garage(){     
        //eliminazione di tutte le righe
        tabella.getItems().clear();        
        //inserimento righe all'interno della tabella
        for (int i=0;i<lista.length;i++)
        {
            tabella.getItems().add(new CarModel(lista[i]));
        }
    }
    
    
    //mostra contenuto file con filtro solo macchine disponibili
    @FXML
    private void availability()
    {
        //eliminazione di tutte le righe
        tabella.getItems().clear();
        //richiamo funzione che individua la posizione all'interno di lista, di tutte le macchine che hanno libera come disponibilità
        int [] arr = find("libera");
        //aggiunge gli elementi individuati prima e li inserisce nella tabella della prima pagina
        add_element_table(arr);   
    }
    
    
    //mostra contenuto file con filtro solo macchine noleggiate
    @FXML
    private void noleggiate()
    {
        //eliminazione di tutte le righe
        tabella.getItems().clear();
        //richiamo funzione che individua la posizione all'interno di lista, di tutte le macchine che hanno noleggiata come disponibilità
        int [] arr = find("noleggiata");
        //aggiunge gli elementi individuati prima e li inserisce nella tabella della prima pagina
        add_element_table(arr);
    }
    
    
    //permette di filrare cosa mostrare in tabella in base a quello che inserisci nel relativo textfield
    @FXML
    private void ricerca()
    {
        //eliminazione di tutte le righe
        tabella.getItems().clear();
        //prende il testo dalla textfield ricerca e lo mette in una stringa
        String to_find = ricerca.getText();
        //richiamo funzione che individua la posizione all'interno di lista, di tutte le macchine che hanno l'attributo inserito
        int [] arr = find(to_find);
        //aggiunge gli elementi individuati prima e li inserisce nella tabella della prima pagina
        add_element_table(arr);
        
    }
    
    
    //noleggia una macchina, per cui modifica la disponibilità della macchina
    @FXML
    private void noleggia() throws IOException, ParseException
    {
        Data obj2 = new Data();
        String data = obj2.data;
        //modifica la disponibilità della macchina da libera a nolrggiata
        gestione_noleggi("noleggiata", "libera", data);
    }
    
    
    //libera una macchina, per cui modifica la disponibilibà da noleggiata a libera
    @FXML
    private void libera_noleggi() throws IOException, ParseException
    {
        Data obj3 = new Data("Wed Mar 19 19:09:39 CET 2022");
        long diff = obj3.diff_data;
        //modifica la disponibilità della macchina da noleggiata a libera
        gestione_noleggi("libera", "noleggiata", "");
    }
    
    
    //mostra i noleggi attivi nella tabella due, con il nome del noleggiatore
    @FXML
    private void noleggi_attivi()
    {
        //eliminazione di tutte le righe
        tabella1.getItems().clear();
        //richiamo funzione che individua la posizione all'interno di lista, di tutte le macchine che hanno noleggiata come disponibilità
        int [] arr = find("noleggiata");
        //se l'array di lista non ha allocato nulla, viene creata una riga vuota sulla tabella
        if (arr.length == 0)
        {
            tabella1.getItems().add(new CarRented());
        }
        else
        {
            //richiamo funzione che alloca nella prima tabella le macchine disponibili
            stampa_CarRented(arr);
        }    
    }
    
    
    //funzione creazione sottoliste, la lista madre è quella contenuta nel csv
    private List [] sub_list(List madre, List [] figlio)
    {
        for (int i=0;i<madre.size();i++)
        {
            figlio[i] = (List)madre.get(i);
        }
        return figlio;
    }

    
    //funzione che alloca nella prima tabella le macchine disponibili
    private void stampa_CarModel(int [] array)
    {
        for (int i=0;i<array.length;i++)
        {
            tabella.getItems().add(new CarModel(lista[array[i]]));
        }
    }
    
    
    //funzione che alloca nella seconda tabella le macchine noleggiate
    private void stampa_CarRented(int [] array)
    {
        for (int i=0;i<array.length;i++)
        {
            
                tabella1.getItems().add(new CarRented(lista[array[i]]));
            
        }
    }
    
    
    //funzione che restituisce quante volte è contenuta una certa stringa nell'array di liste
    private int contiene(List[] lista, String var)
    {
        int j=0;
        for (int i=0;i<lista.length;i++)
        {
            if (lista[i].contains(var))
            {
                j++;
            } 
        }
        return j;
    }
    
    //funzione che individua la posizione all'interno di lista di una data stringa, restituisce l'array contenente tutte le posizioni degli elementi individuati
    private int [] find(String var)
    {
        int [] arr= new int [contiene(lista, var)];
        int j=0;
        for (int i=0;i<lista.length;i++)
        {
            if (lista[i].contains(var))
            {
                arr[j] = i;
                j++;
            } 
        }
        return arr;
    }
    
    //funzione che modifica il file, modificando la disponibilità da "vecchio" a "nuovo"
    private void gestione_noleggi(String nuovo, String vecchio, String data)  throws IOException, ParseException
    {
        List temp = new ArrayList();
        String targa_n = targa_noleggio.getText();
        String nome = nomecognome.getText();
        String path1 = "src/Autonoleggio/macchine.csv";
        int [] arr2 = find(targa_n);
        areatesto.clear();
        
        if(arr2.length != 0)
        {
            temp = lista[arr2[0]];
            //int save = Integer.parseInt((String)temp.get(3));
            int dif = 0;
            if (lista[arr2[0]].contains(vecchio))
            {
                try
                {   //aggiunge il nome del noleggiatore, se deve essere  liberata inserisce null
                    if (nuovo == "noleggiata")
                    {
                        temp.set(5, nome);
                        temp.set(6, data);
                    }
                    else if (nuovo == "libera")
                    {
                        temp.set(5, "null");
                        
                        Data obj3 = new Data((String)temp.get(6));
                        long diff = obj3.diff_data;
                        dif = (int) diff;
                        
                        temp.set(6, "null");
                    }
                    
                    //temp.set(3, String.valueOf(save));
                    temp.set(4, nuovo);
                    FileWriter writer2 = new FileWriter(path1);
                    if (arr2[0]==0)  //condizione per distinguere se l'elemento da modificare è il primo oppure no, altrimenti stampa tutta la lista, finchè non trova l'elemento modificato
                    {
                        if (nuovo == "libera")  //per eliminare il noleggiatore, visto che la macchina viene restituita
                        {
                            writer2.append(temp.toString().substring(1,temp.toString().length() -2));
                        }
                        else 
                        {
                            writer2.append(temp.toString().substring(1,temp.toString().length() -1));
                        }
                    }
                    else
                    {
                        writer2.append(lista[0].toString().substring(1,lista[0].toString().length() -1));
                    }

                    for(int i=1;i<lista.length;i++)
                    {
                        writer2.append('\n');
                        if (arr2[0]==i)
                        {
                            if (nuovo == "libera")  //per eliminare il noleggiatore, visto che la macchina viene restituita
                            {
                                writer2.append(temp.toString().substring(1,temp.toString().length() -1));
                            }
                            else 
                            {
                                writer2.append(temp.toString().substring(1,temp.toString().length() -1));
                            }
                        }
                        else
                        {
                            //appende al file una sotto stringa della stringa generata dall'analisi riga per riga di una lista (se appendevo la lista e basta ad inizio e fine c'erano delle quadre) 
                            writer2.append(lista[i].toString().substring(1,lista[i].toString().length() -1));  

                        }
                    }
                    //creazione del file
                    areatesto.setText("Azione andata a buon fine");
                    if (nuovo == "libera")
                    {
                        areatesto.appendText("\nImporto da pagare: "+((Integer.parseInt((String)temp.get(3))*(dif+1))));
                    }
                    writer2.close();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
            else 
            {
                
                if (nuovo == "noleggiata")
                {
                	areatesto.setText("La macchina è già noleggiata");
                }
                else
                {
                	areatesto.setText("La macchina è già libera");
                }
            }
        }
        else
        {
            areatesto.setText("La targa o è sbagliata o non è presente nel garage");
        }
    }
    
    
    private void add_element_table (int [] arr)
    {
    	//se l'array di lista non ha allocato nulla, viene creata una riga vuota sulla tabella
        if (arr.length == 0)
        {
            tabella.getItems().add(new CarModel());
        }
        else
        {
            //richiamo funzione che alloca nella prima tabella le macchine disponibili
            stampa_CarModel(arr);
        }
    }
}
