package com.cleancoder.args;

import static com.cleancoder.args.ArgsException.ErrorCode.*;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.*;

public class BooleanArgumentMarshaler implements ArgumentMarshaler {
  private boolean booleanValue=false;

  public void set(Iterator<String> currentArgument) throws ArgsException {
    String parameter = null;
    try {
      parameter = currentArgument.next();
      if((!parameter.equalsIgnoreCase("true")) && 
	(!parameter.equalsIgnoreCase( "false")))
       throw new ArgsException(MISSING_BOOLEAN);
      booleanValue = Boolean.parseBoolean(parameter);
      if((booleanValue != true) && (booleanValue != false))
       throw new ArgsException(INVALID_BOOLEAN);
    } catch (NoSuchElementException e) {
      throw new ArgsException(MISSING_BOOLEAN);
    }
  }

  public static boolean getValue(ArgumentMarshaler am) {
    if (am != null && am instanceof BooleanArgumentMarshaler)
      return ((BooleanArgumentMarshaler) am).booleanValue;
    else
      return false;
  }
}
