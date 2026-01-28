#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

if [ -e "./ACTUAL_SAVE.TXT" ]
then
    rm ACTUAL_SAVE.TXT
fi

if [ -e "./data/data.txt" ]
then
    rm ./data/data.txt
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin Mark < input.txt > ACTUAL.TXT
diff ACTUAL.TXT EXPECTED.TXT
RESULT1=$?

rm ./data/data.txt

java -classpath ../bin Mark < input_save.txt > ACTUAL_SAVE.TXT
diff ACTUAL_SAVE.TXT EXPECTED_SAVE.TXT
RESULT2=$?

# compare the output to the expected output
if [ $RESULT1 -eq 0 ] && [ $RESULT2 -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    [ $RESULT1 -ne 0 ] && echo " -> Case 1 failed"
    [ $RESULT2 -ne 0 ] && echo " -> Case 2 failed"
    exit 1
fi