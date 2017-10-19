/*

* * * * * * * * * * * * * Homework 4 * * * * * * * * * * * * * * * * * * * * * * *

* * * * * * * * * * * * * * * * * * * * * *
*  Written by: Adrian Santos (N00798593)  *
*  Date: 02/27/2017                       *
* * * * * * * * * * * * * * * * * * * * * *

Write a program to implement Huffman coding and decoding (see pages 415-421) in Java.

The program's input will be a command line file that can contain any char, but the
only ones of interest in this assignment are the capital letters A through G.
A COMMAND LINE FILE IS JUST THAT: A FILE NAME THAT IS ENTERED ON THE COMMAND
LINE (you will use jGrasp with build/run args) PRIOR TO RUNNING THE PROGRAM.
Read the file char by char.
IF the file happens to contain chars other than A through G IGNORE THEM in a-d.
Spaces should be ignored , for example , as should other chars!
You may assume that each of A through G are in the file.

I want you to display (all answers MUST  go to
the console) using a menu system (from the console OR from a window) :

  a.  The Huffman tree (see page 405 and 418 )itself (you may use the code from Tree.java if you wish.)
      You may assume that the tree will turn out to be no deeper than the tree shown on page 405.
      You may also assume that no individual char appears more than 9 times.
      Print either a char or a weight depending upon whether the node is a leaf or not.
      The Huffman tree can be constructed using a priority queue.
      Exercise OPTION a before other options.

  b.  the code table that displays the encoding for each of the chars A through G (see page
      416-417.) EXERCISE  OPTION b after OPTION a.

  c.  the binary encoding of the portion of the file
      that only contains the chars A through G (after each eight bits leave a space and print only 3 bytes per line.)
      The binary encoding may not necessarily display a "full" byte at the end (see page 417.) Exercise
      OPTION c after OPTION b

 d.  the A-G portion of the original file that is calculated by using the HUFFMAN TREE  from
     part a and the binary encoding from part c (don't just print the file back from the original; you
     must use the HUFFMAN TREE to do this.)  So you want to "read" the binary encoding from part c (using the
     HUFFMAN TREE from part a to reconstruct the portion of the file that consists of A through G.)  BASICALLY
     YOU ARE DEOCODING THE PORTION OF THE FILE THAT CONSISTS OF THE CHARS A-G.  AS YOU READ THE ENCODED FILE
     YOU ARE  MARCHING DOWN THE HUFFMAN TREE--WHEN YOU REACH A LEAF, YOU CAN FIGURE OUT WHICH CHAR IT IS AND
     YOU PRINT THAT CHAR. Exercise OPTION d after OPTION c.
*/

/*
  Concepts
  - PQueue (Ascending based on the frequency)
  - Nodes
  - Stacks
  - Run args
  - Switch case

  Info
  - Leaf (Wt+Char)
  - Ignore spaces & H-Z

  Steps
  1.) Take 2 characters with the lowest frequency
  2.) Make a 2 leaf node tree from step 1
*/

//Library Imports
import java.util.*;
import java.io.*;
import java.lang.*;

public class n00798593{

  //This is the Main Method
  public static void main(String[] args)throws FileNotFoundException, IOException{
    //File Reader - reads from the argument line
    File file = new File (args[0]);
    Scanner input = new Scanner(file);
    String text = input.next();//Reads through the test file and saves it as a string

    int[] counts = getCharacterFrequency(text); // Count frequency each character from the text

    //Object Instantiation
    Menu showMe = new Menu(); //Displays the menu
    Tree tree = getHuffmanTree(counts); // Create a Huffman tree

    showMe.printMenu();//Display the menu
    Scanner choice = new Scanner(System.in);//Activates the keyboard

    //Local Variables
    String[] huffCodes = getCode(tree.root); // Get codes
    String encodeIt = "";//Blank file until the for loop for encoding
    int a = 0;
    Tree.Node temp = tree.root;

    //Encodes the text file
    for (int i = 0,j = 0; i < text.length(); i++){
        switch(text.charAt(j)){
          case 'A':
            encodeIt += huffCodes[(int)'A'];
            j++;
            break;
          case 'B':
            encodeIt += huffCodes[(int)'B'];
            j++;
            break;
          case 'C':
            encodeIt += huffCodes[(int)'C'];
            j++;
            break;
          case 'D':
            encodeIt += huffCodes[(int)'D'];
            j++;
            break;
          case 'E':
            encodeIt += huffCodes[(int)'E'];
            j++;
            break;
          case 'F':
            encodeIt += huffCodes[(int)'F'];
            j++;
            break;
          case 'G':
            encodeIt += huffCodes[(int)'G'];
            j++;
            break;
         default:
        }
    }

    //Switch case
    while (true){
        System.out.print("Please choose your input: ");
        String myChoice = choice.nextLine();
      switch (myChoice){
          case "a":
                // System.out.println("Displays the huffman tree");
                tree.displayTree(tree);
                break;
          case "b":
                // System.out.println("Displays the code table");
                System.out.printf("%-15s|%-15s|%-15s\n",
                  "Character", "Frequency", "Code");
                  System.out.printf("%-15s|%-15s|%-15s\n",
                    "---------", "---------", "----");
                for (int i = 0; i < huffCodes.length; i++)
                  if (counts[i] != 0) // (char)i is not in text if counts[i] is 0
                    System.out.printf("%-15s|%-15d|%-15s\n", (char)i + "", counts[i], huffCodes[i]);
                break;
          case "c":
                // System.out.println("We're going to encode the strings now..");
                for (int j=0,k=8,l=0; j<encodeIt.length(); j+=8,k+=8,l++){
                  if(k>encodeIt.length()){
                    k=encodeIt.length();
                  }
                  if(l % 3 == 0 && l != 0){
                     System.out.println();
                  }
                  System.out.print(encodeIt.substring(j,k) + " ");
                }
                System.out.println();
                break;
          case "d":
                // System.out.println("Let's decode the code above...");
                decodeIt(tree.root, encodeIt, a, temp);
                break;
          case "x":
                System.out.println("Program is closing now..");
                System.exit(1);
          default:
                System.out.println("Error: Input does not follow the format. Please give another input");
                break;
        }//end switch case
    }//end while


  }

  // Gets the frequency of the characters
  public static int[] getCharacterFrequency(String text) {
    int[] counts = new int[72]; // Up to 72 ASCII characters
    for (int i = 0; i < text.length(); i++)
      counts[(int)text.charAt(i)]++; // Count the character in text
    return counts;
  }

  // Get a Huffman tree from the codes
  public static Tree getHuffmanTree(int[] counts) {
    // Create a heap to hold trees
    Heap<Tree> heap = new Heap<Tree>(); // Defined in Chapter 12 Listing 12.2
    for (int i = 0; i < counts.length; i++) {
      if (counts[i] > 0)
        heap.add(new Tree(counts[i], (char)i)); // A leaf node tree
    }

    while (heap.getSize() > 1) {
      Tree t1 = heap.remove(); // Remove the smallest weight tree
      Tree t2 = heap.remove(); // Remove the next smallest weight
      heap.add(new Tree(t1, t2)); // Combine two trees
    }

    return heap.remove(); // The final tree
  }

  // Get Huffman codes for the characters
  public static String[] getCode(Tree.Node root) {
    if (root == null) return null;
    String[] huffCodes = new String[72];
    assignCode(root, huffCodes);
    return huffCodes;
  }

  // Recursively get codes to the leaf node
  private static void assignCode(Tree.Node root, String[] huffCodes) {
    if (root.left != null) {
      root.left.code = root.code + "0";
      assignCode(root.left, huffCodes);

      root.right.code = root.code + "1";
      assignCode(root.right, huffCodes);
    }
    else {
      huffCodes[(int)root.element] = root.code;
    }
  }

  public static String[] getDecode(Tree.Node root) {
   if (root == null) return null;
   String[] decodeMe = new String[100];
//    decodeIt(root, deCode);
   return decodeMe;
  }

  //Decodes the string
  public static void decodeIt(Tree.Node root, String decodeMe, int i, Tree.Node temp){
      while(i<decodeMe.length()){
         if(root.code == "" || root.code != ""){
           if(decodeMe.charAt(i) == '0' && root.left != null){
              ++i;
              if(i>decodeMe.length()-1){
                System.out.print(root.left.element + "");
                break;
              }
              decodeIt(root.left, decodeMe, i, temp);
           }
           else if(decodeMe.charAt(i) == '1' && root.right != null){
              ++i;
              if(i>decodeMe.length()-1){
                decodeIt(root.right, decodeMe, i, temp);
                System.out.print(root.right.element + "");
                break;
              }
              decodeIt(root.right, decodeMe, i, temp);
           }else{
             System.out.print(root.element + "");
             decodeIt(temp, decodeMe, i, temp);
           }
         }
         return;
      }
    System.out.println();
    return;
  }


  // Define a Huffman coding tree
  public static class Tree implements Comparable<Tree> {
    Node root; // The root of the tree

    //Create a tree with two subtrees
    public Tree(Tree t1, Tree t2) {
      root = new Node();
      root.left = t1.root;
      root.right = t2.root;
      root.weight = t1.root.weight + t2.root.weight;
    }

    //Create a tree containing a leaf node
    public Tree(int weight, char element) {
      root = new Node(weight, element);
    }

    @Override // Compare trees based on their weights
    public int compareTo(Tree t) {
      if (root.weight < t.root.weight) // Purposely reverse the order
        return 1;
      else if (root.weight == t.root.weight)
        return 0;
      else
        return -1;
    }

    public class Node {
      char element; // Stores the character for a leaf node
      int weight; // weight of the subtree rooted at this node
      Node left; // Reference to the left subtree
      Node right; // Reference to the right subtree
      String code = ""; // The code of this node from the root

      //Create an empty node
      public Node() {
      }

      // Create a node with the specified weight and character
      public Node(int weight, char element) {
        this.weight = weight;
        this.element = element;
      }
    }

    // creates a tree based off of weights
    public void displayTree(Tree tree)
        {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println(
        "......................................................");
        while(isRowEmpty==false)
           {
           Stack localStack = new Stack();
           isRowEmpty = true;

           for(int j=0; j<nBlanks; j++)
              System.out.print(' ');

           while(globalStack.isEmpty()==false)
              {
              Node temp = (Node)globalStack.pop();
              if(temp != null)
                 {
                 if(temp.element !=  '\0')
                  System.out.print(temp.element);
                 if(temp.element == '\0')
                  System.out.print("#");
                 System.out.print(temp.weight);
                 localStack.push(temp.left);
                 localStack.push(temp.right);

                 if(temp.left != null || temp.right != null)
                    isRowEmpty = false;
                 }
              else
                 {
                 System.out.print("--");
                 localStack.push(null);
                 localStack.push(null);
                 }
              for(int j=0; j<nBlanks*2-2; j++)
                 System.out.print(' ');
              }  // end while globalStack not empty
           System.out.println();
           nBlanks /= 2;
           while(localStack.isEmpty()==false)
              globalStack.push( localStack.pop() );
           }  // end while isRowEmpty is false
        System.out.println(
        "......................................................");
        }  // end displayTree()
  }
}

//Uses Heaps for the program
class Heap<E extends Comparable<E>> {
  private java.util.ArrayList<E> list = new java.util.ArrayList<E>();

  //Create a default heap
  public Heap() {
  }

  // Create a heap from an array of objects
  public Heap(E[] objects) {
    for (int i = 0; i < objects.length; i++)
      add(objects[i]);
  }

  // Add a new object into the heap
  public void add(E newObject) {
    list.add(newObject); // Append to the heap
    int currentIndex = list.size() - 1; // The index of the last node

    while (currentIndex > 0) {
      int parentIndex = (currentIndex - 1) / 2;
      // Swap if the current object is greater than its parent
      if (list.get(currentIndex).compareTo(
          list.get(parentIndex)) > 0) {
        E temp = list.get(currentIndex);
        list.set(currentIndex, list.get(parentIndex));
        list.set(parentIndex, temp);
      }
      else
        break; // the tree is a heap now

      currentIndex = parentIndex;
    }
  }

  //Remove the root from the heap
  public E remove() {
    if (list.size() == 0) return null;

    E removedObject = list.get(0);
    list.set(0, list.get(list.size() - 1));
    list.remove(list.size() - 1);

    int currentIndex = 0;
    while (currentIndex < list.size()) {
      int leftChildIndex = 2 * currentIndex + 1;
      int rightChildIndex = 2 * currentIndex + 2;

      // Find the maximum between two children
      if (leftChildIndex >= list.size()) break; // The tree is a heap
      int maxIndex = leftChildIndex;
      if (rightChildIndex < list.size()) {
        if (list.get(maxIndex).compareTo(
            list.get(rightChildIndex)) < 0) {
          maxIndex = rightChildIndex;
        }
      }

      // Swap if the current node is less than the maximum
      if (list.get(currentIndex).compareTo(
          list.get(maxIndex)) < 0) {
        E temp = list.get(maxIndex);
        list.set(maxIndex, list.get(currentIndex));
        list.set(currentIndex, temp);
        currentIndex = maxIndex;
      }
      else
        break; // The tree is a heap
    }

    return removedObject;
  }

  //Get the number of nodes in the tree */
  public int getSize() {
    return list.size();
   }
}

//Class for printing the Menu
class Menu{
  public void printMenu(){
  //Print Choices
  System.out.println("\nThese are the choices for this program (Please enter only one character)");
  System.out.println("\ta - Displays the huffman tree ");
  System.out.println("\tb - Displays the code table of the input");
  System.out.println("\tc - Encodes the input into binary codes");
  System.out.println("\td - Decodes the binary codes into characters ");
  System.out.println("\tx - Exits the program");
  }
}
