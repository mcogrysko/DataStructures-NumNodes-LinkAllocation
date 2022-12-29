/** AdjMatrix
  * Reads adjacency matrices from text files and displays/prints paths for all nodes in
  * each matrix
  *
  * @author Mike Ogrysko
  *
  */


import java.util.*;
import java.io.*;


public class AdjMatrix {
   public static void main(String[] args) throws IOException { // Start main
      if (args.length != 2) {
         throw new IllegalArgumentException("\n\nOne or both file args are missing. \n\nPlease provide them in quotes - e.g., java AdjMatrix \"input.txt\" \"output.txt\"\n");
      }
      FileReader inFile = null;
      FileWriter outFile = null;
      inFile = new FileReader(args[0]);
      File ifile = new File(args[0]);
      Scanner input = new Scanner(inFile);
      BufferedReader inputCheck = new BufferedReader(new FileReader(args[0]));
      outFile = new FileWriter(args[1]);
      File ofile = new File(args[1]);
      boolean stopApplication = false;
      // Catch blank files
      if (ifile.length() == 0) {
         System.out.println("This file is empty...Stopping.");
         outFile.write("\nThis file is empty...Stopping.\n\n");
         outFile.close();
      }
      // Assuming the file is not blank
      else {
         // Intro headers
         displayHeaders(ifile,ofile,outFile);
         /*
         * This will check the file before processing. We'll use BufferedReader to grab each line and
         * do comparisons by length of the line.
         */
    	  	// Initialize line and set it equal to null
         String line = null;
         // Initialize data, matrixCount, matrixRowCount and  and set it equal to 0
         int data = 0;
         int matrixCount = 0;
         int matrixRowCount = 0;
         int matrixPrint = 0;
         // Iterate through the file
        	while ((line = inputCheck.readLine() ) != null) {
     	      // Initialize and set rowLength equal to the length of the line
     	      int rowLength = line.length();
     	      // If it is a matrix size, grab the size
            if (rowLength == 1 || rowLength == 2) {
     	  	      matrixCount++;
               matrixRowCount = 0;
     	  	      data = Integer.parseInt(line);
     	  	   }
     	  	   else {
     	  	      // Remove the spaces from the line for the other lines
     	  	      rowLength = line.replaceAll("\\s","").length();
     	  	      matrixRowCount++;
     	  	   }
     	  	   // If the matrix size is equal to the number of rows
     	  	   if (data == matrixRowCount) {
     	  	      System.out.println("\nMatrix #" + matrixCount);
               outFile.write("\nMatrix #" + matrixCount);
               System.out.println("Matrix Size: " + data + ", Columns: " + rowLength + ", Rows: " + matrixRowCount);
               outFile.write("\nMatrix Size: " + data + ", Columns: " + rowLength + ", Rows: " + matrixRowCount);
               // If the matrix is square
               if (data == rowLength && data == matrixRowCount) {
                  System.out.println("Matrix #" + matrixCount + " is square.");
                  outFile.write("\nMatrix #" + matrixCount + " is square.\n");
                  matrixPrint++;
               }
               // If the # of rows is greater than the # of cols, print message and set stopApplication to true
               else if (rowLength < matrixRowCount) {
                  System.out.println("Matrix #" + matrixCount + " is not square. The number of rows is greater than the number of columns.");
                  outFile.write("\nMatrix #" + matrixCount + " is not square. The number of rows is greater than the number of columns.\n");
                  stopApplication = true;
                  matrixPrint++;
     	  	    	}
               // If the # of rows is less than the # of cols, print message and set stopApplication to true
               else if (rowLength > matrixRowCount) {
                  System.out.println("Matrix #" + matrixCount + " is not square. The number of rows is less than the number of columns.");
                  outFile.write("\nMatrix #" + matrixCount + " is not square. The number of rows is less than the number of columns.\n");
                  stopApplication = true;
                  matrixPrint++;
               }
               // If the size is not = to the # of rows or the # of cols, print message and set stopApplication to true
               else {
                  System.out.println("Matrix #" + matrixCount + " has a problem. The matrix size specified does not align with the number of rows and columns.");
                  outFile.write("\nMatrix #" + matrixCount + " has a problem. The matrix size specified does not align with the number of rows and columns.\n");
                  stopApplication = true;
                  matrixPrint++;
     	  	    	}
            }
         }
         inputCheck.close();
         // If stopApplication is false, continue
         if (stopApplication == false) {
            System.out.println("\nAdjacency List Paths: ");
            outFile.write("\n\nAdjacency List Paths: \n\n");
            // Read the matrix and putting it into a list
            processAdjMatrix(input, outFile);
         }
         // else, stop the application
         else {
            if (matrixPrint != matrixCount) {
               System.out.println("\nThere should be " + matrixCount + " matrices in the input. The number of rows in one may be less than the specified size.");
               outFile.write("\nThere should be " + matrixCount + " matrices in the input. The number of rows in one may be less than the specified size.\n");
            }
            System.out.println("\nStopping application. Check input file.\n");
            outFile.write("\nStopping application. Check input file.\n");
            outFile.close();
         }
      }
   }


   /** displayHeaders()
    * Displays and prints headers for output
    *
    * Pre-Conditions: ifile and ofile are of File type; outFile is FileWriter type
    *
    * Post-Conditions: message is printed on screen and in file
    *
    * @param ifile File
    * @param ofile File
    * @param outFile FileWriter
    *
    * @author Mike Ogrysko
    */
   public static void displayHeaders(File ifile, File ofile, FileWriter outFile) { // start displayHeaders()
      try {
         // Output heading messages
         System.out.println("\nMike Ogrysko");
         outFile.write("Mike Ogrysko\n");
         System.out.println("\njava AdjMatrix \"" + ifile.getName() + "\" \"" + ofile.getName() + "\"");
         outFile.write("java AdjMatrix \"" + ifile.getName() + "\" \"" + ofile.getName() + "\"\n");
         System.out.println("\nReading \"" + ifile.getName() + "\" and writing to \"" + ofile.getName() + "\"");
         outFile.write("Reading \"" + ifile.getName() + "\" and writing to \"" + ofile.getName() + "\"\n");
      }
      catch (Exception e) {
         System.out.println("\nError writing headers.");
      }
   } // end displayHeaders()


   /** processAdjMatrix()
    * Runs through the file, creates the list and removes loops; runs listIteration
    *
    * Pre-Conditions: input and outFile are of Scanner and FileWriter types
    *
    * Post-Conditions: message is printed on screen and in file
    *
    * @param input File Scanner
    * @param outFile FileWriter
    *
    * @author Mike Ogrysko
    */
   public static void processAdjMatrix(Scanner input,
                              FileWriter outFile) throws IOException { // start processAdjMatrix()
      int adjMatrixSize = 0;
      boolean[] resultsArray = new boolean[0];
      while (input.hasNextInt()) {
         try {
            // Read the first line as the size
            adjMatrixSize = input.nextInt();
            // Catch if 0 or 1, also works if missing sizes
            if (adjMatrixSize < 2) {
               System.out.println("\nMatrix size must be 2 or greater. Stopping...Please revisit input.");
               outFile.write("\nMatrix size must be 2 or greater. Stopping...Please revisit input.\n");
               break;
            }
            else {
               // Print matrix size provided
               System.out.println("\n" + adjMatrixSize);
               outFile.write("\n" + adjMatrixSize + "\n");
            }
         }
         catch (NumberFormatException e) {
            System.out.println("\nError writing matrix size. Matrix size must be an integer.");
         }
         LinkedList adjMatrixList = new LinkedList();
         adjMatrixList.buildGraph(input,adjMatrixSize);
         if (adjMatrixList.nodeCount() == (adjMatrixSize * adjMatrixSize)) {
            adjMatrixList.printLinkedList(outFile);
            System.out.println();
            try {
               outFile.write("\n");
            }
            catch (Exception e) {
               System.out.println("\nError adding space to file.");
            }
            // Initialize visitArray to capture visits during recursive call
            boolean[] visitArray = new boolean[adjMatrixSize];
            // Iterate through array to remove loops - setting the value to 0
            adjMatrixList.removeLoops();
            listIteration(adjMatrixList, adjMatrixSize, resultsArray, visitArray, outFile);
         }
         else {
            System.out.println("The size of the matrix (" + adjMatrixSize + " x " + adjMatrixSize + ") does not align with the number of nodes read (" + adjMatrixList.nodeCount() + ")...stopping. Please review the input file.");
            break;
         }
         // Catch a blank line at the end of the file
         if (input.hasNextInt()) {
        	   if (input.nextLine().equalsIgnoreCase("")) {
        	      System.out.print("");
            }
         }
      }
      input.close();
      outFile.close();
   } // end processAdjMatrix()


   /** listIteration()
    * Reads adjMatrixList runs displayPaths() for each set of nodes;
    * if not, print a message saying no path found
    *
    * Pre-Conditions: adjMatrixList is a LinkedList, resultsArray and visitArray
    * are boolean arrays and outFile is of FileWriter type
    *
    * Post-Conditions: na
    *
    * @param adjMatrixList LinkedList
    * @param resultsArray boolean[]
    * @param visitArray boolean[]
    * @param outFile FileWriter
    *
    * @author Mike Ogrysko
    */
   public static void listIteration(LinkedList adjMatrixList, int adjMatrixSize, boolean[] resultsArray,
                              boolean[] visitArray, FileWriter outFile) { // start listIteration()
      // Iterate through list to print headers and paths
      for (int i = 0; i < adjMatrixSize; i++) {
         for (int j = 0; j < adjMatrixSize; j++) {
            // Define resultsArray and set all values to false
            resultsArray = new boolean[adjMatrixSize];
            for (int x = 0; x < resultsArray.length; x++) {
               resultsArray[x] = false;
            }
            System.out.println("\nPaths from " + (i + 1) + " to " + (j + 1) + ":");
            try {
               outFile.write("\nPaths from " + (i + 1) + " to " + (j + 1) + ":\n");
               displayPaths(i,j,visitArray,adjMatrixList,adjMatrixSize,resultsArray,outFile);
               noPathMessage(resultsArray,outFile);
            }
            catch (Exception e) {
               System.out.println("\nError writing file.");
            }
         }
      }
   } // end listIteration()


   /** noPathMessage()
    * Reads resultsArray and determines if there was a path between 2 nodes;
    * if not, print a message saying no path found
    *
    * Pre-Conditions: resultsArray is a boolean array and outFile is of FileWriter type
    *
    * Post-Conditions: message is printed on screen and in file
    *
    * @param resultsArray boolean[]
    * @param outFile FileWriter
    *
    * @author Mike Ogrysko
    */
   public static void noPathMessage(boolean[] resultsArray,FileWriter outFile) throws IOException { // start noPathMessage()
      // Initialize and set x and count equal to 0
      int x = 0;
      int count = 0;
      // Iterate through to find a true value - we're looking for a row full of falses to print the message
      while (resultsArray.length-1 >= x) {
         if (resultsArray[x] == true) {
            count--;
            x++;
         }
         else {
            count++;
            x++;
         }
      }
      if (count == resultsArray.length) {
         System.out.println("No Path Found");
         outFile.write("No Path Found\n");
      }
   } // end noPathMessage()


   /** displayPaths()
    * Takes startNode, endNode, visitArray, adjMatrixList, adjMatrixSize, resultsArray and
    * calls findPaths to find and display all paths between startNode and endNode
    *
    * Pre-Conditions: startNode, endNode, & adjMatrixSize are integers, visitArray is a boolean array,
    * adjMatrixList is a LinkedList, resultsArray is a boolean array, and outFile is of FileWriter type
    *
    * Post-Conditions: paths or no path found is printed
    *
    * @param startNode int
    * @param endNode int
    * @param visitArray boolean[]
    * @param adjMatrixList LinkedList
    * @param adjMatrixSize int
    * @param resultsArray boolean[]
    * @param outFile FileWriter
    *
    * @author Mike Ogrysko
    */
   public static void displayPaths(int startNode, int endNode, boolean[] visitArray,
      							LinkedList adjMatrixList, int adjMatrixSize, boolean[] resultsArray,
                                    FileWriter outFile) throws IOException { // start displayPaths()
      // Initialize pathStack
      LinkedStack pathStack = new LinkedStack();
      // Initialize and set firstPass equal to true - used in recursive method
      boolean firstPass = true;
      // If the row is completely empty, don't bother with recursion, just say no path found
      if (adjMatrixList.checkRowEmpty(adjMatrixList.setCurrentRow(startNode),adjMatrixSize)) {
         // Set resultsArray equal to true for current node to avoid conflicting message
         resultsArray[startNode] = true;
         System.out.println("No Path Found");
         outFile.write("No Path Found\n");
         return;
      }
      else {
         // Add startValue to path
         pathStack.push(startNode);
         // Method will run recursively to build path
         findPaths(startNode, endNode, startNode, visitArray, adjMatrixList, adjMatrixSize, pathStack, firstPass, resultsArray, outFile);
      }
   } // end displayPaths()


   /** findPaths()
    * Recursive method that takes currentNode, endNode, origStartNode, visitArray, adjMatrixList,
    * adjMatrixSize, localPathStack, firstPass, resultsArray, and outFile to find and display all
    * paths between startNode and endNode
    *
    * Pre-Conditions: currentNode, endNode, and adjMatrixSize are integers, visitArray is a boolean array,
    * adjMatrixList is a LinkedList, localPathStack is LinkedStack, firstPass is boolean,
    * resultsArray is a boolean array, and outFile is of FileWriter type
    *
    * Post-Conditions: If found, paths are printed
    *
    * @param currentNode int
    * @param endNode int
    * @param origStartNode int
    * @param visitArray boolean[]
    * @param adjMatrixList LinkedList
    * @param adjMatrixSize int
    * @param localPathStack LinkedStack
    * @param firstPass boolean
    * @param resultsArray boolean[]
    * @param outFile FileWriter
    *
    * @author Mike Ogrysko
    */
   public static void findPaths(int currentNode, int endNode, int origStartNode, boolean[] visitArray,
      						 LinkedList adjMatrixList, int adjMatrixSize, LinkedStack localPathStack,
                                 boolean firstPass, boolean[] resultsArray, FileWriter outFile) { // start findPaths()
      // Stopping case - Display the path if currentNode is equal to endNode and this is not the first pass through
      if ((currentNode == endNode) && (firstPass == false)) {
         resultsArray[currentNode] = true;
         localPathStack.display();
         localPathStack.printFile(outFile);
      }
      // Use this so that the start node is not repeated in the path
      if ((firstPass == true) && (origStartNode != endNode)) {
         visitArray[currentNode] = true;
      }
      /* Set currentNode visited if it this is not the first pass through,
         use this for cases where the start and end nodes are the same and a path might exist */
      if (firstPass == false) {
         visitArray[currentNode] = true;
      }
      // Set firstPass equal to false
      firstPass = false;
      Node currentRow = adjMatrixList.setCurrentRow(currentNode);
      // Iterate through the values in each row, continue if not visited yet and value is equal to 1
      for (int i = 0; i < adjMatrixSize; i++) {
         if ((! visitArray[i]) && (currentRow.col == i) && (currentRow.data) == 1) {
            // Push value to stack
            localPathStack.push(i);
            // Recursive call
            findPaths(i, endNode, origStartNode, visitArray, adjMatrixList, adjMatrixSize, localPathStack, firstPass, resultsArray, outFile);
            // Pop the last value once complete
            localPathStack.pop();
         }
         // Move to the next value in the row
         currentRow = currentRow.next;
      }
         // Set currentNode visited equal to false
         visitArray[currentNode] = false;
    } // end findPaths()
}
