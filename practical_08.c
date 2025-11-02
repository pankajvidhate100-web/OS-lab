
#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>

#define NUM_PHILOSOPHERS 5
#define THINKING 0
#define HUNGRY 1
#define EATING 2

sem_t mutex;
sem_t S[NUM_PHILOSOPHERS];
int state[NUM_PHILOSOPHERS];
int phil[NUM_PHILOSOPHERS] = {0, 1, 2, 3, 4};

void test(int i) {
    if (state[i] == HUNGRY &&
        state[(i + 4) % NUM_PHILOSOPHERS] != EATING &&
        state[(i + 1) % NUM_PHILOSOPHERS] != EATING) {
        state[i] = EATING;
        sleep(1);
        printf("Philosopher %d takes fork %d and %d\n", i + 1, (i + 4) % NUM_PHILOSOPHERS + 1, i + 1);
        printf("Philosopher %d is Eating\n", i + 1);
        sem_post(&S[i]);
    }
}

void take_fork(int i) {
    sem_wait(&mutex);
    state[i] = HUNGRY;
    printf("Philosopher %d is Hungry\n", i + 1);
    test(i);
    sem_post(&mutex);
    sem_wait(&S[i]);
    sleep(1);
}

void put_fork(int i) {
    sem_wait(&mutex);
    state[i] = THINKING;
    printf("Philosopher %d puts down fork %d and %d\n", i + 1, (i + 4) % NUM_PHILOSOPHERS + 1, i + 1);
    printf("Philosopher %d is Thinking\n", i + 1);
    test((i + 4) % NUM_PHILOSOPHERS);
    test((i + 1) % NUM_PHILOSOPHERS);
    sem_post(&mutex);
}

void* philosopher(void* num) {
    while (1) {
        int i = *(int*)num;
        sleep(1);
        take_fork(i);
        sleep(2);
        put_fork(i);
    }
}

int main() {
    int i;
    pthread_t thread_id[NUM_PHILOSOPHERS];
    sem_init(&mutex, 0, 1);
    for (i = 0; i < NUM_PHILOSOPHERS; i++)
        sem_init(&S[i], 0, 0);

    for (i = 0; i < NUM_PHILOSOPHERS; i++)
        pthread_create(&thread_id[i], NULL, philosopher, &phil[i]);

    for (i = 0; i < NUM_PHILOSOPHERS; i++)
        pthread_join(thread_id[i], NULL);
}
