/* This is for homework 1 in COP3530, which displays the use of arrays in data structures

  Written by: Adrian Santos (N00798593)
        Date: 01/14/2017



        Assignment #1        NO LATE ASSIGNMENTS ACCEPTED PLEASE.
        DUE 11 pm

        Generally I will run your Java program on jGrasp with Windows.
        You must use SSH to ftp your code
        to Osprey so that you can use turnin (see #5 below).


        Any Java program that you turn in will always be in a single file named unfid.java where unfid
        is YOUR unf id.  So , I would name my file n00009873.java . This implies that the name of the class
        containing main
        must be n00009873.  So in this program you must change HighArray.java to unfid.java and ALSO
        rename the class HighArrayApp to unfid (always pay attention to capitalization).

        So , in general, my class containing main will be named n00009873
        and the file containing all my classes will be named n00009873.java


        1.	log on to osprey using ssh.
        2.	change your password (if you like!)
                $ passwd

        3.	Construct program 2.1 on page 76 of the book but you are going to write a getMin()
                method also.
        	Make the last four lines of code:

        	y=arr.getMax(); /*this should work with any data set not just the data he has.
                              I will modify the data in your code to test it with several
                              data sets. You should declare any variables like y and z that you need.
                              The data might be all postive, all negative, or a mixture. But
                              it will alwasy consists of ints, including possibly the int 0

        	System.out.println(y);
                z=arr.getMin();
                System.out.println(z);

        	All of the source code should be in a single file , named unfid.java
        	So my file would be named n00009873.java
        	I need you to name your file unfid.java because
        	turnin (see #5 below) renames your turned in file to unfid, where unfid is YOUR
        	unf id and I
        	want an easy way to simply  rename your turned in file back to unfid.java (!!!).
        	I know this is convoluted, but I don't want you to use disks to turn in assignments.
        	I want you to use turnin on Osprey.

        4.      Compile the program using jGrasp to test it and then transfer that file to osprey.unf.edu
                using ssh.

        5.	turnin an electronic version of "unfid.java" using
        	$ turnin unfid.java  kmartin.cop3530.a1

        	When I get it , the system renames it unfid (YOUR unfid).

        6.	check to see if the size of the file you saved is the same as the size
                of the file turned in

        	$ turnin -c kmartin.cop3530.a1  (this is a-one, not a-ell)
                (tells you how many bytes you have turned in--check against how
                many you have saved)
            	$ ls -l
                (list with long option)

        Generally, I will compile your file  and then run it.  You should
        do the same on Osprey or using jGrasp on Windows to make sure you have done everything properly.

        NEVER turnin ANY BINARY CODE IN THIS CLASS.  NO LATE ASSIGNMENTS ACCEPTED..GET IT DONE
        EARLY!
        DO NOT USE shar for this assignment. Just turnin a single .java file.
*/

class HighArray{
  private int[] a; //ref to array
  private int nElems; //number of data items

  //---------------------------------------------------------
  public HighArray (int max){  //constructor
    a = new int[max]; //create the array
    nElems = 0; //no items yet
  }
  //---------------------------------------------------------
  public boolean find (int searchKey){ //find specified value
    int j; //for each element
    for (j=0; j<nElems; j++) //for each element
      if (a[j] == searchKey)  //found item?
        break;  //exit loop
      if(j==nElems)
        return false; //yes, can find it
      else
        return true; //no, found it
  } //end find
  //---------------------------------------------------------
  public void insert (int value) { //put element into array{
    a[nElems] = value; //insert it
    nElems++; //increment size
  }
  //---------------------------------------------------------
  public boolean delete (int value){//delete
    int j;
    for(j=0; j<nElems; j++) //look for it
      if(value == a[j])
        break;
      if(j==nElems) //can't find it
        return false;
    else{
      for(int k=j; k<nElems; k++) //move higher ones down
        a[k] = a[k+1];
        nElems--; //decrement size
        return true;
    }
  } //end delete
  //---------------------------------------------------------
  public void display(){  //displays array contents
    for (int j=0; j<nElems; j++)  //for each element
      System.out.print(a[j] + " "); //display it
    System.out.println("");
  }
  //---------------------------------------------------------
  public int getMax(){//Gets the maximum number
   int max = a[0];
    for (int j=0; j < nElems; j++){//for each element
      if(a[j] > max){ //if array index is larger than reference 0
        max = a[j]; //the max will be the current
      } //then loop if condition is still met
    }
    return max;//if found and done searching, return the maximum value
  }
  //---------------------------------------------------------
  public int getMin(){//Gets the minimum number
    int min = a[0];
      for (int j=0; j < nElems; j++){//for each element
        if(a[j] < min){//if array index is larger than reference 0
          min = a[j];//the min will be the current
        }//then loop if condition is still met
      }//end for loop
      return min; //if found and done searching, return the minimum value
  }



} // end class HighArray
/////////////////////////////////////////////////////////////////////
class n00798593{
  public static void main (String[] args){
    int maxSize = 100; //array size
    HighArray arr; //reference to array
    arr = new HighArray(maxSize); //create the array

    /* * * * * * * * * Test Value 1 * * * * * * * * * */

    arr.insert(77); //insert 10 items
    arr.insert(99);
    arr.insert(44);
    arr.insert(55);
    arr.insert(22);
    arr.insert(88);
    arr.insert(11);
    arr.insert(00);
    arr.insert(66);
    arr.insert(33);


    arr.display(); //display items

    int searchKey = 35; //search for items
    if (arr.find(searchKey))
      System.out.println("Found " + searchKey);
    else
      System.out.println("Can't find  " + searchKey);

    arr.delete(00); //delete 3 items
    arr.delete(55);
    arr.delete(99);

    /* -  - - - - - - - - Break for Test Values - - - - - - - -*/
    /* * * * * * * * * * * * * *  Test Value 2 * * * * * * * * * * */
    /*
    //With Negative Values
    //Repeating values are not running properly
    arr.insert(25); //insert 10 items
    arr.insert(86);
    arr.insert(91);
    arr.insert(-17);
    arr.insert(9);
    arr.insert(62);
    arr.insert(-91);
    arr.insert(71);
    arr.insert(90);
    arr.insert(31);


    arr.display(); //display items

    int searchKey = 35; //search for items
    if (arr.find(searchKey))
      System.out.println("Found " + searchKey);
    else
      System.out.println("Can't find  " + searchKey);

    arr.delete(91); //delete 3 items
    arr.delete(17);
    arr.delete(71);
    */


    /* * * * * * * * * * * * * *  Test Value 2 * * * * * * * * * * */
    /*
    arr.insert(-1); //insert 10 items
    arr.insert(-89);
    arr.insert(-31);
    arr.insert(-31);
    arr.insert(-31);
    arr.insert(-17);
    arr.insert(0);
    arr.insert(-3);
    arr.insert(-4);
    arr.insert(-5);


    arr.display(); //display items

    int searchKey = 31; //search for items
    if (arr.find(searchKey))
      System.out.println("Found " + searchKey);
    else
      System.out.println("Can't find  " + searchKey);

    arr.delete(31); //delete 3 items
    arr.delete(2);
    arr.delete(3);

    */

    arr.display(); //display items again

    int y = 0;
    int z = 0;

    y = arr.getMax();//get maximum
    System.out.println("The maximum value is: " + y);
    z = arr.getMin();//get minimum
    System.out.println("The minimum value is: " + z);

  } //end main ()
}//end class HighArray App
//////////////////////////////////////////////////////////
