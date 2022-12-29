/** Node class
  * Node for linked list; sets data, row, and col values;
  * sets the next and below pointers
  *
  * @author Mike Ogrysko
  *
  */

public class Node {
   // Initialize integers data, row, and col and Nodes next and below
   int data;
   int row;
   int col;
   Node next;
   Node below;


   /** Node()
    * Default constructor for node
    *
    * Pre-Conditions: Nodes next and below are set to null
    *
    * Post-Conditions: na
    *
    * @author Mike Ogrysko
    */
   Node() {
      this.next = null;
      this.below = null;
   }


   /** Node()
    * Clone for Node passes objects to constructor
    *
    * Pre-Conditions: integers d, r, and c
    *
    * Post-Conditions: na
    *
    * @param d int
    * @param r int
    * @param c int
    *
    * @author Mike Ogrysko
    */
   Node(int d, int r, int c) {
      this.data = d;
      this.row = r;
      this.col = c;
      this.next = null;
      this.below = null;
   }
}
