
package com.cleancoder.args;

import java.util.*;

public class ArgsMain {
  public static void main(String[] args) {
    try {
      Args arg = new Args("l,p#,d*,f##,s[*]", args);
      boolean logging = arg.getBoolean('l');
      int port = arg.getInt('p');
      String directory = arg.getString('d');
      double doubleValue = arg.getDouble('f');
      String[] string_array = arg.getStringArray('s');
      executeApplication(logging, port, directory,doubleValue,string_array);
    } catch (ArgsException e) {
      System.out.printf("Argument error: %s\n", e.errorMessage());
    }
  }

  private static void executeApplication(boolean logging, int port, String directory, double doubleValue, String[] string_array) {
       System.out.printf("logging is %s, port:%d, directory:%s double:%f\n",logging, port, directory,doubleValue);
	System.out.printf("String Array contents:...\n");
	for(int i=0;i<string_array.length;i++)
	System.out.println(string_array[i]);
  }
}
