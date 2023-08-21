import model.Myserver as ms

server = ms.Myserver(input("Digite o ip do host: "), int(input("Digite a porta do host: ")))
server.run()