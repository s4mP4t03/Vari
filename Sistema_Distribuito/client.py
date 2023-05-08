from math import *
import socket

def client_program():
    host = socket.gethostname()  # as both code is running on same pc
    port = 5000  # socket server port number
    _locals = locals()

    client_socket = socket.socket()  # instantiate
    client_socket.connect((host, port))  # connect to the server

    code = client_socket.recv(1024).decode() #decode
    var = str(code)
    num2 = client_socket.recv(1024).decode()
    var = var.replace('num2', num2)
    exec(var)
    client_socket.close()  # close the connection


if __name__ == '__main__':
    client_program()
