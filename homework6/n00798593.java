/*

* * * * * * * * * * * * * Homework 6 * * * * * * * * * * * * * * * * * * * * * * *

* * * * * * * * * * * * * * * * * * * * * *
*  Written by: Adrian Santos (N00798593)  *
*  Date: 03/26/2017                       *
* * * * * * * * * * * * * * * * * * * * * *

For this program you are going to read three files of strings (using only lowercase alpha chars, each with no blanks, and each
separated by a new line--all three files will be on the command line--build/run args). The data will be such as:

zyxabc
ace
bobbysue
maybeuptofortychars
etc

The files will have been constructed using Notepad on a Windows machine.

Each string should be thought of as a polynomial, such as

'z'*26**5 + 'y'*26**4 + 'x'* 26**3 + 'a'*26**2 + 'b'*26**1 +'c'*26**0
'a'*26**2 + 'c'*26**1 + 'e'*26**0
etc

where 'z' is the ASCII int associated with it (similarly) for all alpha chars.
For example, 'a' ==97.

So each string is essentially treated as a very large int which causes overflow.
The strings might have as many as 40 chars so that the leading power would be 26**39.


Task 1: Count the number of strings in the first file, say n.
Task 2: Find the first prime number larger than 2n, say p (a hint for finding p is in the book).
Task 3: Construct an array A and another array B of size p (indexed from 0 to p-1) to hold strings.
Task 4: Insert each of the strings in the first file into A using linear probing and into B using
        quadratic probing. In each case use Horner's polynomial.
        Print the contents of A (index, string, and probe length for each insertion, all three on a single  line) followed
        by the contents of B (index, string, and probe length) . Only do the printing for non-empty cells and
        print the cells with the smallest index used first.
                                 A
                      index   string   probe length for insertion
                       3        xyz        4
                       etc
      ave probe length:                        x.xx
                                 B
                      index   string   probe length for insertion
                       3        xyz        4
                       etc
                 ave:                     y.yy


It is required that you hash (Horner's polynomial) using the technique we discussed
in class so as to avoid overflow at each stage. See:
http://math.fullerton.edu/mathews/n2003/HornerMod.html and/or pages 564-565.


Task 5: Search for each of the strings in the second  file from  A using hashing and linear probing,
        and from B using hashing and quadratic probing. Print the following tables for A and B
        (just for the searches) :

               String   Success Failure Probe length for success  Probe length for failure
                ...      yes                     ...
                ...              yes                                          ...

average probe length:                                  x.xx                         y.yy


Task 6: Delete each of the strings in the third file from  A (if present)  using hashing and linear probing,
        and from B (if present) using hashing and quadratic probing. Print the following tables for A and B
        (just for the deletions) :

               String   Succcess Failure Probe length for success  Probe length for failure
                ...      yes                             ...
                ...              yes                                                ...

 average probe length:                                  x.xx                         y.yy

*/
/*
For this program you are going to read three files of strings (using only lowercase alpha chars, each with no blanks, and each
separated by a new line--all three files will be on the command line--build/run args). The data will be such as:

zyxabc
ace
bobbysue
maybeuptofortychars
etc

The files will have been constructed using Notepad on a Windows machine.

Each string should be thought of as a polynomial, such as

'z'*26**5 + 'y'*26**4 + 'x'* 26**3 + 'a'*26**2 + 'b'*26**1 +'c'*26**0
'a'*26**2 + 'c'*26**1 + 'e'*26**0
etc

where 'z' is the ASCII int associated with it (similarly) for all alpha chars.
For example, 'a' ==97.

So each string is essentially treated as a very large int which causes overflow.
The strings might have as many as 40 chars so that the leading power would be 26**39.


Task 1: Count the number of strings in the first file, say n.
Task 2: Find the first prime number larger than 2n, say p (a hint for finding p is in the book).
Task 3: Construct an array A and another array B of size p (indexed from 0 to p-1) to hold strings.
Task 4: Insert each of the strings in the first file into A using linear probing and into B using
        quadratic probing. In each case use Horner's polynomial.
        Print the contents of A (index, string, and probe length for each insertion, all three on a single  line) followed
        by the contents of B (index, string, and probe length) . Only do the printing for non-empty cells and
        print the cells with the smallest index used first.
                                 A
                      index   string   probe length for insertion
                       3        xyz        4
                       etc
      ave probe length:                        x.xx
                                 B
                      index   string   probe length for insertion
                       3        xyz        4
                       etc
                 ave:                     y.yy


It is required that you hash (Horner's polynomial) using the technique we discussed
in class so as to avoid overflow at each stage. See:
http://math.fullerton.edu/mathews/n2003/HornerMod.html and/or pages 564-565.


Task 5: Search for each of the strings in the second  file from  A using hashing and linear probing,
        and from B using hashing and quadratic probing. Print the following tables for A and B
        (just for the searches) :

               String   Success Failure Probe length for success  Probe length for failure
                ...      yes                     ...
                ...              yes                                          ...

average probe length:                                  x.xx                         y.yy


Task 6: Delete each of the strings in the third file from  A (if present)  using hashing and linear probing,
        and from B (if present) using hashing and quadratic probing. Print the following tables for A and B
        (just for the deletions) :

               String   Succcess Failure Probe length for success  Probe length for failure
                ...      yes                             ...
                ...              yes                                                ...

 average probe length:                                  x.xx                         y.yy
*/

//Library Imports
import java.util.*;
import java.io.*;
import java.lang.*;

public class n00798593{
        public static void main(String [] args) throws IOException, FileNotFoundException {

                //File Readers
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(args[0]))));
                BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(new File(args[0]))));
                BufferedReader br3 = new BufferedReader(new InputStreamReader(new FileInputStream(new File(args[1]))));
                BufferedReader br4 = new BufferedReader(new InputStreamReader(new FileInputStream(new File(args[2]))));

                //String containers
                String text = new String();
                String text2 = new String();
                String text3 = new String();
                String text4 = new String();
                String divider = "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";

                //String counter for task 1
                int stringCounter = 0;

                //Reads the file and applies a counter on number of strings
                while((text = br.readLine()) != null){
                        if(!(text.equals(""))){
                                stringCounter++;
                                // System.out.println(text);//Debug
                        }

                }
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                //Task 1: Count the number of strings in the first file, say n.
                System.out.println("The total number of lines is: " + stringCounter);
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                //Task 2: Find the first prime number larger than 2n, say p (a hint for finding p is in the book).
                int n = ((2*stringCounter)+1);
                int size = countPrime(n);
                System.out.println("The first prime number after a number larger than 2n is: " + size);

                //Vars Initialization
                int probeLength = 0; int a = 0; int b = 0; int c = 0;
                int d = 0; int e = 0; int f = 0; int z = 0;
                int y = 0; int x = 0; int w = 0;

                //StrArray
                String A[] = new String[size];
                String B[] = new String[size];
                String A2[] = new String[size];
                String B2[] = new String[size];

                //Array List Initialization
                  //Task 4
                  ArrayList<Integer> totalPLA = new ArrayList<>();
                  ArrayList<Integer> totalPLB = new ArrayList<>();

                  //Task 5
                  ArrayList<String> searchArrA = new ArrayList<>();
                  ArrayList<String> searchArrB = new ArrayList<>();
                  ArrayList<Integer> succSearchPLA = new ArrayList<>();
                  ArrayList<Integer> succSearchPLB = new ArrayList<>();
                  ArrayList<Integer> failSearchPLA = new ArrayList<>();
                  ArrayList<Integer> failSearchPLB = new ArrayList<>();

                  //Task 6
                  ArrayList<String> deleteArrA = new ArrayList<>();
                  ArrayList<String> deleteArrB = new ArrayList<>();
                  ArrayList<Integer> succDelPLA = new ArrayList<>();
                  ArrayList<Integer> succDelPLB = new ArrayList<>();
                  ArrayList<Integer> failDelPLA = new ArrayList<>();
                  ArrayList<Integer> failDelPLB = new ArrayList<>();


                //Read File 1 part 2
                while((text2 = br2.readLine()) != null){
                        if(!(text2.equals(""))){
                                linearProb(text2,hashFunc3(text2, size),A,size,probeLength, totalPLA);
                                quadProb(text2,hashFunc3(text2, size),B,size,probeLength, totalPLB);
                                linearProb2(text2,hashFunc3(text2, size),A2,size,probeLength);
                                quadProb2(text2,hashFunc3(text2, size),B2,size,probeLength);
                        }
                }

                //Read File 2
                while((text3 = br3.readLine()) != null){
                        if(!(text3.equals(""))){
                            linearSearch(text3, hashFunc3(text3, size) ,A2, size, probeLength, searchArrA, hashFunc3(text3, size), succSearchPLA, failSearchPLA);
                            quadSearch(text3, hashFunc3(text3, size) ,B2, size, probeLength, searchArrB, hashFunc3(text3, size), succDelPLB, failDelPLB);
                        }
                }

                //Read File 3
                while((text4 = br4.readLine()) != null){
                        if(!(text4.equals(""))){
                            linearDelete(text4, hashFunc3(text4, size) ,A2, size, probeLength, deleteArrA, hashFunc3(text4, size), succDelPLA, failDelPLA);
                            quadDelete(text4, hashFunc3(text4, size) ,B2, size, probeLength, deleteArrB, hashFunc3(text4, size), succSearchPLB, failSearchPLB);
                        }
                }

                //For Loops to Process Probe Lengths
                for(int i = 0; i <= totalPLA.size()-1; i++){
                        a += totalPLA.get(i);
                }

                for(int i = 0; i <= totalPLB.size()-1; i++){
                        b += totalPLB.get(i);
                }

                for(int i = 0; i <= succSearchPLA.size()-1; i++){
                        c += succSearchPLA.get(i);
                }

                for(int i = 0; i <= failSearchPLA.size()-1; i++){
                        d += failSearchPLA.get(i);
                }

                for(int i = 0; i <= succSearchPLB.size()-1; i++){
                        e += succSearchPLB.get(i);
                }

                for(int i = 0; i <= failSearchPLB.size()-1; i++){
                        f += failSearchPLB.get(i);
                }

                for(int i = 0; i <= succDelPLA.size()-1; i++){
                        z += succDelPLA.get(i);
                }

                for(int i = 0; i <= failDelPLA.size()-1; i++){
                        y += failDelPLA.get(i);
                }

                for(int i = 0; i <= succDelPLB.size()-1; i++){
                        x += succDelPLB.get(i);
                }

                for(int i = 0; i <= failDelPLB.size()-1; i++){
                        w += failDelPLB.get(i);
                }

                //Activate if debugging is needed
                // System.out.println("Here is the total prob length for A: " + a);
                // System.out.println("Here is the total prob length for B: " + b);
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                /*
                Task 4: Insert each of the strings in the first file into A using linear probing and into B using
                        quadratic probing. In each case use Horner's polynomial.
                        Print the contents of A (index, string, and probe length for each insertion, all three on a single  line) followed
                        by the contents of B (index, string, and probe length) . Only do the printing for non-empty cells and
                        print the cells with the smallest index used first.
                */
                //Array A
                System.out.println(divider);
                System.out.printf("%80s", ("Array A\n"));
                System.out.println(divider);
                System.out.println("Index\t\t\t\t\t\t\t|String\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|Probe Length for Insertion");
                for(int i = 0; i <= A.length-1; i++){
                        if(!(A[i] == null)){
                                System.out.println(A[i]);
                        }
                }
                float finalAvgPLA = (float)a / stringCounter;
                System.out.printf("\n%-20s %80.3f","Average Probe Length: ", finalAvgPLA);
                System.out.println("\n");//Debug blank line

                //Array B
                System.out.println(divider);
                System.out.printf("%80s", ("Array B\n"));
                System.out.println(divider);
                System.out.println("Index\t\t\t\t\t\t\t|String\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|Probe Length for Insertion");
                for(int i = 0; i <= B.length-1; i++){
                        if(!(B[i] == null)){
                                System.out.println(B[i]);
                        }
                }
                float finalAvgPLB = (float)b / stringCounter;
                System.out.printf("\n%-20s %80.3f","Average Probe Length: ", finalAvgPLB);
                System.out.println("\n");//Debug blank line
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                /*
                Task 5: Search for each of the strings in the second  file from  A using hashing and linear probing,
                        and from B using hashing and quadratic probing. Print the following tables for A and B
                        (just for the searches) :
                */
                System.out.println(divider);
                System.out.printf("%80s", ("Searching Array A\n"));
                System.out.println(divider);
                System.out.println("String\t\t\t\t\t\t\t\t\t\t\t\t\t\t|Success\t\t\t\t|Failure\t\t\t\t|Probe Length for Success\t\t\t\t|Probe Length for Failure");
                System.out.println("------\t\t\t\t\t\t\t\t\t\t\t\t\t\t|-------\t\t\t\t|-------\t\t\t\t|------------------------\t\t\t\t|------------------------");
                //----------------------------
                for (String s : searchArrA) {
                    System.out.println(s);
                }
                //----------------------------
                float finalAvgPLC = (float)c / stringCounter;
                float finalAvgPLD = (float)d / stringCounter;
                System.out.printf("\n%-20s %70.3f %30.3f","Average Probe Length: ", finalAvgPLC, finalAvgPLD);

                //----------------------------
                //Array B
                System.out.println("\n"); //Debug Blank Line
                System.out.println(divider);
                System.out.printf("%80s", ("Searching Array B\n"));
                System.out.println(divider);
                System.out.println("String\t\t\t\t\t\t\t\t\t\t\t\t\t\t|Success\t\t\t\t|Failure\t\t\t\t|Probe Length for Success\t\t\t\t|Probe Length for Failure");
                System.out.println("------\t\t\t\t\t\t\t\t\t\t\t\t\t\t|-------\t\t\t\t|-------\t\t\t\t|------------------------\t\t\t\t|------------------------");

                //----------------------------
                for (String s : searchArrB) {
                    System.out.println(s);
                }
                //----------------------------
                float finalAvgProbLenE = (float)e / stringCounter;
                float finalAvgProbLenF = (float)f / stringCounter;
                System.out.printf("\n%-20s %70.3f %30.3f","Average Probe Length: ", finalAvgProbLenE, finalAvgProbLenF);
                System.out.println("\n"); //Debug Blank Line
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                /*
                Task 6: Delete each of the strings in the third file from  A (if present)  using hashing and linear probing,
                        and from B (if present) using hashing and quadratic probing. Print the following tables for A and B
                        (just for the deletions) :
                */
                System.out.println(divider);
                System.out.printf("%80s", ("Deleting Array A\n"));
                System.out.println(divider);
                System.out.println("String\t\t\t\t\t\t\t\t\t\t\t\t\t\t|Success\t\t\t\t|Failure\t\t\t\t|Probe Length for Success\t\t\t\t|Probe Length for Failure");
                System.out.println("------\t\t\t\t\t\t\t\t\t\t\t\t\t\t|-------\t\t\t\t|-------\t\t\t\t|------------------------\t\t\t\t|------------------------");

                //----------------------------
                for (String s : deleteArrA) {
                    System.out.println(s);
                }
                //----------------------------
                float finalAvgPLZ = (float)z / stringCounter;
                float finalAvgPLY = (float)y / stringCounter;
                System.out.printf("\n%-20s %70.3f %30.3f","Average Probe Length: ", finalAvgPLZ, finalAvgPLY);
                System.out.println("\n"); //Debug Blank Line
                //----------------------------
                //Array B
                System.out.println(divider);
                System.out.printf("%80s", ("Deleting Array B\n"));
                System.out.println(divider);
                System.out.println("String\t\t\t\t\t\t\t\t\t\t\t\t\t\t|Success\t\t\t\t|Failure\t\t\t\t|Probe Length for Success\t\t\t\t|Probe Length for Failure");
                System.out.println("------\t\t\t\t\t\t\t\t\t\t\t\t\t\t|-------\t\t\t\t|-------\t\t\t\t|------------------------\t\t\t\t|------------------------");

                for (String s : deleteArrB) {
                    System.out.println(s);
                }

                float finalAvgPLX = (float)x / stringCounter;
                float finalAvgPLW = (float)w / stringCounter;
                System.out.printf("\n%-20s %70.3f %30.3f","Average Probe Length: ", finalAvgPLX, finalAvgPLW);
        }
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //Methods
        public boolean equals(Object o){
                if(this == o){
                        return true;
                }
                if(o == null){
                        return false;
                }
                return false;
        }

        public static int countPrime(int n){
                for(int i = 2; i <= n/2; i++){
                        if(n % i == 0){
                                countPrime(++n);
                        }
                }
                return n;
        }

        public static int hashFunc3(String key, int arraySize) {
                int hashVal = 0;

                for(int j = 0; j < key.length(); j++){
                        int letter  = key.charAt(j) - 96;
                        hashVal = (hashVal * 26 + letter) % arraySize;
                }
                return hashVal;
        }
        public static void linearProb(String key, int index, String[] arr, int arrSize, int probLen, ArrayList<Integer> probTotal) {

                if(arr[index] == null) {
                        probLen++;
                         arr[index] = index + "\t\t\t\t\t\t\t\t|" + key + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|" + probLen;
                        probTotal.add(probLen);
                }else{
                        probLen++;
                        index %= arrSize;
                        linearProb(key, ++index, arr, arrSize, probLen, probTotal);
                }
        }

        public static void quadProb(String key, int index, String[] arr, int arrSize, int probLen, ArrayList<Integer> probTotal) {
                if(arr[index] == null) {
                        probLen++;
                        arr[index] = index + "\t\t\t\t\t\t\t\t|" + key + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|" + probLen;
                        probTotal.add(probLen);
                }else{
                        probLen++;
                        index += (probLen * probLen);
                        index %= arrSize;
                        quadProb(key, index, arr, arrSize, probLen, probTotal);
                }
        }

        public static void linearProb2(String key, int index, String[] arr, int arrSize, int probLen) {
                if(arr[index] == null) {
                        probLen++;
                        arr[index] = key;
                }else{
                        probLen++;
                        index %= arrSize;
                        linearProb2(key, ++index, arr, arrSize, probLen);
                }
        }

        public static void quadProb2(String key, int index, String[] arr, int arrSize, int probLen) {
                if(arr[index] == null) {
                        probLen++;
                        arr[index] = key;
                }else{
                        probLen++;
                        index += (probLen * probLen);
                        index %= arrSize;
                        quadProb2(key, index, arr, arrSize, probLen);
                }
        }

        public static void linearSearch(String key, int index ,String[] strArr, int arrSize, int probLen, ArrayList<String> searchArr, int temp, ArrayList<Integer> probLenSucc, ArrayList<Integer> probLenFail){
          index %= arrSize;
                if(strArr[index] != null){
                    if(strArr[index].equals(key)){
                        probLen++;
                        // System.out.println("String\t\t\t\tSuccess\t\t\t\tFailure\t\t\t\tProbe Length for Success\t\t\t\tProbe Length for Failure");

                        searchArr.add(key + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t|" + "YES" + "\t\t\t\t\t\t|" + "..." + "\t\t\t\t\t\t|" +probLen + "\t\t\t\t\t\t|" + "...");
                        probLenSucc.add(probLen);
                    }else{
                        probLen++;
                        index %= arrSize;
                        if(index != temp-1){
                            linearSearch(key, ++index, strArr, arrSize, probLen, searchArr, temp, probLenSucc, probLenFail);
                        }else{
                            searchArr.add(key + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t|" + "..." + "\t\t\t\t|"+ "YES" + "\t\t\t\t|" +"..." + "\t\t\t\t|" +probLen);
                            probLenFail.add(probLen);
                        }

                    }
                }else{
                            probLen++;
                            searchArr.add(key + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t|" + "..." + "\t\t\t\t|"+ "YES" + "\t\t\t\t|" +"..." + "\t\t\t\t|" +probLen);
                            probLenFail.add(probLen);
                }
        }

        public static void quadSearch(String key, int index ,String[] strArr, int arrSize, int probLen, ArrayList<String> searchArr, int temp, ArrayList<Integer> probLenSucc, ArrayList<Integer> probLenFail){
          index %= arrSize;
                if(strArr[index] != null){
                    if(strArr[index].equals(key)){
                        probLen++;
                        searchArr.add(key + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t|" + "YES" + "\t\t\t\t|" + "..." + "\t\t\t\t|" +probLen + "\t\t\t\t|" + "...");
                        probLenSucc.add(probLen);
                    }else{
                        probLen++;
                        index += (probLen * probLen);
                        index %= arrSize;
                        if(index != temp-1){
                            quadSearch(key, index, strArr, arrSize, probLen, searchArr, temp, probLenSucc, probLenFail);
                        }else{
                            searchArr.add(key + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t|" + "..." + "\t\t\t\t|"+ "YES" + "\t\t\t\t|" +"..." + "\t\t\t\t|" +probLen);
                            probLenFail.add(probLen);
                        }

                    }
                }else{
                            probLen++;
                            searchArr.add(key + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t|" + "..." + "\t\t\t\t|"+ "YES" + "\t\t\t\t|" +"..." + "\t\t\t\t|" +probLen);
                            probLenFail.add(probLen);
                }
        }

        public static void linearDelete(String key, int index ,String[] strArr, int arrSize, int probLen, ArrayList<String> searchArr, int temp, ArrayList<Integer> probLenSucc, ArrayList<Integer> probLenFail){
          index %= arrSize;
                if(strArr[index] != null){
                    if(strArr[index].equals(key)){
                        probLen++;
                        searchArr.add(key + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t|" + "YES" + "\t\t\t\t|" + "..." + "\t\t\t\t|" +probLen + "\t\t\t\t|" + "...");
                        probLenSucc.add(probLen);
                    }else{
                        probLen++;
                        index %= arrSize;
                        if(index != temp-1){
                            linearSearch(key, ++index, strArr, arrSize, probLen, searchArr, temp, probLenSucc, probLenFail);
                        }else{
                            searchArr.add(key + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t|" + "..." + "\t\t\t\t|"+ "YES" + "\t\t\t\t|" +"..." + "\t\t\t\t|" +probLen);
                            probLenFail.add(probLen);
                        }

                    }
                }else{
                            probLen++;
                            index %= arrSize;
                            searchArr.add(key + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t|" + "..." + "\t\t\t\t|"+ "YES" + "\t\t\t\t|" +"..." + "\t\t\t\t|" +probLen);
                            probLenFail.add(probLen);
                }
        }

        public static void quadDelete(String key, int index ,String[] strArr, int arrSize, int probLen, ArrayList<String> searchArr, int temp, ArrayList<Integer> probLenSucc, ArrayList<Integer> probLenFail){
          index %= arrSize;
                if(strArr[index] != null){
                    if(strArr[index].equals(key)){
                        probLen++;
                        searchArr.add(key + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t|" + "YES" + "\t\t\t\t|" + "..." + "\t\t\t\t|" +probLen + "\t\t\t\t|" + "...");
                        probLenSucc.add(probLen);
                    }else{
                        probLen++;
                        index += (probLen * probLen);
                        index %= arrSize;
                        if(index != temp-1){
                            linearSearch(key, ++index, strArr, arrSize, probLen, searchArr, temp, probLenSucc, probLenFail);
                        }else{
                            searchArr.add(key + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t|" + "..." + "\t\t\t\t|"+ "YES" + "\t\t\t\t|" +"..." + "\t\t\t\t|" +probLen);
                            probLenFail.add(probLen);
                        }

                    }
                }else{
                            probLen++;
                            index %= arrSize;
                            searchArr.add(key + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t|" + "..." + "\t\t\t\t|"+ "YES" + "\t|\t" +"..." + "\t|\t" +probLen);
                            probLenFail.add(probLen);
                }
        }

}
