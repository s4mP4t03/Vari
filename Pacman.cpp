#include <iostream>
#include <windows.h>
#include <conio.h>
#include <ctime>

//COSTANTI PER LE DIMENSIONI DEL BORDO
#define H 20
#define W 30

char v[H][W];
int temp, temp1; //VARIABILI GLOBALI USATE PER SALVARE DEI DATI DI ALCUNE VARIBILI (FUNZIONE BLOCCHI)
int input; //SALVARE L'INPUT
int score = 0; //TIENE CONTO DEL PUNTEGGIO

using namespace std;

/**
  * Gets: number of seconds to wait for user input
  * Returns: '_' if there was no input, otherwise returns the char inputed
**/
char waitForCharInput(int seconds) //FUNZIONE PER L'INSERIMENTO DA TASTIERA, CONSIDERANDO IL CASO IN CUI NON VENGA INSERITO NULLA
{
    char c = '_'; //default return
    while (seconds != 0) {
        if (_kbhit()) { //if there is a key in keyboard buffer
            c = _getch(); //get the char
            input = c;  //SALVATAGGIO VALORE DI c
            break; //we got char! No need to wait anymore...
        }

        Sleep(300); //one second sleep when is set to 1000 (300)
        --seconds; //countdown a second
    }
    return c;
}

//STRUCT PER LE CARATTERISTICHE DELLE ENTITA'
struct Entity
{
    int x;
    int y;
    char glyph;
    char before;  //PER SALVARE IL CARATTERE CHE C'ERA NELLA CELLA DELLA MAPPA PRIMA CHE L'ENTITA' VI SI METTESSE SOPRA
};



//STRUCT PER LE CARATTERISTICHE DEI BLOCCHI
struct blocks
{
    int x; //prima cella occupata dal blocco sull'asse delle x
    int y; //prima cella occupata dal blocco sull'asse delle y
    int dx; //durata del blocco sull'asse delle x
    int dy; //durata del blocco sull'asse delle y
    int key; //chiave per entrare in un if per poter salvare i dati di alcune variabili (funzione blocchi)
};



//FUNZIONE PER GLI SPOSTAMETNI DELLE ENTITA'
void move_entity(Entity& entity, int dx, int dy)
{
    entity.x += dx;
    entity.y += dy;
}


//FUNZIONE CREAZIONE BLOCCHI
void blocchi(blocks& block, int x, int y);


// FUNZIONE PER L'AGGIORNAMENTO DELLO SCHERMO
void clear()
{
    COORD topleft = { 0, 0 }; //topleft variabile ti tipo COORD che punta alla posizione in alto a sinistra = 0, 0
    HANDLE console = GetStdHandle(STD_OUTPUT_HANDLE); //console variabile di tipo HANDLE
    SetConsoleCursorPosition(console, topleft); //funzione che richiama i valori delle due variabili 
}


//FUNZIONE PER PERIMETRO CREAZIONE FANTASMI
void board_ghost(int x, int y);


//FUNZIONE PER GLI SPOSTAMENTI DEI FANTASMI
void ghost_mov(Entity& ghost);


//FUNZIONE CHE CREA L'AMBIENTE DI GIOCO
void board(Entity& pm, Entity& ghost1, Entity& ghost2, Entity& ghost3, Entity& ghost4 )
{
    int x, y;

    //DICHIARAZIONI DELLE STRUTTURE DEI BLOCCHI
    blocks seven_three{ 2, 2, 6, 1, 1 };
    blocks eight_three1{ 1, 5, 7, 2, 1 };
    blocks eight_three2{ 21, 5, 7, 2, 1 };
    blocks eight_three3{ 1, 9, 7, 2, 1 };
    blocks eight_three4{ 21, 9, 7, 2, 1 };
    blocks four_two{ 13, 2, 3, 1, 1 };
    blocks two_four{ 10, 2, 1, 3, 1 };
    blocks two_four1{ 18, 2, 1, 3, 1 };
    blocks two_one2{ 12, 5, 1, 0, 1 };
    blocks two_one3{ 16, 5, 1, 0, 1 };
    blocks two_three2{ 18, 11, 1, 1, 1 };
    blocks two_one{ 16, 11, 1, 0, 1 };
    blocks two_three3{ 10, 11, 1, 1, 1 };
    blocks two_one0{ 12, 11, 1, 0, 1 };
    blocks six_one1{ 2, 13, 5, 0, 1 };
    blocks three_two{ 5, 14, 2, 1, 1 };
    blocks one_three{ 1, 15, 2, 0, 1 };
    blocks two_two{ 9, 15, 1, 1, 1 };
    blocks twelve_one{ 2, 17, 11, 0, 1 };
    blocks eight_two{ 12, 14, 7, 1, 1 };
    blocks one_five{ 21, 13, 0, 4, 1 };
    blocks two_one4{ 15, 16, 1, 1, 1 };
    blocks two_one5{ 19, 17, 1, 0, 1 };
    blocks two_five{ 23, 13, 1, 4, 1 };
    blocks one_three1{ 26, 15, 2, 0, 1 };
    blocks one_three2{ 25, 17, 2, 0, 1 };
    blocks one_two{ 9, 13, 1, 0, 1 };
    blocks one_four1{ 13, 13, 3, 0, 1 };
    blocks one_three4{ 25, 13, 2, 0, 1 };
    blocks seven_three1{ 21, 2, 6, 1, 1 };



    // FOR INIZIALIZZAZIONE MATRICE
    for (y = 0; y < H; y++)
    {
        for (x = 0; x < W; x++)
        {
            //CELLA IN CUI E' PRESENTE PACMAN
            if (x == pm.x && y == pm.y)  
            {
                v[y][x] = pm.glyph;
            }
            //CELLA IN CUI E' PRESENTE GHOST1
            else if (x == ghost1.x && y == ghost1.y)
            {
                v[y][x] = ghost1.glyph;
            }
            //CELLA IN CUI E' PRESENTE GHOST2
            else if (x == ghost2.x && y == ghost2.y)  
            {
                v[y][x] = ghost2.glyph;
            }
            //CELLA IN CUI E' PRESENTE GHOST3
            else if (x == ghost3.x && y == ghost3.y) 
            {
                v[y][x] = ghost3.glyph;
            }
            //CELLA IN CUI E' PRESENTE GHOST4
            else if (x == ghost4.x && y == ghost4.y)  
            {
                v[y][x] = ghost4.glyph;
            }
            else if (x == 0 || x == W - 1 || y == 0 || y == H - 1)  //CELLE PIU' ESTERNE
            {
                if (x == 0 && y == 8)  //LUOGO PER IL TELETRASPORTO
                {
                    v[y][x] = '{';
                }
                else if (x == W - 1 && y == 8)  //LUOGO PER IL TELETRASPORTO
                {
                    v[y][x] = '}';
                }
                else
                {
                    v[y][x] = '#';
                }
            }
            else
            {
                /* BLOCCO N.7.3*/   if (x == seven_three.x && y == seven_three.y) { blocchi(seven_three, x, y); }
                /* BLOCCO N.2.3.1*/ else if (x == four_two.x && y == four_two.y) { blocchi(four_two, x, y); }
                /* BLOCCO N.2.3.4*/ else if (x == two_four.x && y == two_four.y) { blocchi(two_four, x, y); }
                /* BLOCCO N.2.3.4*/ else if (x == two_four1.x && y == two_four1.y) { blocchi(two_four1, x, y); }
                /* BLOCCO N.8.3.1*/ else if (x == eight_three1.x && y == eight_three1.y) { blocchi(eight_three1, x, y); }
                /* BLOCCO N.2.1.2*/ else if (x == two_one2.x && y == two_one2.y) { blocchi(two_one2, x, y); }
                /* BLOCCO N.2.1.3*/ else if (x == two_one3.x && y == two_one3.y) { blocchi(two_one3, x, y); }
                /* BLOCCO N.8.3.2*/ else if (x == eight_three2.x && y == eight_three2.y) { blocchi(eight_three2, x, y); }
                /* BLOCCO N.8.3.3*/ else if (x == eight_three3.x && y == eight_three3.y) { blocchi(eight_three3, x, y); }
                /* BLOCCO N.8.3.4*/ else if (x == eight_three4.x && y == eight_three4.y) { blocchi(eight_three4, x, y); }
                /* BLOCCO N.2.3.2*/ else if (x == two_three2.x && y == two_three2.y) { blocchi(two_three2, x, y); }
                /* BLOCCO N.2.3.3*/ else if (x == two_three3.x && y == two_three3.y) { blocchi(two_three3, x, y); }
                /* BLOCCO N.2.1.0*/ else if (x == two_one0.x && y == two_one0.y) { blocchi(two_one0, x, y); }
                /* BLOCCO N.2.1*/   else if (x == two_one.x && y == two_one.y) { blocchi(two_one, x, y); }
                /* BLOCCO N.6.1.1*/ else if (x == six_one1.x && y == six_one1.y) { blocchi(six_one1, x, y); }
                /* BLOCCO N.1.2*/   else if (x == one_two.x && y == one_two.y) { blocchi(one_two, x, y); }
                /* BLOCCO N.1.4.1*/ else if (x == one_four1.x && y == one_four1.y) { blocchi(one_four1, x, y); }
                /* BLOCCO N.3.2*/   else if (x == three_two.x && y == three_two.y) { blocchi(three_two, x, y); }
                /* BLOCCO N.8.2*/   else if (x == eight_two.x && y == eight_two.y) { blocchi(eight_two, x, y); }
                /* BLOCCO N.1.3*/   else if (x == one_three.x && y == one_three.y) { blocchi(one_three, x, y); }
                /* BLOCCO N.2.2*/   else if (x == two_two.x && y == two_two.y) { blocchi(two_two, x, y); }
                /* BLOCCO N.12.1*/  else if (x == twelve_one.x && y == twelve_one.y) { blocchi(twelve_one, x, y); }
                /* BLOCCO N.1.5*/   else if (x == one_five.x && y == one_five.y) { blocchi(one_five, x, y); }
                /* BLOCCO N.2.5*/   else if (x == two_five.x && y == two_five.y) { blocchi(two_five, x, y); }
                /* BLOCCO N.1.3.1*/ else if (x == one_three1.x && y == one_three1.y) { blocchi(one_three1, x, y); }
                /* BLOCCO N.1.3.2*/ else if (x == one_three2.x && y == one_three2.y) { blocchi(one_three2, x, y); }
                /* BLOCCO N.2.2.2*/ else if (x == two_one4.x && y == two_one4.y) { blocchi(two_one4, x, y); }
                /* BLOCCO N.2.2.3*/ else if (x == two_one5.x && y == two_one5.y) { blocchi(two_one5, x, y); }
                /* BLOCCO N.1.3.4*/ else if (x == one_three4.x && y == one_three4.y) { blocchi(one_three4, x, y); }
                /* BLOCCO N.1.3.4*/ else if (x == seven_three1.x && y == seven_three1.y) { blocchi(seven_three1, x, y); }


                /* ZONA IN CUI SPAWNANO I FANTASMI*/else if (x == 10 && y == 7) { board_ghost(x, y); }

                else if (v[y][x] != ' ' && v[y][x] != '-' && v[y][x] != '|') //CELLE CHE NON HANNO BLOCCHI
                {
                    if (v[y][x] == pm.glyph) //IF PER TOGLIERE I PUNTINI NELLE CELLE IN CUI VA PACMAN
                    {
                        v[y][x] = ' ';
                    }
                    else
                    {
                        v[y][x] = '.';
                    }
                }
            }
        }
    }
}

int main()
{
    int x, y;  //VARIABILI USATE COME INDICI DEL VETTORE
    
    Entity pm{ 15 , 12 , '@', ' ' };  //CARATTERISTICHE DI PACMAN
    Entity ghost1{ 12 , 8, '<', '.' };
    Entity ghost2{ 14 , 8, '<', '.' };
    Entity ghost3{ 16 , 8, '<', '.' };
    Entity ghost4{ 18 , 8, '<', '.' };

    bool cond = true;  //VARIABILE PER CONTROLLARE IL WHILE PRINCIPALE
    char pc_mov;  //VARIABILE CHE INDICATA L'INPUT DA TASTIERA

    while (cond && score < 225)  //WHILE PRINCIPALE; FINISCE SE COND E' FALSO O SE SCORE E' >=225 [225 = NUMERO MASSIMO DI PUNTINI]
    {
        board(pm, ghost1, ghost2, ghost3, ghost4);  //CHIAMATA ALLA FUNZIONE PER INIZIALIZZARE LA MATRICE

        // FOR STAMPA ARRAY
        cout << "Score: " << score;
        cout << "\n\n";
        for (y = 0; y < H; y++)
        {
            printf("\n");
            printf("\t\t");
            for (x = 0; x < W; x++)
            {
                cout << v[y][x];
            }
        }
        cout << "\n\n";

        //MOVIMENTI DEI FANTASMI
        ghost_mov(ghost1);
        ghost_mov(ghost2);
        ghost_mov(ghost3);
        ghost_mov(ghost4);

        //INSERIMENTO INPUT
        pc_mov = waitForCharInput(1);  //CHIAMATA ALLA FUNZIONE PER L'INSERIMENTO DELL'INPUT [RETURN = INPUT]
        clear();  //CHIAMATA ALLA FUNZIONE PER PULIRE LO SCHERMO
        if (pc_mov == '_')  //IF CONSEGUENZA DEL DEFAULT RETURN DI waitForCharInput
        {
            pc_mov = input;  //ASSEGNARE pc_mov UGUALE AL CONTENUTO SALVATO IN UNA VARIABILE TEMPORANEA
        }
        switch (pc_mov)
        {
        case 'q': cond = false; break;      //CONDIZIONI PER FARE IN MODO CHE PACMAN NON VADA SU I BLOCCHI

        case 'a': if (v[pm.y][pm.x - 1] == '.') { score++; }  //CONDIZIONE PER CONTROLLARE SE FACENDO 'A' C'E' UN PUNTO E COSI' SCORE+
                if (v[pm.y][pm.x - 1] != '#' && v[pm.y][pm.x - 1] != '-' && v[pm.y][pm.x - 1] != '|') { move_entity(pm, -1, 0); }
                if (v[pm.y][pm.x] == '{') { pm.x = W - 2; } break; //IF PER PERMETTERE IL TELETRASPORTO

        case 'w': if (v[pm.y - 1][pm.x] == '.') { score++; }  //CONDIZIONE PER CONTROLLARE SE FACENDO 'W' C'E' UN PUNTO E COSI' SCORE+
                if (v[pm.y - 1][pm.x] != '#' && v[pm.y - 1][pm.x] != '-' && v[pm.y - 1][pm.x] != '|') { move_entity(pm, 0, -1); } break;

        case 'd': if (v[pm.y][pm.x + 1] == '.') { score++; }  //CONDIZIONE PER CONTROLLARE SE FACENDO 'D' C'E' UN PUNTO E COSI' SCORE+
                if (v[pm.y][pm.x + 1] != '#' && v[pm.y][pm.x + 1] != '-' && v[pm.y][pm.x + 1] != '|') { move_entity(pm, +1, 0); }
                if (v[pm.y][pm.x] == '}') { pm.x = 1; } break; //IF PER PERMETTERE IL TELETRASPORTO

        case 's': if (v[pm.y + 1][pm.x] == '.') { score++; }  //CONDIZIONE PER CONTROLLARE SE FACENDO 'S' C'E' UN PUNTO E COSI' SCORE+
                if (v[pm.y + 1][pm.x] != '#' && v[pm.y + 1][pm.x] != '-' && v[pm.y + 1][pm.x] != '|') { move_entity(pm, 0, +1); } break;
        }
    }
    if (score >= 225) //CONDIZIONI PER IL PUNTEGGIO
    {
        cout << "HAI VINTO!!";
        return 0;
    }
    else
    {
        cout << "HAI PERSO!";
        return 0;
    }
}



//FUNZIONE CREAZIONE BLOCCHI
void blocchi(blocks& block, int x, int y)
{

    //CONTROLLO PER PRIMA ISTRUZIONE, PER SALVARE I DATI
    if (block.key == 1)
    {
        temp = block.x;
        temp1 = block.dx;
        block.key = 0;
    }

    //IF PER AUMENTARE LA X
    if (block.dx > 0)
    {
        block.x++;
        block.dx--;
    }

    //IF PER PASSARE ALLA RIGA SUCCESSIVA E VENGONO RESETTATI LA X E DX, KEY VIENE RESETTATO A 1 COSI DA POTER SALVARE I VALORI ATTRAVERSO IL PRIMO IF
    else if (block.dy > 0)
    {
        block.y++;
        block.dy--;
        block.x = temp;
        block.dx = temp1;
        block.key = 1;

    }
    v[y][x] = '#';
}


//FUNZIONE PER PERIMETRO CREAZIONE FANTASMI
void board_ghost(int x, int y)
{
    int i, t_x;
    t_x = x;
    for (i = 0; i < 4; i++)
    {
        v[y][x] = '-';
        x++;
    }
    for (i = 0; i < 2; i++)
    {
        v[y][x] = ' ';
        x++;
    }
    for (i = 0; i < 4; i++)
    {
        v[y][x] = '-';
        x++;
    }
    y++;
    x = t_x;
    v[y][x] = '|';
    x++;
    for (i = 0; i < 8; i++)
    {
        v[y][x] = ' ';
        x++;
    }
    v[y][x] = '|';
    y++;
    x = t_x;
    for (i = 0; i < 10; i++)
    {
        v[y][x] = '-';
        x++;
    }
}



//FUNZIONE PER GLI SPOSTAMENTI DEI FANTASMI
void ghost_mov(Entity& ghost)
{
    int place, a=0;
    srand((unsigned)time(0));

    v[ghost.y][ghost.x] = ghost.before;  //VIENE RIMESSO NELLA CELLA IN CUI E' QUELLO CHE C'ERA PRIMA 
    ghost.before = '.';  //VIENE MESSO IL VALORE DI DEFAULT 
    while (a == 0)
    {
        place = rand() % 4;
        switch (place)
        {                                                                                                                                                  //SE DOVE SI SPOSTA E' VUOTO ALLORA VERRA' SALVATO CEH E' VUOTO PER RIMETTERLO POI ALL'INIZIO DELLA FUNZIONE
        case 1: if (v[ghost.y][ghost.x - 1] != '#' && v[ghost.y][ghost.x - 1] != '-' && v[ghost.y][ghost.x - 1] != '|' && v[ghost.y][ghost.x - 1] != ghost.glyph) { if (v[ghost.y][ghost.x - 1] == ' ') { ghost.before = ' ';} move_entity(ghost, -1, 0); a++; }
              if (v[ghost.y][ghost.x] == '{') { ghost.x = W - 2; } break; //IF PER PERMETTERE IL TELETRASPORTO

        case 2: if (v[ghost.y - 1][ghost.x] != '#' && v[ghost.y - 1][ghost.x] != '-' && v[ghost.y - 1][ghost.x] != '|' && v[ghost.y - 1][ghost.x] != ghost.glyph) { if (v[ghost.y - 1][ghost.x] == ' ') { ghost.before = ' ';} move_entity(ghost, 0, -1); a++; } break;
   
        case 3: if (v[ghost.y][ghost.x + 1] != '#' && v[ghost.y][ghost.x + 1] != '-' && v[ghost.y][ghost.x + 1] != '|' && v[ghost.y][ghost.x + 1] != ghost.glyph) { if (v[ghost.y][ghost.x + 1] == ' ') { ghost.before = ' ';} move_entity(ghost, +1, 0); a++; }
              if (v[ghost.y][ghost.x] == '}') { ghost.x = 1; } break; //IF PER PERMETTERE IL TELETRASPORTO

        case 4: if (v[ghost.y + 1][ghost.x] != '#' && v[ghost.y + 1][ghost.x] != '-' && v[ghost.y + 1][ghost.x] != '|' && v[ghost.y + 1][ghost.x] != ghost.glyph) { if (v[ghost.y + 1][ghost.x] == ' ') { ghost.before = ' ';} move_entity(ghost, 0, +1); a++; } break;
        }
    }
    cout << place;
    cout << ghost.glyph;
    cout << ghost.x << "y" << ghost.y << "\n";
    
}
