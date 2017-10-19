/*

* * * * * * * * * * * * * Homework 4 * * * * * * * * * * * * * * * * * * * * * * *

* * * * * * * * * * * * * * * * * * * * * *
*  Written by: Adrian Santos (N00798593)  *
*  Date: 02/16/2017                       *
* * * * * * * * * * * * * * * * * * * * * *

Do knapsack 6.4 (recursively) on page 313. The input should be captured on a single line in a window,
such as for the example, but with the target  FIRST followed by the weights, on page 306 (there
will be no repeated values). Permit the user to enter all the numbers on one line:

**********THERE WILL BE NO DEDUCT IF YOU DECIDE TO USE THE CONSOLE FOR INPUT.
20 11 8 7 6 5
In this case the output would be:
8 7 5

Be sure and show all possible solutions! This is important! Only partial credit if this is not done.


It would be nice to have the output also in a window, but if you prefer the console, that is ok.
You may assume that the largest capacity (as well as any individual weight) is 100 and the largest
number of weights is 25.
You may also assume that the weights are sorted from largest to smallest.
The basic idea is to send a capacity and an array of weights to a recursive method and to either insert the
weight or not. In either case call the method again with a reduced capacity and a shorter array OR with the same
capacity and a shorter array. There should be a base case(s) for easy capacity and/or easy array.
IF you do it this way, you would probably return another array which would be the potential solution array which
of course would only be printed it it is truly a solution.

There are multiple ways to attack the knapsack but recursion MUST be used! A design issue is whether to send two arrays or whether
you want to send one array and return another. It is your choice! */

//Library Imports
import java.util.*;
import java.lang.*;
import java.io.*;

public class n00798593{

  //Statements
    public static void main(String[] args){
        //Instantiation
        SackArray sackArrList = new SackArray();
        SackArray myKnap2 = new SackArray();

        //Get a user input (Activate the keyboard)
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter numbers separated by spaces (The first number will be your capacity): ");
        String userInput = input.nextLine();

        //Get the weights
        String[] inputArrIntoStrings = userInput.trim().split("\\s+");
        //Prints the capacity
        int capacity = Integer.parseInt(inputArrIntoStrings[0]);

          //Prints the weights
          for(int i = 1; i < inputArrIntoStrings.length; i++){
            sackArrList.add(Integer.parseInt(inputArrIntoStrings[i]));
          }

        //Statements
        System.out.println("Your chosen capacity: " + capacity);
        System.out.println("The possible combinations are below: ");
        clearAndFillKnapSack(capacity, myKnap2, sackArrList);

        }//end main method

      public static void clearAndFillKnapSack(int capWt, SackArray sack, SackArray sackArrList){
        sack.clear();
        doKnapsack(capWt, 0, sackArrList, sack);
        }

      public static void doKnapsack(int capWt, int wt, SackArray sackArrList, SackArray myKnap2){

        if(wt == sackArrList.size()){

          if(capWt == 0){
            for (int j = 0; j < myKnap2.size(); j++){
              System.out.print(myKnap2.getWeight(j)+ " ");
            }//end for loop
            System.out.println("");
          }// end if(capWt == 0)
          return;
        }// end if(wt == capacity.size())

        myKnap2.add(sackArrList.getWeight(wt));
        doKnapsack(capWt - sackArrList.getWeight(wt), wt+1, sackArrList, myKnap2);
        myKnap2.remove(myKnap2.size()-1);
        doKnapsack(capWt, wt+1, sackArrList, myKnap2);
        }//end doKnapsack method
  }//end main class


class SackArray extends ArrayList{

    public int getWeight(int n){
        int w = -1;
        if (n < size()){
            Integer weight = (Integer)get(n);
            w = weight.intValue();
        }
        return w;
    }

  }//end sackArray
