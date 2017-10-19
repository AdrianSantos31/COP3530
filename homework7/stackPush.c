#include "my.h"
#define MAX_V 26 // This should be a sufficient size for the graphs
void stackPush(Stack *stack, Vertex *element) {
    if (stack->size < MAX_V) {
        stack->vertexStack[stack->size] = element;
        stack->size++;
    }
}
