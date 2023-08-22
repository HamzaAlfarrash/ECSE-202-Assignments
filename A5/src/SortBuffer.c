/*
 ============================================================================
 Name        : Sortbuffer.c
 Author      : Hamza Alfarrash
 Version     :
 Copyright   : Your copyright notice
 Description :
 ============================================================================
 */

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "bTree.h"

#define bufferSize 80
#define false 0
#define true !false

char* readLine(); //calling the function to avoid error
/*
 * main function reads input from through
 * readLine function and stores the values
 * in a bTree. then displays sorted and reverse
 * sorted values respectively
 */
int main(int argc, char *argv[]) {

	//write welcome message
	printf("Text Sorting Program: (ECSE 202 - Assignment 5)\n");
	printf("Enter names to be sorted, line by line. A blank line terminates.\n");
	printf("You can cut and paste text into this window:\n");
	initTree();
	while (true) {
		//get input
		char *input = readLine(); // stores the returned buffer
		if (input == NULL) { //heap is full
			printf("couldn't allocate memory \n"); //error message
			return -1; //error
		} else if (strlen(input) < 1) { //no more names
			break; //user entered break line
		} else {
			addNode(input); //else, add node to bTree
		}
	}
	if (getNumNodes() == 0) { //if empty line, break
		printf("Tree is empty, nothing to sort.");
	} else {
		printf("Text in sort order: \n"); // traverse inOrder
		displayInOrder(); //prints the sorted name list
		printf("\n");
		printf("Text in reverse sort order: \n"); // traverse inReverseOrder
		displayInReverseOrder(); //prints the name list in reverse
		printf("\n");
	}

	printf("Program terminated"); //end of program
	return 0;
}

/*
 * replicates the built-in readLine method in Java
 * reads input from user and concatenates them to form
 * a "String"
 */
char* readLine() {
	//allocate memory for input string (buffer)
	char *buffer = malloc(bufferSize * sizeof(char));

	// fgets -> ([where we store result], [length of what we read]
	//                ,[where we read from])
	if (buffer != NULL) {
		fgets(buffer, bufferSize * sizeof(char), stdin); //reads a limited number of characters into a array of chars
		if (strlen(buffer) > 0) {
			buffer[strlen(buffer) - 1] = '\0'; // assigns the terminal character
		}
	}
	return buffer;
}
