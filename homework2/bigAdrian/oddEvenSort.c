#include "my.h"

//This program does the odd even sort

void oddEvenSort(int list[], int length){

//Local Declarations
int i, j;
int isSorted = 0;

//Statements
	while(!isSorted){
		isSorted = 1;
		for(i = 1; i < length-1; i += 2){
			if((list[i] > list[i+1])){
			swap(list, i, i+1);
			isSorted = 0;
			}//end if
		}//end for loop
		
		for(i = 0; i < length -1; i += 2){
			if((list[i] > list[i+1])){
				swap(list, i, i+1);
				isSorted = 0;
			}//end if
		}//end for loop
	}//end while
return;

}//end of function
