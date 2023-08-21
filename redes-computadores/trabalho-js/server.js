
// Aqui estamos importando duas bibilhotecas
const readline = require('readline-sync'); // essa le comandos do terminal
const net = require('net'); // esta e o socket
const { exit } = require('process');

// Criando as variaveis host e port, primeiro irá printar na tela "Digite o host: " e "Digite a porta: ".
const HOST = readline.question("Digite o host: ");
const PORTA = readline.questionInt("Digite a porta: ");

// Criando o servidor chamando o contrutor create server
const server = net.createServer(socket=>{
   
    // Quando identificar uma conexao ele ira printar
    console.log("Client conectado!");
    
    // Se for reconhecido uma mensagem do tipo END a conexao sera encerrada
    // Se a data 'dado' recebido for, 'sair' a conexão será encerrada.
    socket.on('end', ()=>{
        console.log("Client desconectou!");
        socket.end();
        exit();
    })
    socket.on('data', data=>{
        
        // Se nao, vai trasformar em string e mostrar a mensagem
        console.log(data.toString());
    })
})

// Aqui esta ouvindo por meio de um método do socket
server.listen(PORTA, HOST);