/** LinkedList class
  * Linked list to represent graphs;
  * provides buildGraph, printLinkedList, removeLoops,
  * checkRowEmpty, and setCurrentRow
  *
  * @author Mike Ogrysko
  *
  */

import java.util.Scanner;
import java.io.*;

public class LinkedList {
   // Head of the linked list
   private Node listHead;
   // Counter for number of nodes read
   private int nodeCount;

   /** buildGraph()
    * The method buildGraph() takes the values in the matrix and stores
    * them in a linked list
    *
    * Pre-Conditions: input is a Scanner and graphSize is an integer
    *
    * Post-Conditions: na
    *
    * @param input Scanner
    * @param graphSize int
    *
    * @author Mike Ogrysko
    */
   void buildGraph(Scanner input, int adjMatrixSize) { // start buildGraph()
      // Set nodeCount equal to 0
      nodeCount = 0;
      // Initialize row headers
      Node[] head = new Node[adjMatrixSize];
      Node nextTemp = new Node();
      Node newPointer;
      // Creating linked lists based on the graph size - rows of linked lists
      for (int i = 0; i < adjMatrixSize; i++) {
         head[i] = null;
         for (int j = 0; j < adjMatrixSize; j++) {
            if (input.hasNextInt()) {
        	      // Creating a node to store the input value
        	      newPointer = new Node(input.nextInt(),i,j);
               // Setting listHead as the head of the overall linked list
        	      if (listHead == null) {
        	         listHead = newPointer;
        	      }
        	      if (head[i] == null) {
        	         head[i] = newPointer;
        	      }
        	      else {
        	         nextTemp.next = newPointer;
        	      }
        	      nextTemp = newPointer;
        	      nodeCount++;
            }
         }
      }
      // Setting the column pointers
      for (int i = 0; i < (adjMatrixSize - 1); i++) {
         Node tempNode1 = head[i];
         Node tempNode2 = head[i + 1];
         while (tempNode1 != null && tempNode2 != null) {
            tempNode1.below = tempNode2;
            tempNode1 = tempNode1.next;
            tempNode2 = tempNode2.next;
         }
      }
   } // end buildGraph()


   /** nodeCount()
    * The method nodeCount() returns the number of nodes built into the graph
    *
    * Pre-Conditions: na
    *
    * Post-Conditions: integer nodeCount is returned
    *
    * @return nodeCount int
    *
    * @author Mike Ogrysko
    */
   public int nodeCount() { // start nodeCount()
      return nodeCount;
   } // end nodeCount()


   /** printLinkedList()
    * The method printLinkedList() displays and prints the linked list
    *
    * Pre-Conditions: outFile is a FileWriter
    *
    * Post-Conditions: na
    *
    * @param outFile FileWriter
    *
    * @author Mike Ogrysko
    */
   void printLinkedList(FileWriter outFile) { // start printLinkedList()
      // Initialize Nodes moveNext and moveBelow
      Node moveNext;
      // Set moveBelow to the listHead
      Node moveBelow = listHead;
      // Iterate through the row
      while (moveBelow != null) {
         // Set moveNext equal to moveBelow (or current)
         moveNext = moveBelow;
         // Iterate through the columns and display/print values
         while (moveNext != null) {
            System.out.print(moveNext.data + " ");
            try {
               outFile.write(moveNext.data + " ");
            }
            catch (Exception e) {
               System.out.println("\nError writing to file.");
            }
            // Move to the next value in the row
            moveNext = moveNext.next;
         }
         System.out.println();
         try {
            outFile.write("\n");
         }
         catch (Exception e) {
            System.out.println("\nError writing to file.");
         }
         // Move to the next row
         moveBelow = moveBelow.below;
      }
   } // end printLinkedList()

   /** removeLoops()
    * The method removeLoops() finds loops and sets the values to 0,
    * erasing the loops
    *
    * Pre-Conditions: na
    *
    * Post-Conditions: na
    *
    * @author Mike Ogrysko
    */
   void removeLoops() { // start removeLoops()
      Node moveNext;
      Node moveBelow = listHead;
      // Iterate through the rows
      while (moveBelow != null) {
         moveNext = moveBelow;
         // Iterate through the columns
         while (moveNext != null) {
            // If the column and row are the same and the value is 1, set the value to 0
            if ((moveNext.data == 1) && (moveNext.col == moveNext.row)) {
               moveNext.data = 0;
            }
            // Move to the next value in the row
            moveNext = moveNext.next;
         }
         // Move to the next row
         moveBelow = moveBelow.below;
      }
   } // end removeLoops()


   /** checkRowEmpty()
    * The method checkRowEmpty() determines if a row is empty and can be skipped
    *
    * Pre-Conditions: currentNode is a Node, adjMatrixSize is an integer
    *
    * Post-Conditions: return boolean empty
    *
    * @param currentNode Node
    * @param adjMatrixSize int
    * @return empty boolean
    *
    * @author Mike Ogrysko
    */
   boolean checkRowEmpty(Node currentRow, int adjMatrixSize) { // start checkRowEmpty()
      // Initialize and set empty equal to false
      boolean empty = false;
      // Iterate through the values in each row
      for (int i = 0; i < adjMatrixSize; i++) {
         // If the value in the row is equal to 1, it is not empty
         if ((currentRow.col == i) && (currentRow.data) == 1) {
           	empty = false;
            return empty;
         }
         // Else mark it empty
         else {
            empty = true;
         }
         // Move to the next value
         currentRow = currentRow.next;
      }
      return empty;
   } // end checkRowEmpty()


   /** setCurrentRow()
    * The method setCurrentRow() searches for and returns the current node
    *
    * Pre-Conditions: currentNode is an integer
    *
    * Post-Conditions: moveNext Node is returned
    *
    * @param currentNode int
    * @return moveNext Node
    *
    * @author Mike Ogrysko
    */
   Node setCurrentRow(int currentNode) { // start setCurrentRow()
      Node moveNext = null;
      Node moveBelow = listHead;
      // Move through the rows
      while (moveBelow != null) {
        	moveNext = moveBelow;
        	// Move through values in the row
        	while (moveNext != null) {
        	   // If the row is equal to the current node return the node
        	   if (moveNext.row == currentNode) {
        	      return moveNext;
        	   }
        	   // Else move to the next value in the row
        	   else {
        	      moveNext = moveNext.next;
        	   }
        	}
        	// Move to the next row
        	moveBelow = moveBelow.below;
      }
      return moveNext;
   } // end setCurrentRow()
}
