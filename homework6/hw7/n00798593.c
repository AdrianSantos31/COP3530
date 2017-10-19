/*
 * n00798594.c
 *
 *  Created on: Apr 13, 2017
 *      Author: Ian
 */
//Preprocessor Files
#include "my.h"

int main(int argc, const char *argv[1])
{
    //File Reader
	FILE *file = fopen(argv[1], "r");	
	char x[100];
        long size;
	int i = 0;//For loop

	if(file==NULL){
	    printf("File not found, please try again: \n");
	    exit(1);
        }
	
	while(fgets(x,100,file)!=NULL){
	 printf(x);
	}
//--------------------------------------------	

	for(i=0; i<=35;i++){
	   printf("-");
	}
	printf("\n");//Line Cleaner

	for(i=0; i<=10; i++){
	printf("%c ", x[i]);	 
 	}

	printf("\n");//Line Cleaner
 //------------------------------------------------------------------   

    //Local Variables
    EDGE* e = (EDGE*)malloc(sizeof(EDGE));
    VERTEX* a = (VERTEX*)malloc(sizeof(VERTEX));
    VERTEX* b = (VERTEX*)malloc(sizeof(VERTEX));
		    
   for(i = 0; i<=35; i++){
	printf("-");	
	}
   printf("\n");//Line Separator
    	
    a->c = 'A';
    b->c = 'B';


    e->v = b;
    a->p = e;


    printf("%c -> %c\n", a->c, a->p->v->c);
    return 0;
}

