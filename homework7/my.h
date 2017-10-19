
//Library Imports
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>

//Structures
struct EdgeTag;
struct VStack;
struct VTag;
struct VQueue;

//Method Headers
void bfs(struct VTag* vertices[]);
void AdjList(struct VTag* vertices[], FILE* inputFile);
void dfs(struct VTag* vertices[]);
int main(int argc, char* argv[]);
struct VTag* queueGetFront(struct VQueue* queue);
void queueInsert(struct VQueue* queue, struct VTag* vertex);
struct VTag* queuePeekFront(struct VQueue* queue);
struct VTag* stackPeek(struct VStack* stack);
struct VTag* stackPop(struct VStack* stack);
void stackPush(struct VStack* stack, struct VTag* vertex);
void topSort(struct VTag* vertices[]);

//Structure Definitions
//Vertex
typedef struct VTag {
    char letter;
    bool isVisited;
    struct EdgeTag* edges;
    struct EdgeTag* tailEdge;
} Vertex;

//Edge
typedef struct EdgeTag {
    struct VTag* connectingVertex;
    struct EdgeTag* nextEdge;
} Edge;

//Stack
typedef struct VStack {
    struct VTag* vertexStack[26];
    int size;
} Stack;

//Vertex Queue
typedef struct VQueue {
    struct VTag* vertexQueue[26];
    int size;
} Queue;
