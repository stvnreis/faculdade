import threading
import socket

class Myserver:
    def __init__(self, host, door):
        self.__door = door
        self.__host = host
        self.__server = socket.socket(socket.AF_INET, socket.SOCK_STREAM) # AF_INET --> conexao por ipv4, SOCK_STREAM --> envio de sockets
    
    @property
    def door(self):
        return self.__door
    
    @property
    def host(self):
        return self.__host
    
    def run(self):
        self.__server.bind((self.__host, self.__door))
        self.__server.listen()

        print(f"Listening on {self.__host}:{self.__door}")
        
        while True:
            conn, addr = self.__server.accept()
            thread = threading.Thread(target=self.handle_client, args=(conn, addr)) # criando threads para cada nova conexao
            thread.start()
            print(f"[CONEXOES ATIVAS] {threading.activeCount()-1}\n")

    def handle_client(self, conn, addr):
        print(f"[NOVA CONEXAO] {addr} conectado\n")
        while True:
            msg = conn.recv(2048).decode("utf-8") # recebendo mensagens de 2048 bytes com codificacao utf-8
            
            if msg == "sair":
                print(f"Encerrando conexao com client {addr}")
                break
            if msg:
                print(f"[{addr}]: {msg}")
        
        conn.close()