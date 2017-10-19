#include "my.h"

void swap(int list[], int i, int j){

	int temp = list[i];
	list[i] = list[j];
	list [j] = temp;

	return;
}//end of function
