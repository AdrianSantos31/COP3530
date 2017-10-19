/*
* * * * * * * * * * * * * Homework 7 * * * * * * * * * * * * * * * * * * * * * * *

* * * * * * * * * * * * * * * * * * * * * *
*  Written by: Adrian Santos (N00798593)  *
*  Date: 04/13/2017                       *
* * * * * * * * * * * * * * * * * * * * * *

1.  This program is to be written in C.
2.  You should write individual functions stored in separate files (one
function per file) and each function should use an include statement
for your header file.
3.  You should use one appropriate header file for all your structures
and for the prototypes of the functions you write
4.  You should not have any global variables in your
header file.
5.  You should use put all your .c's and your headerfile
myheader.h  in a separate folder
and shar those files together, $ shar -T *.c  myheader.h >bigone
6.  You should use turnin , $ turnin bigone kmartin.cop3530.a7

There will be deducts if any of the "rules " 1-4 are not followed (no
credit for a program written in Java);
 -25 for not using individual functions in individual files;
 -10 for not using a header file;
and -25 for using global variables in your header file.

I will run the program on Osprey using gcc :
$ gcc *.c
$ ./a.out myfile
(note this is NOT redirection and it is MY choice as to the name of the
data file, so use must program for a command line file name.)


myfile will consist of the vertices (single letters) for a directed,
unweighted graph.
e.g.  A  B
      B  C
      E  X
      C  D
      A  C

would indicate there are edges from A to B, B to C, E to X, C to D, and
A to C.

The array of vertices in this case would be [A,B,C,E,X D]
but this would not be all the edges because you may assume that all of
the vertices are reachable from the first vertex (A in this case).
You should start your depth first and breadth first searches from the
first vertex so that all the vertices are printed.

Here are the functions your program should call with no prompts whatsover:
a. Print the graph as  adjacency lists (see Table 13.2).  (If you get this properly for
adjacency lists you earn  an 85.)
b. Call the depth first search. (5 more points.)
c. Call the breadth first search. (5 more points.)
d. Call the topological sort (you may assume there are
no cycles--see pg. 654) (5 more points.)

So you should have functions that build the adjacency lists, perform the
depth first search, perform the breadth first search, and perform the
topological sort.   You MUST use adjacency lists to construct the graph,
NOT a 2x2 array.
*/



//Library Imports
#include "my.h"
#define MAX_V 26 // This should be a sufficient size for the graphs

//Main Method
int main(int argc, char* argv[]) {
    FILE* inputFile = fopen(argv[1], "r");

    if (inputFile == NULL) {
        printf("Error: File cannot be found, please try again: \n");
        return(1);
    }

    Vertex* vertices[MAX_V];
    int i;//for loop initialization
    for (i = 0; i < MAX_V; i++) {
        vertices[i] = (Vertex*)malloc(sizeof(Vertex));
        vertices[i]->letter = '?';
    }

    createAdjList(vertices, inputFile);
    dfs(vertices);
    bfs(vertices);
    topSort(vertices);

    return(0);
}
