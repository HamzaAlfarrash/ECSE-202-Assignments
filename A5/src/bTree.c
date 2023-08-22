/*
 ============================================================================
 Name        : bTree.c
 Author      : Hamza Alfarrash
 Version     :
 Copyright   : Your copyright notice
 Description :
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>
#include "bTree.h"

// "Instance" Variables - global to all functions in the bTree.c file.
bNode *root = NULL; // bTree root node
int numNodes = 0; // Number of nodes in tree

void initTree() {
	// Initialize the bTree
	root = NULL;
	numNodes = 0;
}
// Adds a new node to the bTree.
void addNode(char *data) {
	bNode *current;

	//for empty tree
	if (root == NULL) {
		root = makeNode(data);
	}
	// If not empty, descend to the leaf node according to
	// the input data.
	else {
		current = root;
		while (true) {

			if (keyCompareToIgnoreCase(data, current->data) < 0) { // Compare tokens

				// if (data.compareToIgnoreCase(current.data) < 0) { // Compare strings

				// New data < data at node, branch left
				if (current->left == NULL) { // leaf node
					current->left = makeNode(data); // attach new node here
					break;
				} else {                          //otherwise
					current = current->left;    //keep traversing
				}
			} else {
				// New data >= data at node, branch right
				if (current->right == NULL) {         // leaf node
					current->right = makeNode(data);  // attach
					break;
				} else {                           // otherwise
					current = current->right;    // keep traversing
				}
			}
		}
	}
}
bNode* makeNode(char *data) { // Function that allocates a bNode
	bNode *node = malloc(sizeof(bNode)); //allocates memory for node (in heap)
	numNodes++; //increments to num of nodes
	node->data = data; //assigns data
	//initializes node childs to null
	node->left = NULL;
	node->right = NULL;
	return node; //returns the created node
}
int keyCompareToIgnoreCase(char *name1, char *name2) { // Modified version of strcasecmp to handle proper names.

	//get last names
	char *lastName1 = lastToken(name1);
	char *lastName2 = lastToken(name2);
	// compare last names
	return strcasecmp(lastName1, lastName2);

}
char* lastToken(char *input) { // Returns index of last token in string.
	for (int i = strlen(input) - 1; i >= 0; i--) {
		char c = input[i];
		if (c == ' ') {
			return (char*) (input + i + 1);
		}
	}
	return input; // no space, compare with entire name
}
void displayInOrder() { // Prints bTree in forward order by calling recursive traversal function
	traverseInOrder(root);
}
void traverseInOrder(bNode *root) { // The recursive function that performs inorder traversal
	// left, root, right
	if (root->left != NULL) {
		traverseInOrder(root->left);
	}
	printf("%s\n", root->data);

	if (root->right != NULL) {
		traverseInOrder(root->right);
	}
}
void displayInReverseOrder() { // Prints bTree in reverse order by calling recursive traversal function
	traverseInReverseOrder(root);
}
void traverseInReverseOrder(bNode *root) { // Same as InOrder with order of recursion reversed
	// right, root, left
	if (root->right != NULL) {
		traverseInReverseOrder(root->right);
	}
	printf("%s\n", root->data);

	if (root->left != NULL) {
		traverseInReverseOrder(root->left);
	}

}
void deleteTree() {					// Deletes the bTree (all allocated nodes)
	postOrder(root);

}
void postOrder(bNode *root) { // Post order traversal used to delete tree nodes
	// checks: left, right, then frees node
	if (root->left != NULL) {
		postOrder(root->left);
	}
	if (root->right != NULL) {
		postOrder(root->right);
	}
	free(root->data); // Deallocate buffer
	free(root); // Deallocate bNode
}
int getNumNodes() { // Getter returns the number of nodes in the tree.
	return numNodes;
}

