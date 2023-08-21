import socket
import os

host = input("Digite o ip do servidor: ")
port = int(input("Digite a porta do servidor: "))

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((host, port))

os.system('cls')

print(f"Conectado em {host}:{port}")

while True:
    message = input()
    
    if(message == "sair"):
        s.send(bytes(message, "utf-8"))
        break
    
    s.send(bytes(message, "utf-8"))
s.close()