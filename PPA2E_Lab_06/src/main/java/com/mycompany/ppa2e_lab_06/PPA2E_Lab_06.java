/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ppa2e_lab_06;

import java.util.Random;   //// import added

/**
 * Stack for storing strings
 * @author Libor Vasa
 */

interface IStringStack {
 
  String[] expandArray (String[] data);
    
  void add(String s);
  
  String get(); 
 
  void removeLast();
}

/**
 * Procrastination reducing assistant
 * @author Libor Vasa
 */
class ProcrastinationAssistant {
  public static void main(String[] args) {
    IStringStack stack = new StackArray();  /// wrong name of interface
    IStringStack stack2 = new StackArray_2();  /// wrong name of interface

    long startTime = System.nanoTime();
    testStack(stack, 100000);
    long endTime = System.nanoTime();
    
    System.out.println("\nExecution Time capacity x2: " + (endTime - startTime)/1_000_000  + " ms");
    
    startTime = System.nanoTime();
    testStack(stack2, 100000);
    endTime = System.nanoTime();
    
    System.out.println("\nExecution Time capacity +10: " + (endTime - startTime)/1_000_000  + " ms");
    
    
  }

  /**
   * Generates and returns a random string 
   * @return returns a random string of random characters (5 to 24 characters)
   */
  public static String randomString() {
    StringBuilder sb = new StringBuilder();
    Random r = new Random();
    for (int i = 0; i < (5 + r.nextInt(20)); i++) {  
      sb.append((char) (r.nextInt(24) + 65));
    }
    return(sb.toString());
  }
  
  
  public static void testStack(IStringStack stack, int capacity){
      String[] random_values = new String[capacity];
      for (int i =0; i < random_values.length; i++){
          random_values[i]= randomString();
      }
      
      for (int i =0; i < random_values.length; i++){
          stack.add(random_values[i]);
      }
      
      for (int i =random_values.length; i >0; i--){
          if (stack.get().equals(random_values[i-1])){
            //  System.out.println("Values are the same.");
              stack.removeLast();
          }
      }
          
      
      
  }
  
  
  
}    //// missing


/**
 * Implementation of a stack using an array
 * @author Libor Vasa
 */
class StackArray implements IStringStack {

  private String[] data;

  private int freeIndex;

  public StackArray() {
    data = new String[5]; /// String instead of int
    freeIndex = 0;
  }
  
 
  
  @Override
  public String[] expandArray (String[] data){
      int capacity = data.length;
      int new_capacity = 2*capacity;      
      String[] new_data = new String[new_capacity];
      for (int i =0; i< capacity; i++){
          new_data[i] = data[i];
      }
      return new_data;
      
  }
  
  @Override
  public void add(String s) {    /// public and Override
    if (freeIndex > data.length-1){
        data = expandArray(data);
        //System.out.println(data.length);
       // System.out.println("Stack is full.");
    } 
     data[freeIndex] = s;
  //  System.out.println(data[freeIndex]);
    freeIndex++;
    
  
  }
  
  @Override
  public String get() {         /// public and Override
      if (freeIndex >0){
       return data[freeIndex-1];
       } else {
          System.out.print("Stack is empty");
          return null;
      }
     
  }

  @Override
  public void removeLast() {  /// public and Override
      if (freeIndex > 0) {
            freeIndex--;
            data[freeIndex] = null; 
         //   System.out.println("freeindex: "+ freeIndex);
        } else {
            System.out.println("Stack is empty");
        }
  }
}




class StackArray_2 extends StackArray { 
  
  @Override
  public String[] expandArray (String[] data){
      int capacity = data.length;
      int new_capacity = 10+capacity;     //////// here it should be changed 
      String[] new_data = new String[new_capacity];
      for (int i =0; i< capacity; i++){
          new_data[i] = data[i];
      }
      return new_data;
      
  }
  
 
}
