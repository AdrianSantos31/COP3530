
#include "my.h"
#define MAX_V 26 // This should be a sufficient size for the graphs
void queueInsert(Queue* vertexQueue, Vertex* vertex) {
    if (vertexQueue->size < MAX_V) {
        vertexQueue->vertexQueue[vertexQueue->size] = vertex;
        vertexQueue->size++;
    }
}
