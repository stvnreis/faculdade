#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>
#include <time.h>
#include <unistd.h>

#define THREAD_NUM 8

pthread_mutex_t mutexBuffer;
sem_t semEmpty;
sem_t semFull;

int buffer[10];
int count = 0;

void* produtor(void* arg){
    while(1){
        int x = rand() % 100;

        sem_wait(&semEmpty);
        pthread_mutex_lock(&mutexBuffer);
        buffer[count] = x;
        count++;
        printf("Adding to the Buffer: %d\n", x);
        pthread_mutex_unlock(&mutexBuffer);
        sem_post(&semFull);
    }
}

void* consumidor(void* arg){
    while(1){
        sem_wait(&semFull);
        pthread_mutex_lock(&mutexBuffer);
        count--;
        int y = buffer[count];
        pthread_mutex_unlock(&mutexBuffer);
        sem_post(&semEmpty);
        printf("Removing from the Buffer: %d\n", y);
        sleep(1);
    }
}

int main(){
    srand(time(NULL));
    pthread_t th[THREAD_NUM];
    pthread_mutex_init(&mutexBuffer, NULL);
    sem_init(&semEmpty, 0, 10);
    sem_init(&semFull, 0, 0);

    int i;
    for(i = 0; i<THREAD_NUM; i++){
        if(i%2 == 0){
            pthread_create(&th[i], NULL, &produtor, NULL);
        }
        else{
            pthread_create(&th[i], NULL, &consumidor, NULL);
        }
    }

    for(i=0; i<THREAD_NUM; i++){
        pthread_join(th[i], NULL);
    }

    pthread_mutex_destroy(&mutexBuffer);
    sem_destroy(&semFull);
    sem_destroy(&semEmpty);
    return 0;
}