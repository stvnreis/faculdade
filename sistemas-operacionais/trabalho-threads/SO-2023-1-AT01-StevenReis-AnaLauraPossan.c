#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>
#include <semaphore.h>
#include <stdbool.h>

// Steven de Luca Reis de Oliveira, RA: 2494790
// Ana Laura Possan, RA: 2484650


pthread_mutex_t lock;
sem_t semEmpty;
sem_t semFull;

#define THREAD_NUM 8
#define TAM 10


typedef struct{
    int arr[TAM];
    int head;
    int tail;
    int qtd;
}Fila;

// iniciando a fila do buffer
void iniciar(Fila *f){
    f->head = 0;
    f->tail = 0;
    f->qtd = 0;
}

// função para incrementar o indice, deixando a fila "circular"
int incrementaIndice(int indice) {
  return indice == TAM - 1? 0 : indice + 1;
}

// verificando se a fila esta vazia
bool estaVazia(Fila *f){
    return f->qtd == 0 ? true : false;
}

// verificando se a fila esta vazia
bool estaCheia(Fila *f){
    return f->qtd == TAM ? true : false;
}

// função que insere elementos no final do buffer
void inserir(Fila *f, int valor){
    if(estaCheia(f) == 1){
        return;
    }
    else{
        f->arr[f->tail] = valor;
        f->tail = incrementaIndice(f->tail);
        f->qtd++;
    }
}

// funcao que retira elemento do inicio do buffer
int retirar(Fila *f){
    if(estaVazia(f) == 1){
        // caso a fila esteja vazia, retorna -1 para podermos identificar, ja que os valores produzidos pello produtor soa inteiros de 0 a 100
        return -1;
    }
    else{
        int valor = f->arr[f->head];
        f->head = incrementaIndice(f->head);
        f->qtd++;
        return valor;
    }
}

// produtor que gera um numero "aleatorio" entre 0 e 100 e insere no buffer para o consumidor "consumir"
void* produtor(void* arg){
    while(true){
        int valorProduzido = rand() % 100;

        // verifica se tem espaco vazio no buffer para poder produzir e inserir na fila
        sem_wait(&semEmpty);
        pthread_mutex_lock(&lock);
        
        inserir(arg, valorProduzido);

        printf("Adicionando no Buffer: %d\n", valorProduzido);
        pthread_mutex_unlock(&lock);
        // envia que um slot foi ocupado
        sem_post(&semFull);
    }
}

// consumidor que consome o valor que se encontra no inicio do buffer e libera espaco para o produtor voltar a produzir
void* consumidor(void* arg){
    while(true){
        // verifica se existem slots ocupados
        sem_wait(&semFull);
        pthread_mutex_lock(&lock);
        
        int valorConsumido = retirar(arg);

        pthread_mutex_unlock(&lock);
        // avisa que um slot do buffer foi liberado
        sem_post(&semEmpty);
        
        printf("Removendo do Buffer: %d\n", valorConsumido);
        sleep(1);
    }
}

int main(){
    srand(time(NULL));
    // gera threads de acordo com a constante definida no inicio do codigo
    pthread_t th[THREAD_NUM];
    pthread_mutex_init(&lock, NULL);
    // iniciando semaforo para indicar quantos slots estao livres e quantos estao vazios dentro do buffer
    sem_init(&semEmpty, 0, 10);
    sem_init(&semFull, 0, 0);

    Fila f;
    iniciar(&f);

    // criando threads, sendo a primeira o produtor e o restante, consumidores
    int i;
    for(i = 0; i<THREAD_NUM; i++){
        if(i == 0){
            pthread_create(&th[i], NULL, &produtor, (void *) &f);
        }
        else{
            pthread_create(&th[i], NULL, &consumidor, (void *) &f);
        }
    }

    for(i=0; i<THREAD_NUM; i++){
        pthread_join(th[i], NULL);
    }

    pthread_mutex_destroy(&lock);
    sem_destroy(&semFull);
    sem_destroy(&semEmpty);
    return 0;
}