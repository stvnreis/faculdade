import socket
import threading

def handle_client(conn, addr):
    print(f"[NOVA CONEXAO] {addr} conectado\n")

    connected = True
    while connected:
        msg = conn.recv(2048).decode("utf-8") # recebendo mensagens de 2048 bytes com codificacao utf-8

        if msg == 'sair':
            connected = False
            print(f"Encerrando conexao com client {addr}")
            continue
        if msg:
            print(f"[{addr}]: {msg}")
    
    print(f"[CONEXOES ATIVAS] {threading.activeCount()-1}\n")
    conn.close()

def start(server, HOST, PORT):
    server.listen()
    print(f"Listening on {HOST}:{PORT}")
    
    while True:
        conn, addr = server.accept()
        thread = threading.Thread(target=handle_client, args=(conn, addr)) # criando threads para cada nova conexao
        thread.start()
        print(f"[CONEXOES ATIVAS] {threading.activeCount()-1}\n")