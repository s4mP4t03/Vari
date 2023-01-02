package Project_Socket;

public class Manage_str {

    public static int num1 = 0;
    public static int num2 = 0;

    //CONVERTE ARRAY DI CHAR IN INTERO
    private static int to_int(String temp) {
        char[] str = temp.toCharArray(); //converte array di char in stringa
        int n = 0, j = temp.length() - 1, exp = 1;  //dichiarazione di n = valore ritornato //j = lunghezza della stringa //exp usato per indicare unità o decine e così via

        //for che legge la stringa dalla cella più grande a quella più piccola, controlla che in ogni cella ci sia una dei valori presenti nell'if (numeri da 0 a 9)
        for (; j >= 0; j--) {
            if (str[j] == '0' || str[j] == '1' || str[j] == '2' || str[j] == '3' || str[j] == '4' || str[j] == '5' || str[j] == '6' || str[j] == '7' || str[j] == '8' || str[j] == '9') {
                n = n + ((str[j] - 48) * exp);  //un char ha un numero intero (corrispettivo della tabella ASCII) sottraggo quindi 48 e trovo il numero intero corrispondente a quello scritto in char, moltiplicho per exp che vale quanto la posizione in cui quel numero è nella cifra finale(unità, decine), sommo tutto questo con n, quindi con quello precedentemente trovato
                exp = exp * 10;  //avendo trovato le unità, ora passo alle decine
            }

        }
        return n;

    }


    //FUNZIONE PER TROVARE I NUMERI DATI IN UNA STRINGA, RESTITUISCE IL CHAR DELL'OPERATORE
    public static int[] find_num(String temp) {
        char[] arr = temp.toCharArray(); //converte array di char in stringa
        //dichiarazione di due array di char temporanei, prima stringa per parte prima dell'operatore, seconda stringa per la parte dopo
        char[] str = new char[11];
        char[] str2 = new char[11];
        int op = 0, i;  //op usato per scegliere l'operazione da effettuare, i puntatore delle celle array

        for (i = 0; i < 11; i++) {
            //inizialmente viene eseguito l'else (str[i] = arr[i]) in cui metterà il primo numero in una nuiova stringa, successivamente riconosce che c'è un operatore, e andrà nel else del primo if, salverà l'indice corrente dell'array(contiene la posizione dell'operatore nell'array iniziale), viene trovato il numero intero, str viene annullata (non serve più e servirà a dire al secondo if che dovrà andare lì, cioè salvare le cifre dopo l'operatore in una nuova stringa
            if (arr[i] != '+' && arr[i] != '-' && arr[i] != '*' && arr[i] != '/') {
                if (str == null) //per creare una nuova stringa, dopo che è stato trovato l'operatore
                {
                    str2[i] = arr[i]; //salva il secondo addendo in una seconda stringa
                } else {
                    str[i] = arr[i]; //salva il primo addendo in una prima stringa
                }
            } else //questo else verrà eseguito dopo che sarà stato trovato l'operatore
            {
                op = i;  //salvataggio dell'indice dell'operatore
                //richiamo funzione per convertire array di char in un numero
                num1 = to_int(String.valueOf(str));  //trovato il primo numero

                str = null; //una volta fatta questa operazione if (str==null) sarà sempre vero

            }
        }
        //trovato anche il secondo numero sarà in str2 e lo convertirà in intero
        num2 = to_int(String.valueOf(str2));  //trovato il secondo numero
        int basket[] = {op, num1, num2};  //creazione di un array di int, che conterranno la posizione dell'operatore, e i due numeri
        return basket;
    }

}
