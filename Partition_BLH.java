//***********************************************************************
// Ben Heffron
// 3/8/2022
// Partition_BLH
// Period 6
// This program compares 5 algorithms and attempts to equally partition
// the inputted numbers.
//************************************************************************
import java.util.Arrays;
import java.io.*;
import java.util.Scanner;
public class Partition_BLH
{
   public static void main(String[] args)
   {
      Scanner scan = new Scanner(System.in);
      
      System.out.println("*** P|r|o|g|r|a|m|-|P|a|r|t|i|t|i|o|n ***\n");
      
      System.out.print("Input file name: ");
      String file = scan.nextLine();
      
      int[] arr = readIntFile(file);
      Arrays.sort(arr);
      
      // Algorithm 1:
      // Adds numbers starting from the front and the back of the array and puts them in subsets
      System.out.println("##############################################################");
      System.out.println("Algorithm: Alg1");
      readIntFile(file);
      // displays number of integers, and sum of them all
      display(arr);
      long et = System.nanoTime();
      // used to find ROS later
      int[] alg1Arr = alg1(arr);
      et = System.nanoTime() - et;
      System.out.printf("ROS: %.8f, Elapsed time: %.2E nsecs", ROS(alg1Arr), (double) et);
      System.out.println("\n");
      writeIntFile("Alg1_s1_" + file, alg1Arr);
      writeIntFile("Alg1_s2_" + file, alg1Arr);
      System.out.println("\n##############################################################");
      
      // Algorithm 2:
      // Takes every other number into subset 2 while the ones it skips into subset 1
      System.out.println("Algorithm: Alg2");
      display(arr);
      et = System.nanoTime();
      int[] alg2Arr = alg2(arr);
      et = System.nanoTime() - et;
      System.out.printf("ROS: %.8f, Elapsed time: %.2E nsecs", ROS(alg2Arr), (double) et);
      System.out.println("\n");
      writeIntFile("Alg2_s1_" + file, alg2Arr);
      writeIntFile("Alg1_s2_" + file, alg2Arr);
      System.out.println("\n##############################################################");
      
      // Algorithm 3:
      // Takes even numbers into subset 1 and odd into subset 2
      System.out.println("Algorithm: Alg3");
      display(arr);
      et = System.nanoTime();
      int[] alg3Arr = alg3(arr);
      et = System.nanoTime() - et;
      System.out.printf("ROS: %.8f, Elapsed time: %.2E nsecs", ROS(alg3Arr), (double) et);
      System.out.println("\n");
      writeIntFile("Alg3_s1_" + file, alg3Arr);
      writeIntFile("Alg3_s2_" + file, alg3Arr);
      System.out.println("\n##############################################################");
      
      // Algorithm 4:
      // Takes the highest number and puts it in its own subset
      System.out.println("Algorithm: Alg4");
      display(arr);
      et = System.nanoTime();
      int[] alg4Arr = alg4(arr);
      et = System.nanoTime() - et;
      System.out.printf("ROS: %.8f, Elapsed time: %.2E nsecs", ROS(alg4Arr),(double) et);
      System.out.println("\n");
      writeIntFile("Alg4_s1_" + file, alg4Arr);
      writeIntFile("Alg4_s2_" + file, alg4Arr);
      System.out.println("\n##############################################################");
      
      // Algorithm 5:
      // Attempts brute force through binary
      System.out.println("Algorithm: Alg5");
      display(arr);
      et = System.nanoTime();
      int[] alg5Arr = alg5(arr);
      et = System.nanoTime() - et;
      System.out.println("Sum1: " + alg5Arr[0] + ", Sum2: " + alg5Arr[1]);
      System.out.printf("ROS: %.8f, Elapsed time: %.2E nsecs", ROS(alg5Arr), (double) et);
      System.out.println("\n");
      writeIntFile("Alg5_s1_" + file, alg5Arr);
      writeIntFile("Alg5_s2_" + file, alg5Arr);
      System.out.println("\n##############################################################");

   }
//******************************************************************************************************
   
   // Displays the first parts of the formatting in the main method
   public static void display(int[] arr)
   {
      int[] ctArr = new int[2];
      
      // saves the number of integers and sum of them all
      ctArr = ctSum(arr);
      
      System.out.println("n: " + ctArr[0] + ", Sum: " + ctArr[1]);
      
      System.out.println("Partitioning...");
    
   }
   // finds ROS for the sum of each subset
   public static double ROS(int[] nums)
   {
      double box1 = nums[0];
      double box2 = nums[1];
      if(box1 >= box2)
      {
         return box1 / box2;
      }
      else
      {
         return box2 / box1;
      }
   }
   
   // tracks number of integers and sum of them all
   public static int[] ctSum(int[] arr)
   {
      int[] array = new int[2];
      int n = arr.length;
      array[0] = n;
      int sum = 0; 
      for(int i = 0; i < arr.length; i++)
      {
         sum += arr[i]; 
      } 
      array[1] = sum;
      return array;
   }
//*******************************************************************************************************
   public static int[] alg1(int[] arr)
   {  
      int[] box1 = new int[arr.length/2 + 1];
      int[] box2 = new int[arr.length/2 + 1];
      int box1Tot = 0;
      int box2Tot = 0;
      int x = arr.length - 1;
      // iterates through both sides of the array 
      for(int i = 0; i < arr.length / 2 + 1; i++)
      {
         box1[i] = arr[i];
         if(x != i)
         {
            box2[i] = arr[x];
         }
         x--;
      }
      x = 0;
      // adds up the total for each subset
      for(int i = 0; i < arr.length / 2 + 1; i++)
      {
         box1Tot += box1[i];
         box2Tot += box2[x];
         x++;
      }
      int[] boxes = {box1Tot, box2Tot};
      System.out.println("Sum 1: " + boxes[0] + ", Sum2: " + boxes[1]);
      return boxes;
   }
   
   public static int[] alg2(int[] arr)
   {
      int[] box1 = new int[arr.length/2 + 1];
      int[] box2 = new int[arr.length/2 + 1];
      int box1Tot = 0;
      int box2Tot = 0;
      //iterates through original array
      int x = 1;
      
      //iterates through partition cells
      int y = 0;
      int z = 0;
      
      // takes every other number in the array
      for(int i = 0; i < arr.length; i += 2)
      {
         if(i < arr.length)
         {
            box1[y] = arr[i];
         }
         if(x < arr.length)
         {
            box2[z] = arr[x];
         }        
         x += 2;
         z++;
         y++;
      }
       x = 0;
      
      //Adds each cell of both boxes
      for(int i = 0; i < arr.length; i++)
      {
         if(i < box1.length)
         {
            box1Tot += box1[i];
         }
         if(x < box2.length)
         {
         box2Tot += box2[x];
         }
         x++;
      }
      int[] boxes = {box1Tot, box2Tot};
      System.out.println("Sum 1: " + boxes[0] + ", Sum2: " + boxes[1]);
      return boxes;
      
   }
   
   public static int[] alg5(int[] arr)
   {
      int[] subs = new int[2];
      
      double rosSave = Integer.MAX_VALUE;
      int s0Save = 0;
      int s1Save = 0;
      int arrMinus = 2;
      
      //used to sort the two subsets 
      int[] bina = new int[arr.length];
      
      for(int binNum = 1; binNum <= Math.pow(2, arr.length); binNum++)
      {
         // attempts to place every box as either a 1 or 0 in the array
         int binNumUse = binNum;
         if(bina[bina.length - 1] == 1)
         {
            bina[bina.length - 1] = 2;
         }
         else
         {
            bina[bina.length - 1] = 1; 
         }
         int carry = 0;
         if(bina[bina.length - 1] == 2)
         {
            while(carry < bina.length || bina[bina.length - carry - 1] != 2)
            {
               carry++;
               if(bina[bina.length - carry] == 2)
               {
                  bina[bina.length - carry - 1] += 1;
               }
               else
               {
               break;
               }
            }
         } 
         for(int i = 0; i < bina.length; i++)
         {
            if(bina[i] == 2)
            {
               bina[i] = 0;
            }
         }
         // checks each box to see whether it has a 0 or 1 and then adds their totals
         for(int i = 0; i < arr.length; i++)
         {
            if(bina[i] == 1)
            {
               subs[1] += arr[i];
            }
            else
            {
               subs[0] += arr[i];
            }
         }
            if(subs[0] == subs[1])
            {
               return subs; 
            }
            else if(ROS(subs) < rosSave)
            {
               rosSave = ROS(subs);
               s0Save = subs[0];
               s1Save = subs[1];
            }
            if(binNum > Math.pow(2, arr.length) / 2)
            {
               int[] subsSave = {s0Save, s1Save};
               return subsSave;
            } 
            subs[0] = 0;
            subs[1] = 0;
      }
      subs[0] = s0Save;
      subs[1] = s1Save;
      return subs;
   }      
   public static int[] alg3(int[] arr)
   {
      int[] box1 = new int[arr.length];
      int[] box2 = new int[arr.length];
      int box1Tot = 0;
      int box2Tot = 0;
      
      // tracks the iteration of box1
      int x = 0;
      // tracks the iteration of box2
      int y = 0;
      
      // checks to see if the number is even
      for(int i = 0; i < arr.length; i++)
      {
         if(arr[i] % 2 == 0)
         {
            box1[x] = arr[i]; 
            x++;
         }
         else
         {
            box2[y] = arr[i];
            y++;
         }
      }
      x = 0;
      for(int i = 0; i < arr.length; i++)
      {
         if(i < box1.length)
         {
            box1Tot += box1[i];
         }
         if(x < box2.length)
         {
         box2Tot += box2[x];
         }
         x++;
      }
      int[] boxes = {box1Tot, box2Tot};
      System.out.println("Sum 1: " + boxes[0] + ", Sum2: " + boxes[1] + ", ROS: " + ROS(boxes));
      return boxes;
   }
   public static int[] alg4(int[] arr)
   {
      // takes the biggest number and places it in its own subset
      int[] boxes = new int[2];
      for(int i = 0; i < arr.length - 1; i++)
      {
         boxes[0] += arr[i];
      } 
      boxes[1] = arr[arr.length - 1];
      
      System.out.println("Sum 1: " + boxes[0] + ", Sum2: " + boxes[1] + ", ROS: " + ROS(boxes));
      return boxes;
   }
//*******************************************************************************************************
   public static int[] readIntFile(String inFileName) 
   {
      // open input file
      System.out.println("Reading input file: " + inFileName);
      File file = new File(inFileName);
      Scanner input = null;
      try {
         input = new Scanner(file);
      }
      catch (FileNotFoundException ex) {
         System.out.println();
         System.out.println("File read error, cannot open: " + inFileName);
         System.exit(1); // quit the program
      }

      // determine input file size
      int n = 0;
      while (input.hasNextInt()) {
         input.nextInt();
         n++;
      }
      input.close();

      // read integers from input file and store them in an array
      try {
         input = new Scanner(file);
      }
      catch (FileNotFoundException ex) {
         System.out.println("File read error, cannot open: " + inFileName);
         System.exit(1); // quit the program
      }
      int[] intArray = new int[n];
      for (int i=0;input.hasNextInt();i++) {
         intArray[i] = input.nextInt();
      }
      input.close();
     
      // return integer array to calling method
      return intArray;

   } // end readIntFile
   
   public static void writeIntFile(String outFileName, int[] intArray)
   {
      File file = new File(outFileName);
   
      // open output file for writing
      System.out.println("Writing output file: " + outFileName);
      PrintWriter output = null;
      try
      {
         output = new PrintWriter(file);
      }
      catch (FileNotFoundException ex)
      {
         System.out.println("File write error, cannot create: " + outFileName);
         System.exit(1); // quit the program
      }
        
      // write the integers to the file
      for (int i=0;i<intArray.length;i++)
      {
         output.print(String.valueOf(intArray[i]) + " ");
      }
      output.close();
   } // end writeIntFile

}