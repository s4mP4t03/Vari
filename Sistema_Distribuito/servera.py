import socket
import sys

def server_program(prime, hosts):
    n_each_client = int(prime) / int(hosts)

    # get the hostname
    host = socket.gethostname()
    port = 5000  # initiate port no above 1024

    server_socket = socket.socket()  # get instance
    # look closely. The bind() function takes tuple as argument
    server_socket.bind((host, port))  # bind host address and port together

    # configure how many client the server can listen simultaneously
    while(num_host != 0):
        #avendo assegnato a tutti gli host lo stesso numero, nel caso in cui alcuni numeri siano
        #rimasti fuori vengono assegnati all'ultimo host
        if(num_host == 1 && (n_each_client*hosts) != prime):
            n_each_client = n_each_client + (prime - (n_each_client*hosts)) 

        server_socket.listen()
        conn, address = server_socket.accept()  # accept new connection
        print("Connection from: " + str(address))
        
        f = open("primi_code.py", "r")
        a = f.read()
        conn.send(a.encode())
        conn.send(n_each_client.encode())
        num_host -= 1 #ogni client che si connette riduce il numero di connesioni massime

    f.close()
    conn.close()  # close the connection


if __name__ == '__main__':
    prime = sys.argv[1] #dato il numero cercherà i numeri primi tra 1 e il numero dato
    hosts = sys.argv[2] #indica il numero di client che potranno connettersi e trovare i numeri primi
    server_program(prime, hosts)
