package Project_Socket;

import java.net.*;
import java.io.*;

public class Server
{
    private int port;
    private ServerSocket server;

    public Server (int port)
    {
        this.port = port;
        if(!startServer())
        System.err.println("Errore durante la creazione del Server");
    }

    //VERIFICA CREAZIONE SERVER
    private boolean startServer()
    {
        try
        {
            server = new ServerSocket(port);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            return false;
        }
        System.out.println("Server creato con successo!");
        return true;
    }

    //AVVIA IL SERVER E RIMANE IN ASCOLTO PER SEMPRE
    public void runServer()
    {
        while (true)
        {
		try
            	{
			int input2 = 0;
			
	                //Operazioni Operazioni = new Operazioni();
	                
			 // Il server resta in attesa di una richiesta
	                System.out.println("Server in attesa di richieste...");
	                Socket s1 = server.accept();
	                System.out.println("Un client si e' connesso...");

	    		 //Creazione oggetto per la ricezione dell'output dal server	
	                PrintWriter out = new PrintWriter(s1.getOutputStream(),true);
                        //Creazione oggetto per mandare al server un output
	                BufferedReader in = new BufferedReader(new InputStreamReader(s1.getInputStream()));

			while(input2 == 0)
			{
				String a = in.readLine();  //aspetta che arrivi la stringa contenente numeri e operazione
				int basket [] = Manage_str.find_num(a);  //restituisce un array con la posizione dell'operatore all'interno della stringa e i due numeri
				int r=0;
				char [] arr = a.toCharArray();  //converte la stringa in un array di char
				switch (arr[basket[0]]) //richiamo metodi per operazioni 
				{
					case '+': //richiamo metodo somma 
					    r = Operazioni.add(basket[1], basket[2]);
					    break;
					case '-': //richiamo metodo sottrazione
					    r = Operazioni.sott(basket[1], basket[2]);
					    break;
					case '*': //richiamo metodo moltiplicazione
					    r = Operazioni.molt(basket[1], basket[2]);
					    break;
					case '/': //richiamo metodo divisione
					    r = Operazioni.div(basket[1], basket[2]);
					    break;
				}
				String z = Integer.toString(r);  //converte il risultato in una stringa
				out.println(z);  //ritorna il risultato al client
				input2 = 2;
			}
	                // Chiude lo strema di output e la connessione
	                s1.close();
	                System.out.println("Chiusura connessione effettuata");
		}
		 catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
}

    public static void main (String args[]) 
    {
        // Crea un oggetto di tipo Server in ascolto
        // sulla porta 14751
        Server ss = new Server(14751);
        ss.runServer();
    }
}
