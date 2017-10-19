/*
 * my.h
 *
 *  Created on: Apr 13, 2017
 *      Author: Ian
 */

//Preprocessor
#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int main(int argc, const char *argv[1]);

/* Forward declaration */
struct EDGETAG;


typedef struct
{
    char c;
    bool isVisited;
    struct EDGETAG* p;
} VERTEX;


typedef struct EDGETAG
{
    VERTEX* v;
    struct EDGETAG* q;
} EDGE;
