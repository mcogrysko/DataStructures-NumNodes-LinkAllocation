/** LinkedStack class
  * Linked implementation of stack to hold path nodes;
  * provides push, pop, peek, isEmpty, size,
  * display, and printFile methods
  *
  * @author Mike Ogrysko
  *
  */


import java.util.*;
import java.io.*;

public class LinkedStack {

   // Initialize class Node
   private class Node {
   int data;
   Node link;
   }
   // Initialize and set Node top and int size
   private Node top = null;
   private int size;


   /** LinkedStack()
    * The method LinkedStack() sets top to null
    *
    * Pre-Conditions: na
    *
    * Post-Conditions: na
    *
    * @author Mike Ogrysko
    */
   public LinkedStack() { // start LinkedStack()
   top = null;
   size = 0;
   } // end LinkedStack()


   /** isEmpty()
    * The method isEmpty() returns t if empty
    *
    * Pre-Conditions: na
    *
    * Post-Conditions: returns boolean
    *
    * @return boolean
    *
    * @author Mike Ogrysko
    */
   public boolean isEmpty() { // start isEmpty()
   if (top == null) {
   return true;
   }
   else {
   return false;
   }
   } // end isEmpty()


   /** push()
    * The method push() adds element to the top of the stack
    *
    * Pre-Conditions: element must be an integer object
    *
    * Post-Conditions: na
    *
    * @param x int
    *
    * @author Mike Ogrysko
    */
   public void push(int x) { // start push()
   Node n = new Node();
   n.data = x;
   n.link = (top);
   (top) = n;
   size++;
   } // end push()


   /** peek()
    * The method peek() returns the top element of the stack w/o removing it
    *
    * Pre-Conditions: NA
    *
    * Post-Conditions: returns Passenger
    *
    * @return top.data int
    *
    * @author Mike Ogrysko
    */
   public int peek() { // start peek()
   if (!isEmpty()) {
   return top.data;
   }
   else {
   System.out.println("Empty");
   return -1;
   }
   } // end peek()


   /** pop()
    * The method pop() removes the top element of the stack
    *
    * Pre-Conditions: na
    *
    * Post-Conditions: returns top.data
    *
    * @return top.data int
    *
    * @author Mike Ogrysko
    */
   public int pop() { // start pop()
   if (top == null) {
   System.out.println("Underflow");
   }
   top = (top).link;
   size--;
   return top.data;
   } // end pop()


   /** size()
    * The method size() returns the length of the stack
    *
    * Pre-Conditions: na
    *
    * Post-Conditions: returns integer
    *
    * @return size int
    *
    * @author Mike Ogrysko
    */
   public int size() { // start size()
   return size;
   } // end size()


   /** display()
    * The method display() shows the full path on screen
    *
    * Pre-Conditions: NA
    *
    * Post-Conditions: NA
    *
    * @author Mike Ogrysko
    */
   public void display() { // start display()
   Stack<Integer> holdStack = new Stack<Integer>();
   if (top == null) {
   System.out.println("Empty stack");
   return;
   }
   else {
   Node n = top;
   while (n != null) {
   holdStack.push(n.data);
   n = n.link;
   }
   while (!holdStack.isEmpty()) {
   if (holdStack.size() > 1) {
   System.out.print((holdStack.peek() + 1) + "->");
   holdStack.pop();
   }
   else {
   System.out.print((holdStack.peek() + 1));
   holdStack.pop();
   }

   }
   }
   System.out.println();
   } // end display()


   /** printFile()
   * The method printFile() writes the paths for each set of nodes to a text file
   *
   * Pre-Conditions: outFile is FileWriter
   *
   * Post-Conditions: NA
   *
   * @param outFile FileWriter
   *
   * @author Mike Ogrysko
   */
   public void printFile(FileWriter outFile) { // start printFile()
   Stack<Integer> holdStackPrint = new Stack<Integer>();
   try {
   Node n = top;
   while (n != null) {
   holdStackPrint.push(n.data);
   n = n.link;
   }
   while (!holdStackPrint.isEmpty()) {
   if (holdStackPrint.size() > 1) {
   outFile.write((holdStackPrint.peek() + 1) + "->");
   holdStackPrint.pop();
   }
   else {
   outFile.write((holdStackPrint.peek() + 1) + "  ");
   holdStackPrint.pop();
   }
   }
   outFile.write("\n");
   }
   catch (Exception e) {
            System.out.println("\nError writing to file.");
       }
   } // end printFile()
}
