#include "graph.h"
#define MAX_VERTICES 26 // This should be a sufficient size for the graphs
void stackPush(Stack *stack, Vertex *element) {
    if (stack->size < MAX_VERTICES) {
        stack->vertexStack[stack->size] = element;
        stack->size++;
    }
}
