// Aqui estamos importando duas bibilhotecas
const readline = require('readline-sync');// essa le comandos do terminal
const net = require('net');// esta e o socket

// Criando as variaveis host e port, primeiro irá printar na tela "Digite o host: " e "Digite a porta: ".
const HOST = readline.question("Digite o host: ");
const PORTA = readline.questionInt("Digite a porta: ");

// criando client
const client = new net.Socket()

//depois de criado ele conecta na porta e host informados anteriormente
client.connect(PORTA, HOST, ()=>{

    // Quando identificar uma conexao ele ira printar
    console.log("Conectado!");
    while(1)
    {
        message = readline.question(); // lendo do terminal

        //enquanto a mensagem for diferente de tchau ele vai enviar, se não, a conexao sera encerrada
        if(message === 'sair')
        {
            client.write("Desconectando...");
            client.end();
            break;
        }

        //enviando mensagem
        client.write(message);
    }
})
