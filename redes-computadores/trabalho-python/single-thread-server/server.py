import socket
import threading
import functions.myserver as myserver

HOST = input("Digite o ip: ")
PORT = int(input("Digite a porta: "))
ADDR = (HOST, PORT) # tupla para passar de parametro no metodo bind

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM) # AF_INET --> conexao por ipv4, SOCK_STREAM --> envio de sockets
server.bind(ADDR)

print("[INICIANDO] servidor esta iniciando...")

myserver.start(server, HOST, PORT)
