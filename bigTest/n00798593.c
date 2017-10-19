/* This program will do the following
	1.) Read a data of integers from the command line
	2.) Sort the integers using an odd-even sort
	3.) Ask the user for an index inside the sorted array
	4.) The program will return the value inside the user-input index
	
	Written by: Adrian Santos (N00798593)
	Date: 01/23/2017
*/

//Preprocessor
#include "my.h"

//Main Method
int main(int argc, char *argv[1]){

	//Local Declarations
	int i = 0;
	int *b;
	int size;
	int j;
	int key;
	FILE* file = fopen(argv[1], "r");

	//Statements
		//File Checker
	        if(file == NULL){
		printf("Data not found. Please run the program again with the data.");
		exit(1);
		}//end if 

		//Read a file
		fscanf(file, "%d", &size);
   		//printf("\nHere is the size of the array: %d \n",size);
 		
		//Calloc file
		b = (int*)calloc(size, sizeof(int));
		
	
		printf("\nHere is the data from the data file\n");
   		for(j=0; j<size; j++){
    			fscanf(file, "%d ", &b[j]);
   			//printf("%d ", b[j]);
  		 }
		

		//Sorted the array
		oddEvenSort(b, size);
		printf("The array is already sorted. \n");
	/*	for(j=0; j<size; j++){
			printf("%d ", b[j]);	
		}*/		
		
		//Get an input from the user
		while(!(key == -1)){
		printf("Please enter a number to find its index (-1 ends the program): ");
		scanf("%d", &key);
		if(!(key == -1)){		
			if(key > size-1){
				printf("The number entered is too large. Please enter an index less than or equal %d \n", size-1);
				continue;
			}//end if
			else if(key < -1){
				printf("The entered is below the array index. Please enter a number larger than -2 \n");
				continue;
			}//end else if
			else
			printf("The number found at index %d is: %d \n", key, b[key]);
			}//end if 
		}//end while

		fclose(file);
		free(b);
				
}//end main
