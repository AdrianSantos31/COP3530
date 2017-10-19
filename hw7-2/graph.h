

#include <stdbool.h>
#include <stdio.h>
#include <malloc.h>

struct EdgeTag;
struct VertexStack;
struct VertexTag;
struct VertexQueue;

void stackPush(struct VertexStack* stack, struct VertexTag* vertex);
struct VertexTag* stackPop(struct VertexStack* stack);
struct VertexTag* stackPeek(struct VertexStack* stack);
void depthFirstSearch(struct VertexTag* vertices[]);
void queueInsert(struct VertexQueue* queue, struct VertexTag* vertex);
struct VertexTag* queueGetFront(struct VertexQueue* queue);
struct VertexTag* queuePeekFront(struct VertexQueue* queue);
void breadthFirstSearch(struct VertexTag* vertices[]);
void topoSort(struct VertexTag* vertices[]);
void createAdjList(struct VertexTag* vertices[], FILE* inputFile);

typedef struct VertexTag {
    char letter;
    bool isVisited;
    struct EdgeTag* edges;
    struct EdgeTag* tailEdge;
} Vertex;

typedef struct EdgeTag {
    struct VertexTag* connectingVertex;
    struct EdgeTag* nextEdge;
} Edge;

typedef struct VertexStack {
    struct VertexTag* vertexStack[26];
    int size;
} Stack;

typedef struct VertexQueue {
    struct VertexTag* vertexQueue[26];
    int size;
} Queue;
