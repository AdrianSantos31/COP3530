
//Library Imports
#include "my.h"
#define MAX_V 26 // This should be a sufficient size for the graphs

//Create Adjacency List Method
void createAdjList(Vertex* vertices[], FILE* inputFile) {

    //Vertex initialization
    Vertex* startVertex = vertices[0];
    Vertex* firstVertex = (Vertex*)malloc(sizeof(Vertex));
    Vertex* secondVertex = (Vertex*)malloc(sizeof(Vertex));

    //Adjacency List
    printf("\n");
    printf("Adjacency List\n");
    printf("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");
    while (fscanf(inputFile, "%c %c ", &firstVertex->letter, &secondVertex->letter) == 2) {
        firstVertex->edges = NULL;
        secondVertex->edges = NULL;
        firstVertex->isVisited = false;
        secondVertex->isVisited = false;

        //Check if vertex has been visited
        int i;
        for (i = 0; i < MAX_V; i++) {
            if (vertices[i]->letter == '?') {
                *vertices[i] = *firstVertex;
            }

            if (vertices[i]->letter == firstVertex->letter) {
                firstVertex = vertices[i];
                break;
            }
        }

        // End vertex
        for (i = 0; i < MAX_V; i++) {
            if (vertices[i]->letter == '?') {
                *vertices[i] = *secondVertex;
            }

            if (vertices[i]->letter == secondVertex->letter) {
                secondVertex = vertices[i];
                break;
            }
        }

        Edge* newEdge = (Edge*)malloc(sizeof(Edge));
        newEdge->nextEdge = NULL;
        newEdge->connectingVertex = secondVertex;

        //Put the edge to the end of the list
        if (firstVertex->edges == NULL) {
            firstVertex->edges = newEdge;
            firstVertex->edges->nextEdge = NULL;
            firstVertex->edges->connectingVertex = secondVertex;
            firstVertex->tailEdge = newEdge;
        }
        else {
            firstVertex->tailEdge->nextEdge = newEdge;
            firstVertex->tailEdge = newEdge;
        }

        // Create space for next set of vertices
        firstVertex = (Vertex*)malloc(sizeof(Vertex));
        secondVertex = (Vertex*)malloc(sizeof(Vertex));
    }

    // Since start vertex is adjacent to every other vertex, make sure everyone is adjacent
    int i;
    for (i = 1; i < MAX_V; i++) {
        Edge* edge = startVertex->edges;

        if (vertices[i]->letter == '?') {
            break;
        }
        else {
            while (edge != NULL) {
                if (edge->connectingVertex->letter == vertices[i]->letter) {
                    break;
                }
                else if (edge->nextEdge == NULL) {
                    edge->nextEdge = (Edge*)malloc(sizeof(Edge));
                    edge->nextEdge->connectingVertex = vertices[i];
                    edge->nextEdge->nextEdge = NULL;
                    startVertex->tailEdge = edge->nextEdge;
                }
                else {
                    edge = edge->nextEdge;
                }
            }
        }
    }

    // Printing the adjacency list
    for (i = 0; i < MAX_V; i++) {
        Edge* edge = vertices[i]->edges;
        if (vertices[i]->letter == '?') {
            break;
        }
        printf("%c || ", vertices[i]->letter);

          while (edge!= NULL){
            printf("%c ", edge->connectingVertex->letter);
            edge = edge->nextEdge;
		if(edge!=NULL){
	  	    printf("-> ");
		}
	    }
        printf("\n");
    }
    printf("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");
}
