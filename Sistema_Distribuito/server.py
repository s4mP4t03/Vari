import socket
import sys
from _thread import *

n_each_client = 0
num_host = 0
prime = 0
hosts = 0

def server_program():
    n_each_client = int(prime) / int(hosts)
    num_host = hosts

    # get the hostname
    host = socket.gethostname()
    port = 5000  # initiate port no above 1024
    ThreadCount = 0

    ServerSideSocket = socket.socket()  # get instance
    # look closely. The bind() function takes tuple as argument
    try:
        ServerSideSocket.bind((host, port))
    except socket.error as e:
        print(str(e))

    # configure how many client the server can listen simultaneously
    ServerSideSocket.listen(int(hosts))

    while True:
    
        Client, address = ServerSideSocket.accept() # accept new connection
        print('Connected to: ' + address[0] + ':' + str(address[1]))
        start_new_thread(multi_threaded_client, (Client, ))  #inizia nuovo thread richiamando metodo con codice personalizzato
        ThreadCount += 1
        print('Thread Number: ' + str(ThreadCount))
        
    ServerSideSocket.close()



def multi_threaded_client(conn):
    #conn.send(str.encode('Server is working:'))
    while(num_host != 0):
        #avendo assegnato a tutti gli host lo stesso numero, nel caso in cui alcuni numeri siano
        #rimasti fuori vengono assegnati all'ultimo host
        if(num_host == 1 & (n_each_client*hosts) != prime):
            n_each_client = n_each_client + (prime - (n_each_client*hosts)) 
        
        f = open("primi_code.py", "r")
        a = f.read()
        conn.send(a.encode())
        conn.send(n_each_client.encode())
        num_host -= 1 #ogni client che si connette riduce il numero di connesioni massime

    f.close()
        
    conn.close()


if __name__ == '__main__':
    prime = sys.argv[1] #dato il numero cercherà i numeri primi tra 1 e il numero dato
    hosts = sys.argv[2] #indica il numero di client che potranno connettersi e trovare i numeri primi
    server_program()
