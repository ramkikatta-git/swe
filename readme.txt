The submitted source code supporting following features
1.	Supported schemas are
 - char    - Boolean arg.
 - char*   - String arg.
 - char#   - Integer arg.
 - char##  - double arg.
 - char[*] - one element of a string array.
        2. Corrected the Boolean handling logic
        3.  Provided utility (C ELF) to test all schema’s data values.
        4.   Javadoc  used for documentation purpose

The given java code tested in 2 ways
1.	Manual execution of CLI
2.	C utility  which tests  all command line options with various inputs 
(test_case.c placed in main folder)
With the manual execution of CLI found following issues in the given code Skelton
1.	BooleanArgumentMarshaler.java does not have proper logic to read data from command line option.  
a.	Option -l not checking any input from user. There is no exception handling if the user does not enter any input. If we are not providing any argument it is It  is always uses default  value
Scenario
root@osboxes:/home/osboxes/javaargs# java -cp build/jar/args.jar com/cleancoder/args/ArgsMain -l    -p 90  -d /tmp
logging is true, port:90, directory:/tmp

b.	Option -l not checking data validation. Not restricting to enter only Boolean data
Scenario
root@osboxes:/home/osboxes/javaargs# java -cp build/jar/args.jar com/cleancoder/args/ArgsMain -l 3   -p 34  -d /tmp
logging is true, port:0, directory:
2.	The IntegerArgumentMarshaler  option –p (#) not taking inputs if the –l (Boolean) option has input
a.	Scenario
root@osboxes:/home/osboxes/javaargs# java -cp build/jar/args.jar com/cleancoder/args/ArgsMain -l 3   -p 34  -d /tmp
logging is true, port:0, directory:
b.	.  –p considering input, if –l option has blank
Scenario
root@osboxes:/home/osboxes/javaargs# java -cp build/jar/args.jar com/cleancoder/args/ArgsMain -l    -p 90  -d /tmp
logging is true, port:90, directory:/tmp
c.	If the user does not provide –p option it is not raising missing argument exception
root@osboxes:/home/osboxes/javaargs# java -cp build/jar/args.jar com/cleancoder/args/ArgsMain -l 2  -p   -d /tmp
logging is true, port:0, directory:
d.	–p and –d working only –l options is blank
root@osboxes:/home/osboxes/javaargs# java -cp build/jar/args.jar com/cleancoder/args/ArgsMain -l    -p 90  -d /tmp
logging is true, port:90, directory:/tmp
root@osboxes:/home/osboxes/javaargs# 

 After fixing above issues, the modified files are
?	BooleanArgumentMarshaler.java
?	ArgsException.java
The main function code added in ArgsMain.java, Test application is in test_case.c (./test is exe)
  The java code and the test application, evaluating currently the following schemas
- char    - Boolean arg.
 - char*   - String arg.
 - char#   - Integer arg.
 - char##  - double arg.
 - char[*] - one element of a string array.
	
How to Build Jar &execute application:
Build Jar from the source code
root@osboxes:/home/osboxes/javaargs# ant compile jar
Buildfile: /home/osboxes/javaargs/build.xml
compile:
    [javac] /home/osboxes/javaargs/build.xml:8: warning: 'includeantruntime' was not set, defaulting to build.sysclasspath=last; set to false for repeatable builds
compile:
    [javac] /home/osboxes/javaargs/build.xml:8: warning: 'includeantruntime' was not set, defaulting to build.sysclasspath=last; set to false for repeatable builds
jar:
BUILD SUCCESSFUL
Total time: 1 second

Manual execution:
root@osboxes:/home/osboxes/javaargs# java -cp build/jar/args.jar com/cleancoder/args/ArgsMain -l true -p 23 -d /tmp -f 4546.5 -s red -s green -s blue
logging is true, port:23, directory:/tmp double:4546.500000
String Array contents:...
red
green
blue
C-Utility building & execution:
root@osboxes:/home/osboxes/javaargs# gcc test_case.c -o unit_test
root@osboxes:/home/osboxes/javaargs# ./unit_test 
java -cp build/jar/args.jar com/cleancoder/args/ArgsMain -l true -p 23 -d /tmp -f 4546.5 -s red -s green -s blue
logging is true, port:23, directory:/tmp double:4546.500000
String Array contents:...
red
green
blue
java -cp build/jar/args.jar com/cleancoder/args/ArgsMain -l false -p 23 -d /tmp -f 6789.4 -s apple -s orange -s grape
logging is false, port:23, directory:/tmp double:6789.400000
String Array contents:...
apple
orange
grape
java -cp build/jar/args.jar com/cleancoder/args/ArgsMain -l abc -p 23 -d /tmp -f 78.3 -s cat -s rat
Argument error: Could not find valid Boolean parameter for -l.
java -cp build/jar/args.jar com/cleancoder/args/ArgsMain -l 1 -p 23 -d /tmp -f 89.4 -s fish
Argument error: Could not find valid Boolean parameter for -l.
java -cp build/jar/args.jar com/cleancoder/args/ArgsMain -l 0 -p 23 -d /tmp -f 98.2
Argument error: Could not find valid Boolean parameter for -l.
java -cp build/jar/args.jar com/cleancoder/args/ArgsMain -l -1 -p 23 -d /tmp -f 88.3
Argument error: Could not find valid Boolean parameter for -l.
java -cp build/jar/args.jar com/cleancoder/args/ArgsMain -l   -p 23 -d /tmp -f 97.4
Argument error: Could not find valid Boolean parameter for -l.
java -cp build/jar/args.jar com/cleancoder/args/ArgsMain -l   -p -23 -d /tmp -f 67.4
Argument error: Could not find valid Boolean parameter for -l.
java -cp build/jar/args.jar com/cleancoder/args/ArgsMain -l   -p 23.59 -d /tmp -f 54.3
Argument error: Could not find valid Boolean parameter for -l.
java -cp build/jar/args.jar com/cleancoder/args/ArgsMain -l   -p 2.3.59 -d /tmp -f 2.3
Argument error: Could not find valid Boolean parameter for -l.
java -cp build/jar/args.jar com/cleancoder/args/ArgsMain -l True -p 259 -d /tmp -f 8.76
logging is true, port:259, directory:/tmp double:8.760000
String Array contents:...
java -cp build/jar/args.jar com/cleancoder/args/ArgsMain -l True -p 259 -d /tmp -f 8.8.76
Argument error: Argument -f expects a double but was '8.8.76'.
java -cp build/jar/args.jar com/cleancoder/args/ArgsMain -l True -p 259 -d /tmp -f red
Argument error: Argument -f expects a double but was 'red'.
java -cp build/jar/args.jar com/cleancoder/args/ArgsMain -l True -p 259 -d /tmp -f   
Argument error: Could not find double parameter for -f.
java -cp build/jar/args.jar com/cleancoder/args/ArgsMain -l True -p   -d /tmp -f 89.9
Argument error: Argument -p expects an integer but was '-d'.










