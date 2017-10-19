/*This is a requirement of Homework 3

  * * * * * * * * * * * * * * * * * * * * * *
  *  Written by: Adrian Santos (N00798593)  *
  *  Date: 01/30/2017                       *
  * * * * * * * * * * * * * * * * * * * * * *


Elimination process (4-1-6-5-7-3 2)

Do problem 5.5 on pages 248-249 using Java (one file only--multiple classes ok).

The input will be a sequence of 3 ints entered from the keyboard
(separated only by one or more blanks), such as 7 1 3  as in problem 5.5.
This kind of input can be entered again and again until the user enters stop.

7 1 3 means that there are seven items:

1 2 3 4 5 6 7 (the numbering always starts at 1)
and that the holder  starts at 1
and that the passing is 3
so that means that the first one eliminated is 4

creating a new list
1 2 3 5 6 7
which now starts holding  at 5
and the next one eliminated is 1

creating a new list
2 3 5 6 7
which now starts holding at 2
and the next one eliminated is 6

creating a new list 2 3 5 7
which now starts holding at 7
and the next one eliminated is 5

creating a new list 2 3 7
which now starts holding at 7
and the next one eliminated is 7

creating a new list 2 3
which now starts holding at 2
and the next one eliminated is 3

creating the final list 2
IN ALL SITUATIONS THE FIRST NUMBER IN THE LIST IS 1.

When the user wants to stop the input, it should just be: stop
The user should be able to continue providing input after the problem is solved.
The output should be sent to the console (lower pane on jGrasp.)
Please note also that the first number could be an extremely large int.
It might be fun to try a circular linked list in one class , an iterator in another,
and then the application itself, but the choice is yours. Try to learn from the concepts of the chapter!
JUST PRINT THE FINAL LIST OF A SINGLE int.


*/
//Java Library Imports
import java.util.*;

//Main Class
public class n00798593{
    public static void main (String[] args){
      String s = "";
      int a,b,c;

      //Get the input from user
      Scanner input = new Scanner(System.in);

      //if user enters "stop" - program should stop
      while (!(s.equals("stop"))){
        System.out.print("Please enter 3 numbers seperated by spaces: ");
        s = input.nextLine();
        if(s.equals("stop"))
          break;

        String[] wordIntoNumber = s.trim().split("\\s+");
        int[] arr = new int[wordIntoNumber.length];

        for (int i = 0; i < wordIntoNumber.length; i++) {
          arr[i] = Integer.parseInt(wordIntoNumber[i]);
        }//end while loop


        a = arr[0];
        b = arr[1];
        c = arr[2];

        //Apply the Josephs class - name it as a circle for easier concept
        Josephus circle = new Josephus();
        circle.josephus(a,b,c);

        arr = null;
        wordIntoNumber = null;

      }//End While Loop
    }//End Main Method class
}//End n00798593 class

//Implements the Josephus Algorithm
class Josephus{

 public void josephus(int numberOfPeople, int startNumber, int skipNumber){

    //Implements the circularLL class
    circularLinkedList josephusCircle = new circularLinkedList();

    for(int i = 1; i <= numberOfPeople; i++){
      josephusCircle.insert(i);
    }

    //Implements the circularL class
    circularList temporaryPoint = josephusCircle.currentPoint;

    //Below lines gets the start input
    while(temporaryPoint.data!=startNumber){
      temporaryPoint = temporaryPoint.next;
    }

    josephusCircle.currentPoint = temporaryPoint;

    //This while loop traverses the circle
    int index = 0;
    while(temporaryPoint.next != temporaryPoint && temporaryPoint.prev != temporaryPoint){
      index = 0;

      while(index < skipNumber-1){
        temporaryPoint = temporaryPoint.next;
        index++;
      }

      josephusCircle.currentPoint = temporaryPoint.next;
      int digit = josephusCircle.delete();

      //* * * * *  Test the array arrangement order * * * * * */
      //System.out.println("-----> Person: " + digit + " eliminated);
      josephusCircle.currentPoint = temporaryPoint;
      temporaryPoint = temporaryPoint.next;
    }
    //* *  * * * * * * Display the eliminated data * * * * */
    //System.out.println("-----> Person: " + temporaryPoint.data + " was left");
    System.out.println(temporaryPoint.data);
  }


}

//This implements the list
class circularList{
  //Variables
  int data;
  circularList next;
  circularList prev;

  //Methods
  //Implement the circular linked list
  public circularList(int d){
    data = d;
    next = null;
    prev = null;

  }

  //return the data
  public int getData(){
    return data;

  }
}//end circularL class

//Impelements a linked-list
class circularLinkedList{

  //Variables
  circularList currentPoint;

  //Constructors
  public circularLinkedList(){
    currentPoint = null;
    }

  //Inserting
  public void insert(int dd){

    circularList newLink = new circularList(dd);

    if(currentPoint==null){
      newLink.next = newLink;
      newLink.prev = newLink;
    }//end if null

    else if (currentPoint.next==null && currentPoint.prev==null && (!(currentPoint==null))){
      newLink.next = currentPoint;
      newLink.prev = currentPoint;
      currentPoint.prev = newLink;
      currentPoint.next = newLink;
    }//end else if

    else if(currentPoint!=null){
      newLink.next = currentPoint.next;
      newLink.prev = currentPoint;
      currentPoint.next.prev = newLink;
      currentPoint.next = newLink;
    } //end else if

    currentPoint = newLink;
  }//end insert constuctor

  //Delete
  public int delete(){
    circularList temporaryCurrent = currentPoint;
    currentPoint.next.prev = currentPoint.prev;
    currentPoint.prev.next = currentPoint.next;
    currentPoint = temporaryCurrent.prev;
    return temporaryCurrent.data;
  }

}//end circularLL Class
