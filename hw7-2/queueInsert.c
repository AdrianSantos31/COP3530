
#include "graph.h"
#define MAX_VERTICES 26 // This should be a sufficient size for the graphs
void queueInsert(Queue* vertexQueue, Vertex* vertex) {
    if (vertexQueue->size < MAX_VERTICES) {
        vertexQueue->vertexQueue[vertexQueue->size] = vertex;
        vertexQueue->size++;
    }
}
